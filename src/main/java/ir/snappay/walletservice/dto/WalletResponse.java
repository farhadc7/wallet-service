package ir.snappay.walletservice.dto;

import ir.snappay.walletservice.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {
    private BigDecimal balance;
}
