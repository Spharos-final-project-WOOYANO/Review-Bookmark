package spharos.review_bookmark.review.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserReviewAvailableCheckResponse {

    private Boolean checkResult;

}
