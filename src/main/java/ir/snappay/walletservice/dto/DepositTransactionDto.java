package ir.snappay.walletservice.dto;

import ir.snappay.walletservice.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepositTransactionDto extends TransactionDto{
    @NotBlank(message = "iban number should be filled")
    private String ibanNumber;
    @NotBlank(message = "verification code should be filled")
    private String verificationCode;

    public DepositTransactionDto(BigDecimal amount,  String ibanNumber, String verificationCode) {
        super(amount);
        this.ibanNumber = ibanNumber;
        this.verificationCode = verificationCode;
    }
}
