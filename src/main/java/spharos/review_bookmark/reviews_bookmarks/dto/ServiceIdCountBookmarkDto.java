package spharos.review_bookmark.reviews_bookmarks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceIdCountBookmarkDto {
    private Long serviceId;
    private int bookmarkCount;
}
