package ir.snappay.walletservice.service.transaction;

import ir.snappay.walletservice.dto.TransactionDto;
import ir.snappay.walletservice.dto.WithdrawTransactionDto;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.entity.WithdrawTransaction;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.exception.CustomException;
import ir.snappay.walletservice.exception.ErrorCode;
import ir.snappay.walletservice.repository.TransactionRepository;
import ir.snappay.walletservice.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public  class WithdrawTransactionService extends TransactionService{


    public WithdrawTransactionService(TransactionRepository repository, TotalBalanceCalculator totalBalanceCalculator, UserService userService) {
        super(repository, totalBalanceCalculator, userService);
    }

    @Override
    protected void setCurrentBalance(Transaction trx, BigDecimal balance) {
        trx.setCurrentBalance(balance.subtract(trx.getAmount()));

    }

    @Override
    public void check(Transaction trx, BigDecimal balance) {
        if(balance.compareTo(trx.getAmount())<0){
            throw new CustomException(ErrorCode.NOT_ENOUGH_BALANCE);
        }
    }


    @Override
    public Transaction addDetails(TransactionDto dto) {
        WithdrawTransactionDto d= (WithdrawTransactionDto)dto;
        WithdrawTransaction trx= new WithdrawTransaction();
        trx.setBankAccount(d.getAccountNumber());
        return trx;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WITHDRAW;
    }

}
