package ir.snappay.walletservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawTransaction extends Transaction {
    @ManyToOne
    private BankAccount bankAccount;
}
