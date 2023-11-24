package spharos.review_bookmark.review.vo.request;

import lombok.Getter;

@Getter
public class ReviewReplyRegisterRequest {

    private Long reviewId;
    private String answerContent;

}
