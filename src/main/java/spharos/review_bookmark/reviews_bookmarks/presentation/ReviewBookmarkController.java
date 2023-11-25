package spharos.review_bookmark.reviews_bookmarks.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spharos.review_bookmark.reviews_bookmarks.application.ReviewBookmarkService;
import spharos.review_bookmark.reviews_bookmarks.vo.response.MostBookmarkReviewCountResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/review-bookmark")
@RequiredArgsConstructor
public class ReviewBookmarkController {

    private final ReviewBookmarkService reviewBookmarkService;

    @Operation(summary = "찜 많은 서비스 조회",
            description = "찜 많이 받은 서비스 순으로 조회",
            tags = { "Best Bookmark" })
    @GetMapping("/best-bookmark/service")
    public List<MostBookmarkReviewCountResponse> getBestBookmarkReviewCount() {

        return reviewBookmarkService.getBestBookmarkReviewCount().stream()
                .map(dto -> MostBookmarkReviewCountResponse.builder()
                        .serviceId(dto.getServiceId())
                        .bookmarkCount(dto.getBookmarkCount())
                        .reviewCount(dto.getReviewCount())
                        .build())
                .toList();
    }
}
