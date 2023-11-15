package shparos.review_bookmark.review.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shparos.review_bookmark.global.common.response.BaseResponse;
import shparos.review_bookmark.review.application.UserReviewService;
import shparos.review_bookmark.review.dto.ReviewRegisterDto;
import shparos.review_bookmark.review.vo.request.ReviewRegisterRequest;
import shparos.review_bookmark.review.vo.response.UserReviewListResponse;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review-bookmark")
public class UserReviewController {

    private final UserReviewService userReviewService;

    @Operation(summary = "리뷰등록",
            description = "리뷰등록",
            tags = { "Review" })
    @PostMapping("")
    public BaseResponse<?> registerReview(@RequestHeader("email") String email,
                                            @RequestBody ReviewRegisterRequest request) {

        // 리뷰등록
        ReviewRegisterDto reviewRegisterDto = ReviewRegisterDto.builder()
                .email(email)
                .reservationNum(request.getReservationNum())
                .serviceId(request.getServiceId())
                .content(request.getContent())
                .reuse(request.getReuse())
                .imageUrlList(request.getImageUrlList())
                .build();
        userReviewService.registerReview(reviewRegisterDto);
        return new BaseResponse<>();
    }

    @Operation(summary = "리뷰리스트 조회",
            description = "유저가 등록한 리뷰리스트 조회",
            tags = { "Review" })
    @GetMapping("/list")
    public BaseResponse<?> getReviewList(@RequestHeader("email") String email) {

        // 유저 리뷰리스트 조회
        List<UserReviewListResponse> response = userReviewService.getUserReviewList(email);
        return new BaseResponse<>(response);
    }


}
