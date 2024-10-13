package ir.snappay.walletservice.service.transaction;

import ir.snappay.walletservice.dto.TransactionDto;
import ir.snappay.walletservice.entity.DepositTransaction;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.entity.User;
import ir.snappay.walletservice.enums.TransactionStatus;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.repository.TransactionRepository;
import ir.snappay.walletservice.util.ContextUtil;
import org.springframework.stereotype.Service;

@Service
public abstract class TransactionService<D extends TransactionDto> {
    public final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void perform(D dto){
        Transaction trx= addDetails(dto);
        setGeneralData(dto,trx);
        persistTransaction(trx);

    }

    private void persistTransaction(Transaction trx) {
        synchronized (this){
            getCurrentBalance(trx.getUser());
            check(trx);

        }

    }

    public abstract void check(Transaction trx);

    public abstract Transaction addDetails(D d);


    private void setGeneralData(D dto, Transaction trx) {
        var user= ContextUtil.getUser();
        trx.setUser(user);
        trx.setType(dto.getType());
        trx.setAmount(dto.getAmount());
    }

    private void getCurrentBalance(User user) {

    }
}
