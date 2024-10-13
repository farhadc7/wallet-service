package ir.snappay.walletservice.service;

import ir.snappay.walletservice.dto.TransactionDto;
import ir.snappay.walletservice.dto.WalletResponse;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.service.transaction.TransactionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public WalletResponse deposit(TransactionDto dto){
        dto.setType(TransactionType.DEPOSIT);
        transactionServiceMap.get(TransactionType.DEPOSIT).perform(dto);
        return null;
    }

}
