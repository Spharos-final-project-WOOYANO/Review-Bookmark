package spharos.review_bookmark.review.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewWriterResponse {
    private String reviewWriterEmail;
    private String reviewWriterNickName;
    private String reviewWriterProfileImageUrl;
}
