package ir.snappay.walletservice;


import ir.snappay.walletservice.dto.*;
import ir.snappay.walletservice.testUtils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class WalletControllerIntegrationTest {
    @Autowired
    private TestUtils testUtils;

    @LocalServerPort
    private int port;
    private String host = "http://localhost";
    private String depositUri = "/wallet/v1/deposit";
    private String balanceUri = "/wallet/v1/balance";
    private String withdrawUri = "/wallet/v1/withdraw";

    String token;
    String depositAddress;
    String balanceAddress;

    DepositTransactionDto depositDto;
    WithdrawTransactionDto withdrawDto;
    RegisterUserDto userDto;


    @BeforeEach
    public void setup() {
        depositAddress = host + ":" + port + depositUri;
        balanceAddress = host + ":" + port + balanceUri;

        userDto = new RegisterUserDto("09127293015", "Aa@886622");
        depositDto = new DepositTransactionDto(new BigDecimal(50), "1234", "100");

        withdrawDto = new WithdrawTransactionDto();


    }

    @Test
    public void testSuccessfulDeposit() {
        //get token
        token = testUtils.getToken(port,userDto);

        // deposit
        deposit(token, depositDto);

        //get balance:
        var res = testUtils.callGetApi(balanceAddress, token);
        WalletResponse resp = testUtils.getResult(res.getBody().getResponse(), WalletResponse.class);

        assertEquals(2000, resp.getBalance().longValue());
    }

    @Test
    public void testNotEnoughAmountDeposit() {
        token = testUtils.getToken(port,userDto);

        var res = deposit(token, depositDto);
        assertEquals(400, res.getStatusCode().value());
        assertTrue(res.getBody().getError().getMessage().contains("amount not valid - minimum value is 1000."));
    }

    @Test
    public void testSuccessfulWithdraw() {
        token = testUtils.getToken(port,userDto);
        deposit(token, depositDto);

        var res = testUtils.callGetApi(balanceAddress, token);

        WalletResponse resp = testUtils.getResult(res.getBody().getResponse(), WalletResponse.class);

        assertEquals(2000, resp.getBalance().longValue());
    }

    private ResponseEntity<ResponseObject> deposit(String token, DepositTransactionDto depositDto) {
        var res = testUtils.callApi(depositAddress, depositDto, token);
        return res;
    }


}
