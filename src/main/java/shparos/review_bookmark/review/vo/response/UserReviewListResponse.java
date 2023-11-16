package shparos.review_bookmark.review.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserReviewListResponse {

    private Long reviewId;
    private Long serviceId;
    private String reservationNum;
    private Boolean reuse;
    private LocalDateTime createdAt;

}
