package spharos.review_bookmark.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spharos.review_bookmark.global.common.response.ResponseCode;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final ResponseCode responseCode;

}