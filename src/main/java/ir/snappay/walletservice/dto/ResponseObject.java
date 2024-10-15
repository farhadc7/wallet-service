package ir.snappay.walletservice.dto;

import ir.snappay.walletservice.exception.ErrorCode;
import ir.snappay.walletservice.exception.ExceptionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObject <R>{
    private R response;
    private ExceptionResponse  error;
}
