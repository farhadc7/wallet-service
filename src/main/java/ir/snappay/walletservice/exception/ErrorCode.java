package ir.snappay.walletservice.exception;

import lombok.ToString;
import org.springframework.http.HttpStatus;


public enum ErrorCode {

    NOT_ENOUGH_BALANCE(4001,HttpStatus.Series.CLIENT_ERROR,"balance is not enough."),
    RECEIVER_USER_NOT_FOUND(4002,HttpStatus.Series.CLIENT_ERROR,"receiver user not found.");

    private  int value;
    private HttpStatus.Series series;
    private String reason;


    ErrorCode(int value, HttpStatus.Series series, String reason) {
        this.value= value;
        this.series= series;
        this.reason= reason;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HttpStatus.Series getSeries() {
        return series;
    }

    public void setSeries(HttpStatus.Series series) {
        this.series = series;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
