package spharos.review_bookmark.reviews_bookmarks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MostBookmarkReviewCountDto {
    int reviewCount;
    int bookmarkCount;
    Long serviceId;
}
