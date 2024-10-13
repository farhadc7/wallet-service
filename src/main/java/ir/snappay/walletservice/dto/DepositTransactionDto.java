package ir.snappay.walletservice.dto;

import ir.snappay.walletservice.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepositTransactionDto extends TransactionDto{
    private String ibanNumber;
    private String verificationCode;
}
