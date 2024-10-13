package ir.snappay.walletservice.repository;

import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.repository.projection.SumByTransactionType;
import ir.snappay.walletservice.repository.projection.TransactionSumDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {



    @Query("SELECT new ir.snappay.walletservice.repository.projection.TransactionSumDto(SUM(CASE WHEN t.type = 'DEPOSIT' THEN t.amount ELSE 0 END), " +
            "SUM(CASE WHEN t.type = 'WITHDRAW' THEN t.amount ELSE 0 END), " +
            "SUM(CASE WHEN t.type = 'SEND' THEN t.amount ELSE 0 END), " +
            "SUM(CASE WHEN t.type = 'RECEIVE' THEN t.amount ELSE 0 END)) " +
            "FROM Transaction t")
    TransactionSumDto getTransactionSums();
}
