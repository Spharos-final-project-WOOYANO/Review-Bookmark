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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final BookmarkRepository bookmarkRepository;

    @Override
    @Transactional
    public List<BookmarkReviewTotalNumberDto> countBookmarkReview(List<Long> serviceIdList) {

        int reviewCounter = 0;
        int bookmarkCounter = 0;

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

        // 1. 해당 서비스의 리뷰 리스트 조회
        List<Review> reviewList = reviewRepository.findByServiceId(serviceId);

        if (reviewList.isEmpty()) {
            throw new CustomException(ResponseCode.CANNOT_FIND_REVIEW_LIST);
        }

        // 2. 각각 리뷰들의 이미지 리스트들을 담을 리스트 생성
        List<ReviewImage> reviewImageList = new ArrayList<>();
        // 3. 리뷰들의 이미지들을 담을 리스트 생성
        List<String> reviewUrlList = new ArrayList<>();
        // 4. 컨트롤러에 리턴할 Dto 생성
        List<ReviewListDto> reviewListDto = new ArrayList<>();

        for(Review review: reviewList){

            reviewImageList = reviewImageRepository.findByReview(review);

            for(ReviewImage reviewImage: reviewImageList){

                if (reviewImage.getImageUrl() == null){
                    //해당 리뷰에 이미지가 없으면 반복문을 탈출한뒤 다음 리뷰로 넘어감
                    break;
                }
                reviewUrlList.add(reviewImage.getImageUrl());
            }

            ReviewListDto reviewDto = ReviewListDto.builder()
                    .reviewImageUrlList(reviewUrlList)
                    .reviewContent(review.getContent())
                    .reuse(review.getReuse())
                    .answerContent(review.getAnswerContent())
                    .reviewCreateAt(review.getCreatedAt())
                    .build();

            reviewListDto.add(reviewDto);
        }
        return reviewListDto;
    }
}
