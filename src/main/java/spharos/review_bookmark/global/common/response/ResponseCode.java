package spharos.review_bookmark.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(HttpStatus.OK,true, 200, "요청에 성공하였습니다."),


    /**
     * 에러 코드
     */
    CANNOT_FIND_REVIEW(HttpStatus.BAD_REQUEST, false, 4010, "해당하는 리뷰가 존재하지 않습니다."),
    CANNOT_FIND_REVIEW_LIST(HttpStatus.BAD_REQUEST, false, 4010, "해당 서비스의 리뷰가 존재하지 않습니다."),
    CANNOT_FIND_BOOKMARK(HttpStatus.BAD_REQUEST, false, 4020, "해당하는 찜이 존재하지 않습니다."),
    CANNOT_FIND_REVIEW_WRITER(HttpStatus.BAD_REQUEST, false, 4030, "해당하는 리뷰 작성자가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final boolean success;
    private final int code;
    private final String message;

}