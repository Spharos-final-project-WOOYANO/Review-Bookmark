package shparos.review_bookmark.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shparos.review_bookmark.global.common.response.ResponseCode;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final ResponseCode responseCode;

}
