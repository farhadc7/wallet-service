package ir.snappay.walletservice.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSumDto {
   private Long depositSum;
   private Long withdrawSum;
   private Long sendSum;
   private Long receiveSum;
}
