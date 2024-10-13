package ir.snappay.walletservice.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    ErrorCode errorCode;

    public CustomException( ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
