package spharos.review_bookmark.reviews_bookmarks.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MostBookmarkReviewCountResponse {

    private Long serviceId;
    private int bookmarkCount;
    private int reviewCount;
}
