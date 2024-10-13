package ir.snappay.walletservice.mapper;

import java.util.List;

public interface GeneralMapper<D,E> {
    D entityToDto(E e);
    E dtoToEntity(D d);

    List<D> entityToDtoList(List<E> e);
    List<E> dtoToEntityList(List<D> d);

}
