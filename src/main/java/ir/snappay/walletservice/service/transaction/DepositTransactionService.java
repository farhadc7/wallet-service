package ir.snappay.walletservice.service.transaction;

import ir.snappay.walletservice.dto.DepositTransactionDto;
import ir.snappay.walletservice.dto.TransactionDto;
import ir.snappay.walletservice.entity.DepositTransaction;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public  class DepositTransactionService extends TransactionService{


    public DepositTransactionService(TransactionRepository repository) {
        super(repository);
    }

    @Override
    public void check(Transaction trx) {
        /*deposit no needs to check*/
    }


    @Override
    public Transaction addDetails(TransactionDto dto) {
        DepositTransactionDto d= (DepositTransactionDto)dto;
        DepositTransaction trx= new DepositTransaction();
        trx.setVerificationCode(d.getVerificationCode());
        trx.setIbanNumber(d.getIbanNumber());
        return trx;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.DEPOSIT;
    }

}
