package ir.snappay.walletservice.dto;

import ir.snappay.walletservice.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReceiveTransactionDto extends TransactionDto{
    @NotBlank(message = "sender mobile number should be filled.")
    @Pattern(regexp = "^[0]{1}[9]{1}[0-9]{9}$", message = "mobile number is wrong!")
    private String senderMobileNumber;

    public ReceiveTransactionDto(BigDecimal amount, TransactionType type, String senderMobileNumber) {
        super(amount, type);
        this.senderMobileNumber = senderMobileNumber;
    }
}
