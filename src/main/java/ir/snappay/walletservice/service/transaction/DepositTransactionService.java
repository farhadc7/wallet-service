package ir.snappay.walletservice.service.transaction;

import ir.snappay.walletservice.dto.DepositTransactionDto;
import ir.snappay.walletservice.entity.DepositTransaction;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public  class DepositTransactionService extends TransactionService<DepositTransactionDto>{


    public DepositTransactionService(TransactionRepository repository) {
        super(repository);
    }

    @Override
    public Transaction addDetails(DepositTransactionDto dto) {
        DepositTransaction trx= new DepositTransaction();
        trx.setVerificationCode(dto.getVerificationCode());
        trx.setIbanNumber(dto.getIbanNumber());
        return trx;
    }

}
