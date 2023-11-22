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

    private List<String> reviewImageUrlList;
    private String reviewContent;
    private LocalDateTime reviewCreateAt;
    private String answerContent;
    private boolean reuse;
}
