package ir.snappay.walletservice.service;

import ir.snappay.walletservice.dto.TransferDto;
import ir.snappay.walletservice.dto.TransactionDto;
import ir.snappay.walletservice.dto.WalletResponse;
import ir.snappay.walletservice.dto.WithdrawTransactionDto;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.repository.projection.TransactionSumDto;
import ir.snappay.walletservice.service.transaction.TransactionService;
import ir.snappay.walletservice.util.ContextUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WalletService {
    @Autowired
    private List<TransactionService> transactionServiceList;
    private Map<TransactionType, TransactionService> transactionServiceMap= new HashMap<>();

    @PostConstruct
    public void setup(){
        transactionServiceList.forEach(t->transactionServiceMap.put(t.getType(),t));
    }

    public void deposit(TransactionDto dto){
        dto.setType(TransactionType.DEPOSIT);
        perform(dto);
    }

    public void withdraw(WithdrawTransactionDto dto) {
        dto.setType(TransactionType.WITHDRAW);
        perform(dto);
    }

    public void transfer(TransferDto dto) {
        dto.setType(TransactionType.SEND);
        perform(dto);
    }

    private void perform(TransactionDto dto){
        transactionServiceMap.get(dto.getType()).perform(dto);
    }

    public WalletResponse getBalance() {
        var balance =transactionServiceMap.get(TransactionType.DEPOSIT).getCurrentBalance(ContextUtil.getUser().getUsername());
        return new WalletResponse(balance);
    }

    public TransactionSumDto getTotalByTimePeriod(long period) {
       return transactionServiceMap.get(TransactionType.DEPOSIT).getTotalByDate(LocalDateTime.now().minusDays(period));

    }

    public List<Transaction> getAll() {
        return transactionServiceMap.get(TransactionType.DEPOSIT).getAll();
    }
}
