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
public class SendTransaction extends Transaction {
    @ManyToOne
    private User receiver;
}
