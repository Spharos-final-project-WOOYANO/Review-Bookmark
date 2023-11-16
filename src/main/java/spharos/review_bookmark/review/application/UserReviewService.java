package spharos.review_bookmark.review.application;

import spharos.review_bookmark.review.dto.ReviewRegisterDto;
import spharos.review_bookmark.review.vo.response.UserReviewDetailResponse;
import spharos.review_bookmark.review.vo.response.UserReviewListResponse;

import java.util.List;

public interface UserReviewService {

    // 리뷰등록
    void registerReview(ReviewRegisterDto reviewRegisterDto);
    // 유저 리뷰리스트 조회
    List<UserReviewListResponse> getUserReviewList(String email);
    // 리뷰 상세내역 조회
    UserReviewDetailResponse getReviewDetail(Long reviewId);
    // 리뷰삭제
    void deleteReview(Long reviewId);

}
