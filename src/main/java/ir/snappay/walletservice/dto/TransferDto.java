package ir.snappay.walletservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDto extends TransactionDto{
    @NotBlank(message = "receiver mobile number should be filled.")
    @Pattern(regexp = "^[0]{1}[9]{1}[0-9]{9}$", message = "mobile number is wrong!")
    private String receiverMobileNumber;

    public TransferDto(BigDecimal amount, String receiverMobileNumber) {
        super(amount);
        this.receiverMobileNumber = receiverMobileNumber;
    }
}
