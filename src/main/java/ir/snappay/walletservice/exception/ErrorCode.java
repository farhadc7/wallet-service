package ir.snappay.walletservice.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    USER_NOT_FOUND(4001,HttpStatus.Series.CLIENT_ERROR,"user not found exception");

    private  int value;
    private HttpStatus.Series series;
    private String reason;


    ErrorCode(int value, HttpStatus.Series series, String reason) {
        this.value= value;
        this.series= series;
        this.reason= reason;
    }
}
