package shparos.review_bookmark.review.application;

import shparos.review_bookmark.review.dto.ReviewRegisterDto;
import shparos.review_bookmark.review.vo.response.UserReviewListResponse;

import java.util.List;

public interface UserReviewService {

    // 리뷰등록
    void registerReview(ReviewRegisterDto reviewRegisterDto);
    // 유저 리뷰리스트 조회
    List<UserReviewListResponse> getUserReviewList(String email);

}
