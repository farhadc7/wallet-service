package ir.snappay.walletservice.service.transaction;

import ir.snappay.walletservice.dto.ReceiveTransactionDto;
import ir.snappay.walletservice.dto.TransactionDto;
import ir.snappay.walletservice.entity.ReceiveTransaction;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.mapper.TransactionMapper;
import ir.snappay.walletservice.repository.TransactionRepository;
import ir.snappay.walletservice.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public  class ReceiveTransactionService extends TransactionService{

    private final UserService userService;


    public ReceiveTransactionService(TransactionRepository repository,
                                     TotalBalanceCalculator totalBalanceCalculator,
                                     UserService userService,
                                     TransactionMapper mapper) {
        super(repository, totalBalanceCalculator, userService, mapper);
        this.userService = userService;
    }


    @Override
    protected void setCurrentBalance(Transaction trx, BigDecimal balance) {
        trx.setCurrentBalance(balance.add(trx.getAmount()));

    }

    @Override
    public void check(Transaction trx, BigDecimal balance) {
        /*no need check*/
    }


    @Override
    public Transaction addDetails(TransactionDto dto) {
        ReceiveTransactionDto d= (ReceiveTransactionDto) dto;
        ReceiveTransaction trx= new ReceiveTransaction();
        trx.setSender(getUserByMobileNumber(d.getSenderMobileNumber()));
        return trx;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.RECEIVE;
    }


}
