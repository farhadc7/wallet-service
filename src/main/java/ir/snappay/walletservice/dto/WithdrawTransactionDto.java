package ir.snappay.walletservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WithdrawTransactionDto extends TransactionDto{
    @NotBlank(message = "account number should be filled.")
    private String accountNumber;

    public WithdrawTransactionDto(BigDecimal amount, String accountNumber) {
        super(amount);
        this.accountNumber = accountNumber;
    }
}
