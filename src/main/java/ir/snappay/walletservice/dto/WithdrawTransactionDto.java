package ir.snappay.walletservice.dto;

import ir.snappay.walletservice.entity.BankAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WithdrawTransactionDto extends TransactionDto{
    @NotBlank(message = "account number should be filled.")
    private String accountNumber;
}
