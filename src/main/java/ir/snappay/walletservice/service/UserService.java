package ir.snappay.walletservice.service;

import ir.snappay.walletservice.dto.UserDto;
import ir.snappay.walletservice.entity.User;
import ir.snappay.walletservice.mapper.UserMapper;
import ir.snappay.walletservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;


    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDto save(User user){
        return mapper.entityToDto(repository.save(user));
    }

    public Optional<User> findByMobileNumber(String mobileNumber){
        return repository.findByMobileNumber(mobileNumber);
    }

}
