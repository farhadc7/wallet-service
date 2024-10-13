package ir.snappay.walletservice.service.transaction;

import ir.snappay.walletservice.dto.TransactionDto;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.entity.User;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.repository.TransactionRepository;
import ir.snappay.walletservice.util.ContextUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public abstract class TransactionService {
    private final TransactionRepository repository;
    private final TotalBalanceCalculator totalBalanceCalculator;

    public TransactionService(TransactionRepository repository, TotalBalanceCalculator totalBalanceCalculator) {
        this.repository = repository;
        this.totalBalanceCalculator = totalBalanceCalculator;
    }

    public void perform(TransactionDto dto){
        Transaction trx= addDetails(dto);
        setGeneralData(dto,trx);
        persistTransaction(trx);

    }

    private void persistTransaction(Transaction trx) {
        synchronized (this){
            BigDecimal balance  = getCurrentBalance(trx.getUser());
            check(trx,balance);
            setCurrentBalance(trx,balance);
            repository.save(trx);

        }

    }

    protected abstract void setCurrentBalance(Transaction trx, BigDecimal balance);

    public abstract void check(Transaction trx, BigDecimal balance);

    public abstract Transaction addDetails(TransactionDto dto);


    private void setGeneralData(TransactionDto dto, Transaction trx) {
        var user= ContextUtil.getUser();
        trx.setUser(user);
        trx.setType(dto.getType());
        trx.setAmount(dto.getAmount());
    }

    private BigDecimal getCurrentBalance(User user) {
        var sumRes =repository.getTransactionSums(user.getMobileNumber());
        return totalBalanceCalculator.calculate(sumRes);
    }


    public abstract TransactionType getType();
}
