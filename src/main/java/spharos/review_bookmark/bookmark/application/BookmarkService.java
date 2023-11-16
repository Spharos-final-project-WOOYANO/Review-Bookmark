package spharos.review_bookmark.bookmark.application;

import spharos.review_bookmark.bookmark.vo.response.BookmarkResponse;

import java.util.List;

public interface BookmarkService {

    // 찜리스트조회
    List<BookmarkResponse> getBookmarkList(String email);

    // 찜 삭제
    void deleteBookmark(Long bookmarkId);

    // 찜 추가
    void registerBookmark(String email, Long serviceId);
}
