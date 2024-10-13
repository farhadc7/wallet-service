package ir.snappay.walletservice.service;

import ir.snappay.walletservice.dto.LoginResponse;
import ir.snappay.walletservice.dto.LoginUserDto;
import ir.snappay.walletservice.dto.RegisterUserDto;
import ir.snappay.walletservice.dto.UserDto;
import ir.snappay.walletservice.entity.User;
import ir.snappay.walletservice.enums.RoleEnum;
import ir.snappay.walletservice.exception.CustomException;
import ir.snappay.walletservice.exception.ErrorCode;
import ir.snappay.walletservice.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthenticationService(UserService userService, JwtService jwtService, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserDto signup(RegisterUserDto dto){
        User user= new User(dto.getMobileNumber(),passwordEncoder.encode(dto.getPassword()), RoleEnum.USER);
        return userService.save(user);
    }

    public LoginResponse login(LoginUserDto dto){
        User authenticatedUser  = authenticate(dto);

        String jwtToken= jwtService.generateToken(authenticatedUser);
        return  new LoginResponse(jwtToken, jwtService.getExpirationTime());


    }

    private User authenticate(LoginUserDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getMobileNumber(),dto.getPassword())
        );
        return userService.findByMobileNumber(dto.getMobileNumber())
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
