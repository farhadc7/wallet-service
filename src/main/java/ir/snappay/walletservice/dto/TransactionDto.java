package ir.snappay.walletservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.snappay.walletservice.enums.TransactionType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @Schema(hidden = true)
    private TransactionType type;

    public TransactionDto(BigDecimal amount) {
        this.amount = amount;
    }
}
