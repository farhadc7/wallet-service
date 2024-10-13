package ir.snappay.walletservice.service.transaction;

import ir.snappay.walletservice.repository.projection.TransactionSumDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TotalBalanceCalculator {
    public BigDecimal calculate(TransactionSumDto sumDto){
        if(sumDto== null){
           return  BigDecimal.ZERO;
        }
        return  sumDto.getDepositSum().add(sumDto.getReceiveSum()).subtract(sumDto.getWithdrawSum()).subtract(sumDto.getSendSum());
    }
}
