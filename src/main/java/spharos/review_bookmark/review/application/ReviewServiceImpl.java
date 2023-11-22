package spharos.review_bookmark.review.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spharos.review_bookmark.bookmark.domain.Bookmark;
import spharos.review_bookmark.bookmark.infrastructure.BookmarkRepository;
import spharos.review_bookmark.global.common.response.ResponseCode;
import spharos.review_bookmark.global.exception.CustomException;
import spharos.review_bookmark.review.domain.Review;
import spharos.review_bookmark.review.domain.ReviewImage;
import spharos.review_bookmark.review.dto.BookmarkReviewTotalNumberDto;
import spharos.review_bookmark.review.dto.ReviewListDto;
import spharos.review_bookmark.review.infrastructure.ReviewImageRepository;
import spharos.review_bookmark.review.infrastructure.ReviewRepository;
import spharos.review_bookmark.review.presentation.feignClient.UserFeignClient;
import spharos.review_bookmark.review.vo.response.ReviewWriterResponse;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserFeignClient userFeignClient;
    @Override
    @Transactional
    public List<BookmarkReviewTotalNumberDto> countBookmarkReview(List<Long> serviceIdList) {

        int reviewCounter;
        int bookmarkCounter;

        List<BookmarkReviewTotalNumberDto> bookmarkReviewTotalNumberDtoList = new ArrayList<>();

        for (Long serviceId: serviceIdList) {

            List<Review> reviews = reviewRepository.findByServiceId(serviceId);
            reviewCounter = reviews.size();

            List<Bookmark> bookmarks = bookmarkRepository.findByServiceId(serviceId);
            bookmarkCounter = bookmarks.size();

            BookmarkReviewTotalNumberDto bookmarkReviewTotalNumberDto = BookmarkReviewTotalNumberDto.builder()
                    .serviceId(serviceId)
                    .totalBookmark(reviewCounter)
                    .totalReview(bookmarkCounter)
                    .build();
            bookmarkReviewTotalNumberDtoList.add(bookmarkReviewTotalNumberDto);
        }

        return bookmarkReviewTotalNumberDtoList;
    }

    @Override
    public List<ReviewListDto> retrieveReviewList(Long serviceId){

        // - 해당 서비스의 리뷰 리스트 조회
        List<Review> reviewList = reviewRepository.findByServiceId(serviceId);

        if (reviewList.isEmpty()) {
            throw new CustomException(ResponseCode.CANNOT_FIND_REVIEW_LIST);
        }

        // - 해당 리뷰의 이미지 리스트 조회
        List<ReviewImage> reviewImageList;
        // - 각각의 이미지들의 url을 담을 String 타입의 리스트 생성
        List<String> reviewUrlList = new ArrayList<>();

        // - 리뷰 작성자들의 이메일을 담을 String 타입의 리스트 생성
        // - 추후 user service에서 리뷰 작성자들의 닉네임과 프로필 사진을 조회할때 사용됨
        List<String> reviewWriterEmailList = new ArrayList<>();

        for (Review review: reviewList){

            reviewImageList = reviewImageRepository.findByReview(review);

            for (ReviewImage reviewImage: reviewImageList){
                reviewUrlList.add(reviewImage.getImageUrl());
            }
            reviewWriterEmailList.add(review.getUserEmail());
        }
        // - 프로필 사진 설정안해놓은 사용자가 있다면 어떻게 하죠?
        // - feignClient를 사용해서 user에서 리뷰 작성자의 닉네임과 프로필 사진을 조회
        List<ReviewWriterResponse> retrieveReviewWriterList = userFeignClient.getReviewWriter(reviewWriterEmailList);

        // - 컨트롤러에 리턴할 Dto 생성
        List<ReviewListDto> reviewListDtos = new ArrayList<>();

        for(Review review: reviewList){

            // - 여기서 예외는 발생하지 않을거같긴한데 Optional로 처리하면 바로 밑에서
            //   해당 객체의 값을 꺼낼때.get을 사용해야해서 Optional로 처리하지 않기위해 예외처리를 했습니다.
            ReviewWriterResponse reviewWriterResponse = retrieveReviewWriterList.stream()
                    .filter(obj  -> obj.getReviewWriterEmail().equals(review.getUserEmail()))
                    .findFirst()
                    .orElseThrow(() -> new CustomException(ResponseCode.CANNOT_FIND_REVIEW_WRITER));

            ReviewListDto reviewListDto = ReviewListDto.builder()
                    .reviewContent(review.getContent())
                    .reviewCreateAt(review.getCreatedAt())
                    .reviewImageUrlList(reviewUrlList)
                    .answerContent(review.getAnswerContent())
                    .writerNickName(reviewWriterResponse.getReviewWriterNickName())
                    .writerProfileImageUrl(reviewWriterResponse.getReviewWriterProfileImageUrl())
                    .reuse(review.getReuse())
                    .build();

            reviewListDtos.add(reviewListDto);
        }

        return reviewListDtos;
    }
}
