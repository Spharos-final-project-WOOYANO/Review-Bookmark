package shparos.review_bookmark.review.vo.response;

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
public class UserReviewDetailResponse {

    private LocalDateTime createdAt;
    private Boolean reuse;
    private List<String> imageUrlList;
    private String content;
    private String answerContent;

}
