package ir.snappay.walletservice.testUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.snappay.walletservice.dto.LoginUserDto;
import ir.snappay.walletservice.dto.RegisterUserDto;
import ir.snappay.walletservice.dto.ResponseObject;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
public class TestUtils {

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<ResponseObject> signup(String address,RegisterUserDto dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterUserDto> req = new HttpEntity<>(dto, headers);
        ResponseEntity<ResponseObject> resp = testRestTemplate.exchange(address, HttpMethod.POST, req, ResponseObject.class);
        return resp;
    }

    public ResponseEntity<ResponseObject> login(String loginAddress, LoginUserDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginUserDto> req = new HttpEntity<>(dto, headers);

        ResponseEntity<ResponseObject> resp = testRestTemplate.exchange(loginAddress, HttpMethod.POST, req, ResponseObject.class);
        return resp;

    }

    public <T> T getResult(Object o, Class<T> t) {
        return mapper.convertValue(o, t);
    }
}
