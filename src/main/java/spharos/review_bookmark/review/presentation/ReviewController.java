package spharos.review_bookmark.review.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spharos.review_bookmark.global.common.response.BaseResponse;
import spharos.review_bookmark.review.application.ReviewService;
import spharos.review_bookmark.review.dto.BookmarkReviewTotalNumberDto;
import spharos.review_bookmark.review.vo.response.TotalBookmarkReviewResponse;

import java.util.ArrayList;
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
    public BaseResponse<List<TotalBookmarkReviewResponse>> countReviewBookmark(@RequestBody List<Long> serviceIdList) {

        List<BookmarkReviewTotalNumberDto> bookmarkReviewTotalNumberDtoList = reviewService.countBookmarkReview(serviceIdList);

        List<TotalBookmarkReviewResponse> totalBookmarkReviewResponseList = new ArrayList<>();

        for (BookmarkReviewTotalNumberDto bookmarkReviewTotalNumberDto: bookmarkReviewTotalNumberDtoList) {
            TotalBookmarkReviewResponse totalBookmarkReviewResponse = TotalBookmarkReviewResponse.builder()
                    .serviceId(bookmarkReviewTotalNumberDto.getServiceId())
                    .totalBookmark(bookmarkReviewTotalNumberDto.getTotalBookmark())
                    .totalReview(bookmarkReviewTotalNumberDto.getTotalReview())
                    .build();
            totalBookmarkReviewResponseList.add(totalBookmarkReviewResponse);
        }

        return new BaseResponse<>(totalBookmarkReviewResponseList);
    }
}
