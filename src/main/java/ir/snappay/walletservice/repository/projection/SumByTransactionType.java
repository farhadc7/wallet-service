package ir.snappay.walletservice.repository.projection;

import java.math.BigDecimal;

public interface SumByTransactionType {
    BigDecimal getDepositSum();
    BigDecimal getWithdrawSum();
    BigDecimal getSendSum();
    BigDecimal getReceiveSum();
}
