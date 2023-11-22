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

    private String reviewContent;
    private LocalDateTime reviewCreateAt;
    private List<String> reviewImageUrlList;
    private String answerContent;
    private String writerNickName;
    private String writerProfileImageUrl;
    private boolean reuse;

}
