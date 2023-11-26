package spharos.review_bookmark.review.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spharos.review_bookmark.bookmark.infrastructure.BookmarkRepository;
import spharos.review_bookmark.review.domain.ReviewImage;
import spharos.review_bookmark.review.dto.BookmarkReviewTotalNumberDto;
import spharos.review_bookmark.review.dto.ReviewListDto;
import spharos.review_bookmark.review.infrastructure.ReviewImageRepository;
import spharos.review_bookmark.review.infrastructure.ReviewRepository;
import java.util.List;

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

        return serviceIdList.stream()
                .map(serviceId -> {

                    int totalBookmark = bookmarkRepository.findByServiceId(serviceId).size();
                    int totalReview = reviewRepository.findByServiceId(serviceId).size();

                    return new BookmarkReviewTotalNumberDto(serviceId, totalBookmark, totalReview);
                })
                .toList();
    }

    @Override
    public List<ReviewListDto> retrieveReviewList(Long serviceId){

        // - 해당 서비스의 리뷰 리스트 조회
        return reviewRepository.findByServiceId(serviceId).stream()
                //- 리뷰 리스트의 각각의 요소들로 반복
                .map(review -> {
                    // - 리뷰 이미지 리스트 조회
                    List<ReviewImage> reviewImageList = reviewImageRepository.findByReview(review);
                    // - 리뷰 이미지 리스트에서 이미지 url만 추출
                    List<String> reviewImageUrlList = reviewImageList.stream()

                            .map(ReviewImage -> {

                                String reviewImageUrl = ReviewImage.getImageUrl();
                                return (reviewImageUrl!=null) ? reviewImageUrl : "";
                            })
                            .toList();

                    return ReviewListDto.builder()
                            .reviewId(review.getId())
                            .content(review.getContent())
                            .createAt(review.getCreatedAt())
                            .imageUrlList(reviewImageUrlList)
                            .answerContent(review.getAnswerContent())
                            .reuse(review.getReuse())
                            .userEmail(review.getUserEmail())
                            .build();

                })
                .toList();

    }
}