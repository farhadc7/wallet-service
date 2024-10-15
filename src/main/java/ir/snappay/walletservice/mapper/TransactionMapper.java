package ir.snappay.walletservice.mapper;

import ir.snappay.walletservice.dto.TransactionResponse;
import ir.snappay.walletservice.dto.UserDto;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public class TransactionMapper implements GeneralMapper<TransactionResponse, Transaction> {


    @Override
    public TransactionResponse entityToDto(Transaction t) {
        return new TransactionResponse(t.getCurrentBalance(),
                t.getAmount(),t.getStatus(),t.getType(),t.getCreatedDate());

    }

    @Override
    public Transaction dtoToEntity(TransactionResponse transactionResponse) {
        return null;
    }

    @Override
    public List<TransactionResponse> entityToDtoList(List<Transaction> e) {
        return e.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<Transaction> dtoToEntityList(List<TransactionResponse> d) {
        return null;
    }
}
