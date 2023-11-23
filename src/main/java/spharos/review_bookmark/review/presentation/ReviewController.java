package spharos.review_bookmark.review.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spharos.review_bookmark.global.common.response.BaseResponse;
import spharos.review_bookmark.review.application.ReviewService;
import spharos.review_bookmark.review.dto.ReviewListDto;
import spharos.review_bookmark.review.vo.response.TotalBookmarkReviewResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review-bookmark")
public class ReviewController {

    private final ReviewService reviewService;
    @Operation(summary = "찜 수와 리뷰 수 확인",
            description = "찜 수와 리뷰 수 확인",
            tags = { "Bookmark&Review" })
    @PostMapping("/count/review_bookmark")
    public BaseResponse<?> countReviewBookmarkController(@RequestBody List<Long> serviceIdList){

        List<TotalBookmarkReviewResponse> responseList = reviewService.countBookmarkReview(serviceIdList).stream()
                .map(bookmarkReviewTotalNumberDto -> TotalBookmarkReviewResponse.builder()
                        .serviceId(bookmarkReviewTotalNumberDto.getServiceId())
                        .totalBookmark(bookmarkReviewTotalNumberDto.getTotalBookmark())
                        .totalReview(bookmarkReviewTotalNumberDto.getTotalReview())
                        .build())
                .toList();

        return new BaseResponse<>(responseList);
    }

    @Operation(summary = "리뷰 리스트 조회",
            description = "리뷰 리스트 조회",
            tags = { "Review List Retrieve" })
    @GetMapping("/review/list")
    public BaseResponse<List<ReviewListDto>> retrieveReviewListController(@RequestParam("serviceId") Long serviceId){
        // 리뷰 리스트 조회
        List<ReviewListDto> reviewListDtoList = reviewService.retrieveReviewList(serviceId);

        return new BaseResponse<>(reviewListDtoList);

    }
}
