package ir.snappay.walletservice.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExceptionResponse {
    private int code;
    private String message;


}
