package spharos.review_bookmark.review.vo.request;

import lombok.Getter;

import java.util.List;

@Getter
public class ReviewRegisterRequest {

    private String reservationNum;
    private Long serviceId;
    private String content;
    private Boolean reuse;
    private List<String> imageUrlList;

}
