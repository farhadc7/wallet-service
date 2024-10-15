package ir.snappay.walletservice.dto;

import ir.snappay.walletservice.enums.TransactionType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDto {
    @Min(value = 1000,message = "minimum value is 1000.")
    @Max(value = 10000000,message = "max value is 10000000")
    private BigDecimal amount;
    private TransactionType type;

    public TransactionDto(BigDecimal amount) {
        this.amount = amount;
    }
}
