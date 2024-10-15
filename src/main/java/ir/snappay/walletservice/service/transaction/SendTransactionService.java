package ir.snappay.walletservice.service.transaction;

import ir.snappay.walletservice.dto.ReceiveTransactionDto;
import ir.snappay.walletservice.dto.TransferDto;
import ir.snappay.walletservice.dto.TransactionDto;
import ir.snappay.walletservice.entity.SendTransaction;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.exception.CustomException;
import ir.snappay.walletservice.exception.ErrorCode;
import ir.snappay.walletservice.mapper.TransactionMapper;
import ir.snappay.walletservice.repository.TransactionRepository;
import ir.snappay.walletservice.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public  class SendTransactionService extends TransactionService{
    private final ReceiveTransactionService receiveTransactionService;


    public SendTransactionService(TransactionRepository repository,
                                  TotalBalanceCalculator totalBalanceCalculator,
                                  UserService userService,
                                  ReceiveTransactionService receiveTransactionService,
                                  TransactionMapper mapper) {
        super(repository, totalBalanceCalculator, userService, mapper);
        this.receiveTransactionService = receiveTransactionService;
    }

    @Override
    public Transaction perform(TransactionDto dto) {
        TransferDto sd= (TransferDto)dto;
        var trx = super.perform(dto);
        receiveTransactionService.perform(
                new ReceiveTransactionDto(dto.getAmount(),
                        TransactionType.RECEIVE,
                        trx.getUser().getMobileNumber())
                ,
                getUserByMobileNumber(sd.getReceiverMobileNumber()));
        return trx;
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
        TransferDto d= (TransferDto)dto;
        SendTransaction trx= new SendTransaction();
        trx.setReceiver(getUserByMobileNumber(d.getReceiverMobileNumber()));
        return trx;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.SEND;
    }


}
