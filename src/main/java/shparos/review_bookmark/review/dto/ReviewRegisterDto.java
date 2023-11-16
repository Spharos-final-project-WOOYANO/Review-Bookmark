package shparos.review_bookmark.review.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRegisterDto {

    private String email;
    private String reservationNum;
    private Long serviceId;
    private String content;
    private Boolean reuse;
    private List<String> imageUrlList;

}
