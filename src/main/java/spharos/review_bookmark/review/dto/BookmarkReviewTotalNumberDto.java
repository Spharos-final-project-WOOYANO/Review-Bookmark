package spharos.review_bookmark.review.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkReviewTotalNumberDto {
    private Long serviceId;
    private int totalBookmark;
    private int totalReview;
}
