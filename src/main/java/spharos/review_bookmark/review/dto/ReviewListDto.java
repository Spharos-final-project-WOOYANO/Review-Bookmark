package spharos.review_bookmark.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListDto {

    private String content;
    private LocalDateTime createAt;
    private List<String> imageUrlList;
    private String answerContent;
    private boolean reuse;

}
