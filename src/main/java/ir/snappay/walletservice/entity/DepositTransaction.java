package ir.snappay.walletservice.entity;

import ir.snappay.walletservice.enums.TransactionStatus;
import ir.snappay.walletservice.enums.TransactionType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositTransaction extends Transaction {
    private String ibanNumber;
    private String verificationCode;

    public DepositTransaction(BigDecimal currentBalance, BigDecimal amount, User user, TransactionStatus status, TransactionType type, String ibanNumber, String verificationCode) {
        super(currentBalance, amount, user, status, type);
        this.ibanNumber = ibanNumber;
        this.verificationCode = verificationCode;
    }
}
