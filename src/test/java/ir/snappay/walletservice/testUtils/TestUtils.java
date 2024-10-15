package ir.snappay.walletservice.testUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.snappay.walletservice.dto.LoginResponse;
import ir.snappay.walletservice.dto.LoginUserDto;
import ir.snappay.walletservice.dto.RegisterUserDto;
import ir.snappay.walletservice.dto.ResponseObject;
import ir.snappay.walletservice.entity.Transaction;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestUtils {

    private String host = "http://localhost";
    private String signupUri = "/auth/v1/signup";
    private String loginUri = "/auth/v1/login";


    TestRestTemplate testRestTemplate = new TestRestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<ResponseObject> callApi(String address,Object dto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterUserDto> req = new HttpEntity(dto, headers);
        ResponseEntity<ResponseObject> resp = testRestTemplate.exchange(address, HttpMethod.POST, req, ResponseObject.class);
        return resp;
    }
    public ResponseEntity<ResponseObject> callApi(String address,Object dto,String authorization){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authorization);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterUserDto> req = new HttpEntity(dto, headers);
        ResponseEntity<ResponseObject> resp = testRestTemplate.exchange(address, HttpMethod.POST, req, ResponseObject.class);
        return resp;
    }
    public ResponseEntity<ResponseObject> callGetApi(String address,String authorization){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authorization);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterUserDto> req = new HttpEntity( headers);
        ResponseEntity<ResponseObject> resp = testRestTemplate.exchange(address, HttpMethod.GET, req, ResponseObject.class);
        return resp;
    }


    public String getToken(int port,RegisterUserDto userDto){
        callApi(getAddress(port,signupUri),userDto);
        var res =callApi(getAddress(port,loginUri),new LoginUserDto(getSampleRegisterDto().getMobileNumber(),getSampleRegisterDto().getPassword()));
        var loginRes= getResult(res.getBody().getResponse(), LoginResponse.class);
        return loginRes.getToken();
    }

    private String getAddress(int port, String uri){
        return  host + ":" + port + uri;
    }

    private RegisterUserDto getSampleRegisterDto(){
        RegisterUserDto dto = new RegisterUserDto("09127293015", "Aa@886622");
        return dto;
    }
    public <T> T getResult(Object o, Class<T> t) {
        return mapper.convertValue(o, t);
    }

    public List<Transaction> getResultList(Object response, TypeReference<List<Transaction>> listTypeReference) {
        return mapper.convertValue(response,listTypeReference);
    }
}
