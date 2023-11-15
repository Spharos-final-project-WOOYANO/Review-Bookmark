package shparos.review_bookmark.review.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shparos.review_bookmark.global.common.response.ResponseCode;
import shparos.review_bookmark.global.exception.CustomException;
import shparos.review_bookmark.review.domain.Review;
import shparos.review_bookmark.review.domain.ReviewImage;
import shparos.review_bookmark.review.dto.ReviewRegisterDto;
import shparos.review_bookmark.review.infrastructure.ReviewImageRepository;
import shparos.review_bookmark.review.infrastructure.ReviewRepository;
import shparos.review_bookmark.review.vo.response.UserReviewDetailResponse;
import shparos.review_bookmark.review.vo.response.UserReviewListResponse;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserReviewServiceImpl implements UserReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    // 리뷰등록
    @Override
    @Transactional
    public void registerReview(ReviewRegisterDto dto) {

        // 리뷰등록
        Review review = Review.createReview(dto.getEmail(), dto.getServiceId(), dto.getReservationNum(),
                dto.getContent(), dto.getReuse());
        reviewRepository.save(review);

        // 리뷰 이미지가 있는 경우 이미지등록
        if(dto.getImageUrlList() != null) {
            for(String imageUrl : dto.getImageUrlList()) {
                ReviewImage reviewImage = ReviewImage.createReviewImage(review, imageUrl);
                reviewImageRepository.save(reviewImage);
            }
        }
    }

    // 유저 리뷰리스트 조회
    @Override
    public List<UserReviewListResponse> getUserReviewList(String email) {

        // 유저 리뷰리스트를 조회
        List<Review> reviewList = reviewRepository.findByUserEmailOrderByIdDesc(email);

        // 리뷰정보가 없는 경우 null을 return
        if(reviewList.isEmpty()) {
            return null;
        }

        List<UserReviewListResponse> responseList = new ArrayList<>();
        for(Review review:reviewList) {
            UserReviewListResponse response = UserReviewListResponse.builder()
                    .reviewId(review.getId())
                    .serviceId(review.getServiceId())
                    .reservationNum(review.getReservationNum())
                    .reuse(review.getReuse())
                    .createdAt(review.getCreatedAt())
                    .build();
            responseList.add(response);
        }

        return responseList;
    }

    // 리뷰 상세내역 조회
    @Override
    public UserReviewDetailResponse getReviewDetail(Long reviewId) {

        // 리뷰를 조회
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ResponseCode.CANNOT_FIND_REVIEW));

        // 리뷰이미지를 조회
        List<ReviewImage> reviewImageList = reviewImageRepository.findByReview(review);

        List<String> imageUrlList = new ArrayList<>();
        // 리뷰이미지가 존재하는 경우
        if(!reviewImageList.isEmpty()) {
            imageUrlList = reviewImageList.stream()
                    .map(ReviewImage::getImageUrl).toList();
        }

        return UserReviewDetailResponse.builder()
                .createdAt(review.getCreatedAt())
                .reuse(review.getReuse())
                .imageUrlList(imageUrlList)
                .content(review.getContent())
                .answerContent(review.getAnswerContent())
                .build();
    }

    // 리뷰삭제
    @Override
    @Transactional
    public void deleteReview(Long reviewId) {

        // 삭제할 리뷰를 조회
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ResponseCode.CANNOT_FIND_REVIEW));

        // 삭제할 리뷰이미지가 존재하는지 조회
        List<ReviewImage> reviewImageList = reviewImageRepository.findByReview(review);

        // 리뷰이미지가 존재하는 경우 삭제
        if(!reviewImageList.isEmpty()) {
            for(ReviewImage reviewImage : reviewImageList) {
                reviewImageRepository.delete(reviewImage);
            }
        }

        // 리뷰를 삭제
        reviewRepository.delete(review);
    }


}
