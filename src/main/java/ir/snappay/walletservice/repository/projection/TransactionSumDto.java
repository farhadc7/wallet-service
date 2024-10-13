package ir.snappay.walletservice.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionSumDto {
   private BigDecimal depositSum;
   private BigDecimal withdrawSum;
   private BigDecimal sendSum;
   private BigDecimal receiveSum;

   public TransactionSumDto(BigDecimal depositSum, BigDecimal withdrawSum, BigDecimal sendSum, BigDecimal receiveSum) {
      this.depositSum = depositSum== null?BigDecimal.ZERO:depositSum;
      this.withdrawSum = withdrawSum== null?BigDecimal.ZERO:withdrawSum;
      this.sendSum = sendSum == null?BigDecimal.ZERO:sendSum;
      this.receiveSum = receiveSum ==null?BigDecimal.ZERO:receiveSum;
   }

   public BigDecimal getDepositSum() {
      return depositSum;
   }

   public void setDepositSum(BigDecimal depositSum) {
      this.depositSum = depositSum;
   }

   public BigDecimal getWithdrawSum() {
      return withdrawSum;
   }

   public void setWithdrawSum(BigDecimal withdrawSum) {
      this.withdrawSum = withdrawSum;
   }

   public BigDecimal getSendSum() {
      return sendSum;
   }

   public void setSendSum(BigDecimal sendSum) {
      this.sendSum = sendSum;
   }

   public BigDecimal getReceiveSum() {
      return receiveSum;
   }

   public void setReceiveSum(BigDecimal receiveSum) {
      this.receiveSum = receiveSum;
   }
}
