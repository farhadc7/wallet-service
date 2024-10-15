package ir.snappay.walletservice.dto;

import ir.snappay.walletservice.entity.User;
import ir.snappay.walletservice.enums.TransactionStatus;
import ir.snappay.walletservice.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private BigDecimal currentBalance;
    private BigDecimal amount;
    private TransactionStatus status;
    private TransactionType type;
    private LocalDateTime date;
}
