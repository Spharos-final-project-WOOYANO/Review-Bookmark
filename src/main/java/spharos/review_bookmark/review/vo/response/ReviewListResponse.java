package spharos.review_bookmark.review.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewListResponse {
    private Long serviceId;
    private Long reviewId;
    private String content;
    private LocalDateTime createAt;
    private List<String> imageUrlList;
    private String answerContent;
    private boolean reuse;
}
