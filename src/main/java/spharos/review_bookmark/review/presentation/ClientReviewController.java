package spharos.review_bookmark.review.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spharos.review_bookmark.global.common.response.BaseResponse;
import spharos.review_bookmark.review.application.ClientReviewService;
import spharos.review_bookmark.review.vo.request.ReviewReplyModifyRequest;
import spharos.review_bookmark.review.vo.request.ReviewReplyRegisterRequest;
import spharos.review_bookmark.review.vo.response.ClientReviewDetailResponse;
import spharos.review_bookmark.review.vo.response.ClientReviewListResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review-bookmark/client/review")
public class ClientReviewController {

    private final ClientReviewService clientReviewService;

    /*
        리뷰 리스트 조회(전체)
    */
    @Operation(summary = "리뷰 리스트 조회(전체)",
            description = "업체의 리뷰 리스트 조회(전체)",
            tags = { " Client Review" })
    @GetMapping("/list/{serviceId}")
    public BaseResponse<?> getClientReviewList(@PathVariable("serviceId") Long serviceId) {

        // 리뷰 리스트 조회(전체)
        List<ClientReviewListResponse> responses = clientReviewService.getClientReviewList(serviceId);
        return new BaseResponse<>(responses);
    }

    /*
        리뷰 상세 조회
    */
    @Operation(summary = "리뷰 상세 조회",
            description = "리뷰 상세 조회",
            tags = { " Client Review" })
    @GetMapping("/detail/{reviewId}")
    public BaseResponse<?> getClientReviewDetail(@PathVariable("reviewId") Long reviewId) {

        // 리뷰 상세 조회
        ClientReviewDetailResponse response = clientReviewService.getClientReviewDetail(reviewId);
        return new BaseResponse<>(response);
    }

    /*
        리뷰 답글 작성
    */
    @Operation(summary = "리뷰 답글 작성",
            description = "리뷰 답글 작성",
            tags = { " Client Review" })
    @PostMapping("/reply")
    public BaseResponse<?> registerReviewReply(@RequestBody ReviewReplyRegisterRequest request) {

        // 리뷰 답글 작성
        clientReviewService.registerReviewReply(request);
        return new BaseResponse<>();
    }

    /*
        리뷰 답글 수정
    */
    @Operation(summary = "리뷰 답글 수정",
            description = "리뷰 답글 수정",
            tags = { " Client Review" })
    @PutMapping("/reply")
    public BaseResponse<?> modifyReviewReply(@RequestBody ReviewReplyModifyRequest request) {

        // 리뷰 답글 수정
        clientReviewService.modifyReviewReply(request);
        return new BaseResponse<>();
    }

}
