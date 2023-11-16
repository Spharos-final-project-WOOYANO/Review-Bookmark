package spharos.review_bookmark.bookmark.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {

    private Long bookmarkId;
    private Long serviceId;

}
