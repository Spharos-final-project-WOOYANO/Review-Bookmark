package spharos.review_bookmark.reviews_bookmarks.application;

import spharos.review_bookmark.reviews_bookmarks.dto.MostBookmarkReviewCountDto;
import java.util.List;

public interface ReviewBookmarkService {

    // 찜 수 많은 업체의 찜 수와 리뷰 수 조회
    List<MostBookmarkReviewCountDto> getBestBookmarkReviewCount();
}
