package ir.snappay.walletservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.snappay.walletservice.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    @LocalServerPort
    private int port;
    private String host = "http://localhost";
    private String signup = "/auth/v1/signup";
    private String login = "/auth/v1/login";


    @Test
    public void testSuccessfulSignup() {

        RegisterUserDto dto = new RegisterUserDto("09127293015", "Aa@886622");
        ResponseEntity<ResponseObject> res = signup(dto);

        UserDto userDto = getResult(Objects.requireNonNull(res.getBody()).getResponse(), UserDto.class);

        assertEquals(HttpStatusCode.valueOf(200), res.getStatusCode());
        assertEquals(dto.getMobileNumber(), userDto.getMobileNumber());
    }

    @Test
    public void testNotValidMobileNumberSignup() {
        RegisterUserDto dto = new RegisterUserDto("0912729", "Aa@886622");
        ResponseEntity<ResponseObject> res = signup(dto);

        assertEquals(HttpStatusCode.valueOf(400), res.getStatusCode());
        assertTrue(res.getBody().getError().getMessage().contains("mobileNumber not valid"));

    }

    @Test
    public void testSuccessfulLogin(){
        RegisterUserDto dto = new RegisterUserDto("09127293015", "Aa@886622");
        LoginUserDto login= new LoginUserDto("09127293015", "Aa@886622");
        signup(dto);

       var res= login(login);

        LoginResponse loginResponse = getResult(Objects.requireNonNull(res.getBody()).getResponse(), LoginResponse.class);
        assertNotNull(loginResponse.getToken());
    }

    @Test
    public void testNotValidPasswordLogin(){
        RegisterUserDto dto = new RegisterUserDto("09127293015", "Aa@886622");
        LoginUserDto login= new LoginUserDto("09127293015", "Aa@8866");
        signup(dto);

        var res= login(login);

        assertEquals(HttpStatusCode.valueOf(400), res.getStatusCode());
        assertTrue(res.getBody().getError().getMessage().contains("The username or password is incorrect"));
        assertEquals(401,res.getBody().getError().getCode());
    }






    private ResponseEntity<ResponseObject> signup(RegisterUserDto dto) {
        String address = host + ":" + port + signup;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterUserDto> req = new HttpEntity<>(dto, headers);
        ResponseEntity<ResponseObject> resp = testRestTemplate.exchange(address, HttpMethod.POST, req, ResponseObject.class);
        return resp;
    }

    private ResponseEntity<ResponseObject> login(LoginUserDto dto) {
        String address = host + ":" + port + login;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginUserDto> req = new HttpEntity<>(dto, headers);

            ResponseEntity<ResponseObject> resp = testRestTemplate.exchange(address, HttpMethod.POST, req, ResponseObject.class);
            return resp;

    }

    private <T> T getResult(Object o, Class<T> t) {
        return mapper.convertValue(o, t);
    }
}
