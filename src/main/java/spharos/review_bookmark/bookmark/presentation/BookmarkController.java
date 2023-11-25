package spharos.review_bookmark.bookmark.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spharos.review_bookmark.bookmark.application.BookmarkService;
import spharos.review_bookmark.bookmark.vo.request.RegisterBookmarkRequest;
import spharos.review_bookmark.bookmark.vo.response.BookmarkResponse;
import spharos.review_bookmark.global.common.response.BaseResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review-bookmark/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(summary = "찜 리스트 조회",
            description = "찜 리스트 조회",
            tags = { "Bookmark" })
    @GetMapping("/list")
    public BaseResponse<?> getBookmarkList(@RequestHeader("email") String email) {

        // 찜리스트 조회
        List<BookmarkResponse> response = bookmarkService.getBookmarkList(email);

        return new BaseResponse<>(response);
    }

    @Operation(summary = "찜 삭제",
            description = "찜 삭제",
            tags = { "Bookmark" })
    @DeleteMapping("/{bookmarkId}")
    public BaseResponse<?> deleteBookmark(@PathVariable("bookmarkId") Long bookmarkId) {

        // 찜 삭제
        bookmarkService.deleteBookmark(bookmarkId);
        return new BaseResponse<>();
    }

    @Operation(summary = "찜 추가",
            description = "찜 추가",
            tags = { "Bookmark" })
    @PostMapping("")
    public BaseResponse<?> registerBookmark(@RequestHeader("email") String email,
                                            @RequestBody RegisterBookmarkRequest request) {

        // 찜 추가
        bookmarkService.registerBookmark(email, request.getServiceId());
        return new BaseResponse<>();
    }

}
