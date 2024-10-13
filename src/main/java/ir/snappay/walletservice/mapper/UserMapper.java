package ir.snappay.walletservice.mapper;

import ir.snappay.walletservice.dto.UserDto;
import ir.snappay.walletservice.entity.User;
import org.mapstruct.Mapper;


@Mapper
public interface UserMapper extends GeneralMapper<UserDto, User>{

}
