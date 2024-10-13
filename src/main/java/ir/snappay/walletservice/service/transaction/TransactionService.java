package ir.snappay.walletservice.service.transaction;

import ir.snappay.walletservice.dto.DepositTransactionDto;
import ir.snappay.walletservice.dto.TransactionDto;
import ir.snappay.walletservice.entity.DepositTransaction;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.entity.User;
import ir.snappay.walletservice.enums.TransactionStatus;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.repository.TransactionRepository;
import ir.snappay.walletservice.util.ContextUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public abstract class TransactionService {
    public final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void perform(TransactionDto dto){
        Transaction trx= addDetails(dto);
        setGeneralData(dto,trx);
        persistTransaction(trx);

    }

    private void persistTransaction(Transaction trx) {
        synchronized (this){
            BigDecimal balance  = getCurrentBalance(trx.getUser());
            trx.setCurrentBalance(balance);
            check(trx);
            repository.save(trx);

        }

    }

    public abstract void check(Transaction trx);

    public abstract Transaction addDetails(TransactionDto dto);


    private void setGeneralData(TransactionDto dto, Transaction trx) {
        var user= ContextUtil.getUser();
        trx.setUser(user);
        trx.setType(dto.getType());
        trx.setAmount(dto.getAmount());
    }

    private BigDecimal getCurrentBalance(User user) {
        repository.getTransactionSums(user.getMobileNumber());
        return BigDecimal.ZERO;
    }


    public abstract TransactionType getType();
}
