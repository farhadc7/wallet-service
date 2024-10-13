package ir.snappay.walletservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.snappay.walletservice.dto.LoginResponse;
import ir.snappay.walletservice.dto.LoginUserDto;
import ir.snappay.walletservice.dto.RegisterUserDto;
import ir.snappay.walletservice.dto.UserDto;
import ir.snappay.walletservice.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@Tag(name= "authentication api", description= "user this api to signup and login users.")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("v1/signup")
    @Operation(summary="register a new user to wallet service")
    public UserDto register(@Valid @RequestBody RegisterUserDto dto){
        return authenticationService.signup(dto);
    }

    @PostMapping("v1/login")
    @Operation(summary ="using this api, users can login to wallet service and access it's functionalities" )
    public LoginResponse login(@Valid @RequestBody LoginUserDto dto){
        return authenticationService.login(dto);
    }
}
