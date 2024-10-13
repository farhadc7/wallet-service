package ir.snappay.walletservice.dto;

import ir.snappay.walletservice.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDto {
    private BigDecimal amount;
    private TransactionType type;
}
