package ir.snappay.walletservice.service.transaction;

import ir.snappay.walletservice.dto.TransactionDto;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.entity.User;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.exception.CustomException;
import ir.snappay.walletservice.exception.ErrorCode;
import ir.snappay.walletservice.repository.TransactionRepository;
import ir.snappay.walletservice.service.UserService;
import ir.snappay.walletservice.util.ContextUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public abstract class TransactionService {
    private final TransactionRepository repository;
    private final TotalBalanceCalculator totalBalanceCalculator;
    private final UserService userService;

    public TransactionService(TransactionRepository repository, TotalBalanceCalculator totalBalanceCalculator, UserService userService) {
        this.repository = repository;
        this.totalBalanceCalculator = totalBalanceCalculator;
        this.userService = userService;
    }

    @Transactional
    public Transaction perform(TransactionDto dto){
        Transaction trx= addDetails(dto);
        setGeneralData(dto,trx);
        return persistTransaction(trx);
    }

    @Transactional
    public Transaction perform(TransactionDto dto,User user){
        Transaction trx= addDetails(dto);
        trx.setUser(user);
        setGeneralData(dto,trx);
        return persistTransaction(trx);
    }

    private Transaction persistTransaction(Transaction trx) {
        synchronized (this){
            BigDecimal balance  = getCurrentBalance(trx.getUser());
            check(trx,balance);
            setCurrentBalance(trx,balance);
            return repository.save(trx);

        }

    }

    protected abstract void setCurrentBalance(Transaction trx, BigDecimal balance);

    public abstract void check(Transaction trx, BigDecimal balance);

    public abstract Transaction addDetails(TransactionDto dto);


    private void setGeneralData(TransactionDto dto, Transaction trx) {
        if(trx.getUser()==null){
            var user= ContextUtil.getUser();
            trx.setUser(user);
        }
        trx.setType(dto.getType());
        trx.setAmount(dto.getAmount());
    }

    private BigDecimal getCurrentBalance(User user) {
        var sumRes =repository.getTransactionSums(user.getMobileNumber());
        return totalBalanceCalculator.calculate(sumRes);
    }

    public User getUserByMobileNumber(String receiverMobileNumber) {
        return userService.findByMobileNumber(receiverMobileNumber).orElseThrow(()->new CustomException(ErrorCode.RECEIVER_USER_NOT_FOUND));
    }


    public abstract TransactionType getType();
}
