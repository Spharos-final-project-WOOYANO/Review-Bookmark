package spharos.review_bookmark.review.vo.request;

import lombok.Getter;

@Getter
public class ReviewReplyModifyRequest {

    private Long reviewId;
    private String answerContent;

}
