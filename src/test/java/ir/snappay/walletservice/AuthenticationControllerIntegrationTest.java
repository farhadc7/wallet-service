package ir.snappay.walletservice;

import ir.snappay.walletservice.dto.*;
import ir.snappay.walletservice.testUtils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthenticationControllerIntegrationTest {

    @Autowired
    private TestUtils testUtils;

    @LocalServerPort
    private int port;
    private String host = "http://localhost";
    private String signup = "/auth/v1/signup";
    private String login = "/auth/v1/login";

    String signupAddress;
    String loginAddress ;

    @BeforeEach
    public void setup(){
         signupAddress = host + ":" + port + signup;
         loginAddress = host + ":" + port + login;

    }


    @Test
    public void testSuccessfulSignup() {

        RegisterUserDto dto = new RegisterUserDto("09127293015", "Aa@886622");
        ResponseEntity<ResponseObject> res = testUtils.callApi(signupAddress,dto);

        UserDto userDto = testUtils.getResult(Objects.requireNonNull(res.getBody()).getResponse(), UserDto.class);

        assertEquals(HttpStatusCode.valueOf(200), res.getStatusCode());
        assertEquals(dto.getMobileNumber(), userDto.getMobileNumber());
    }

    @Test
    public void testNotValidMobileNumberSignup() {
        RegisterUserDto dto = new RegisterUserDto("0912729", "Aa@886622");
        ResponseEntity<ResponseObject> res = testUtils.callApi(signupAddress,dto);

        assertEquals(HttpStatusCode.valueOf(400), res.getStatusCode());
        assertTrue(res.getBody().getError().getMessage().contains("mobileNumber not valid"));

    }

    @Test
    public void testSuccessfulLogin(){
        RegisterUserDto dto = new RegisterUserDto("09127293015", "Aa@886622");
        LoginUserDto login= new LoginUserDto("09127293015", "Aa@886622");
        testUtils.callApi(signupAddress,dto);

       var res= testUtils.callApi(loginAddress,login);

        LoginResponse loginResponse = testUtils.getResult(Objects.requireNonNull(res.getBody()).getResponse(), LoginResponse.class);
        assertNotNull(loginResponse.getToken());
    }

    @Test
    public void testNotValidPasswordLogin(){
        RegisterUserDto dto = new RegisterUserDto("09127293015", "Aa@886622");
        LoginUserDto login= new LoginUserDto("09127293015", "Aa@8866");
        testUtils.callApi(signupAddress,dto);

        var res= testUtils.callApi(loginAddress,login);

        assertEquals(HttpStatusCode.valueOf(400), res.getStatusCode());
        assertTrue(res.getBody().getError().getMessage().contains("The username or password is incorrect"));
        assertEquals(401,res.getBody().getError().getCode());
    }



}
