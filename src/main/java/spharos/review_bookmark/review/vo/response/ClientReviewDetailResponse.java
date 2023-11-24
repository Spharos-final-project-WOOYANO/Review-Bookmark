package spharos.review_bookmark.review.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientReviewDetailResponse {

    private Long reviewId;
    private List<String> reviewImageUrlList;
    private String userEmail;
    private Boolean reuse;
    private LocalDateTime createdAt;
    private String content;
    private String answerContent;

}
