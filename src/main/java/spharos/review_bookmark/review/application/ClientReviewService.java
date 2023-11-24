package spharos.review_bookmark.review.application;

import spharos.review_bookmark.review.vo.request.ReviewReplyRegisterRequest;
import spharos.review_bookmark.review.vo.response.ClientReviewDetailResponse;
import spharos.review_bookmark.review.vo.response.ClientReviewListResponse;

import java.util.List;

public interface ClientReviewService {

    // 리뷰 리스트 조회(전체)
    List<ClientReviewListResponse> getClientReviewList(Long serviceId);
    // 리뷰 상세 조회
    ClientReviewDetailResponse getClientReviewDetail(Long reviewId);
    // 리뷰 답글 작성
    void registerReviewReply(ReviewReplyRegisterRequest request);


}
