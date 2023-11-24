package spharos.review_bookmark.review.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientReviewListResponse {

    private Long reviewId;
    private String reservationNum;
    private LocalDateTime createdAt;
    private Boolean reuse;
    private String userEmail;
    private String content;

}
