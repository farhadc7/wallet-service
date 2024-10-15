package ir.snappay.walletservice.repository;

import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.repository.projection.TransactionSumDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {



    @Query("SELECT new ir.snappay.walletservice.repository.projection.TransactionSumDto(" +
            "SUM(CASE WHEN t.type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS depositSum, " +
            "SUM(CASE WHEN t.type = 'WITHDRAW' THEN t.amount ELSE 0 END) AS withdrawSum, " +
            "SUM(CASE WHEN t.type = 'SEND' THEN t.amount ELSE 0 END) AS sendSum, " +
            "SUM(CASE WHEN t.type = 'RECEIVE' THEN t.amount ELSE 0 END) AS receiveSum) " +
            "FROM Transaction t WHERE t.user.mobileNumber = :mobileNumber")
    TransactionSumDto getTransactionSums(@Param("mobileNumber") String mobileNumber);

    @Query("SELECT new ir.snappay.walletservice.repository.projection.TransactionSumDto(" +
            "SUM(CASE WHEN t.type = 'DEPOSIT' THEN t.amount ELSE 0 END) AS depositSum, " +
            "SUM(CASE WHEN t.type = 'WITHDRAW' THEN t.amount ELSE 0 END) AS withdrawSum, " +
            "SUM(CASE WHEN t.type = 'SEND' THEN t.amount ELSE 0 END) AS sendSum, " +
            "SUM(CASE WHEN t.type = 'RECEIVE' THEN t.amount ELSE 0 END) AS receiveSum) " +
            "FROM Transaction t WHERE t.user.mobileNumber = :mobileNumber and t.createdDate > :date")
    TransactionSumDto getTransactionSumsAfterDate(@Param("mobileNumber") String mobileNumber,@Param("date") LocalDateTime date);

    List<Transaction> findAllByUser_MobileNumberOrderByCreatedDateDesc(String mobileNumber);
}
