package ir.snappay.walletservice.entity;

import ir.snappay.walletservice.enums.TransactionStatus;
import ir.snappay.walletservice.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Builder
public class Transaction extends BaseEntity {
    @Column(precision = 20,scale = 0)
    private BigDecimal currentBalance;
    @Column(precision = 20,scale = 0)
    private BigDecimal amount;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @Enumerated(EnumType.STRING)
    private TransactionType type;


}
