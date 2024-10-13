package ir.snappay.walletservice.entity;

import ir.snappay.walletservice.enums.TransactionStatus;
import ir.snappay.walletservice.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Transaction extends BaseEntity {
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @Enumerated(EnumType.STRING)
    private TransactionType type;


}
