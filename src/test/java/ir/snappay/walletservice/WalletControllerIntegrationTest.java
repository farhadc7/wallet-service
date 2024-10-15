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
    private String transferUri = "/wallet/v1/transfer";
    private String allTransactionsUri = "/wallet/v1/all";

//    String token;
    String depositAddress;
    String balanceAddress;
    String withdrawAddress;
    String transferAddress;
    String allTransactionsAddress;

    DepositTransactionDto depositDto;
    WithdrawTransactionDto withdrawDto;
    RegisterUserDto userDto;
    RegisterUserDto receiverDto;



    @BeforeEach
    public void setup() {
        depositAddress = host + ":" + port + depositUri;
        balanceAddress = host + ":" + port + balanceUri;
        withdrawAddress = host + ":" + port + withdrawUri;
        transferAddress = host + ":" + port + transferUri;
        allTransactionsAddress = host + ":" + port + allTransactionsUri;

        userDto = new RegisterUserDto("09127293015", "Aa@886622");
        receiverDto = new RegisterUserDto("09127293018", "Ha@885522");
        depositDto = new DepositTransactionDto(new BigDecimal(2000), "1234", "100");

        withdrawDto = new WithdrawTransactionDto(BigDecimal.valueOf(1000), "123456");


    }

    @Test
    public void testSuccessfulDeposit() {
        //get token
        String token = testUtils.getToken(port, userDto);

        // deposit
        deposit(token, depositDto);

        //get balance:
        var balance = getBalance(token);

        assertEquals(BigDecimal.valueOf(2000), balance);
    }

    @Test
    public void testNotEnoughAmountDeposit() {
       String token = testUtils.getToken(port, userDto);

        var res = deposit(token,
                new DepositTransactionDto(BigDecimal.valueOf(50), "1234", "324lkj23"));
        assertEquals(400, res.getStatusCode().value());
        assertTrue(res.getBody().getError().getMessage().contains("amount not valid - minimum value is 1000."));
    }

    @Test
    public void testSuccessfulWithdraw() {
        String  token = testUtils.getToken(port, userDto);
        deposit(token, depositDto);

        testUtils.callApi(withdrawAddress, withdrawDto, token);

        var balance = getBalance(token);

        assertEquals(BigDecimal.valueOf(1000),balance);
    }

    @Test
    public void testWithdraw_RequestedAmountGreaterThanBalance() {
        String token = testUtils.getToken(port, userDto);
        deposit(token, depositDto);

        var withdrawRes = testUtils.callApi(withdrawAddress,
                new WithdrawTransactionDto(BigDecimal.valueOf(5000), "234")
                , token);

        var balance= getBalance(token);

        assertEquals(4001, withdrawRes.getBody().getError().getCode());
        assertTrue(withdrawRes.getBody().getError().getMessage().contains("balance is not enough."));
        assertEquals(BigDecimal.valueOf(2000),balance);
    }

    @Test
    public void TransferMoney() {
        String senderTokenn = testUtils.getToken(port, userDto);
        String receiverToken = testUtils.getToken(port, receiverDto);

        deposit(senderTokenn, new DepositTransactionDto(BigDecimal.valueOf(50000), "12334234234", "verify123"));
        testUtils.callApi(transferAddress, new TransferDto(BigDecimal.valueOf(20000), receiverDto.getMobileNumber()),senderTokenn);

        var senderBalance = getBalance(senderTokenn);
        var receiverBalance = getBalance(receiverToken);


        assertEquals(BigDecimal.valueOf(30000),senderBalance);
        assertEquals(BigDecimal.valueOf(20000),receiverBalance);


    }


    private ResponseEntity<ResponseObject> deposit(String token, DepositTransactionDto depositDto) {
        var res = testUtils.callApi(depositAddress, depositDto, token);
        return res;
    }

    private BigDecimal getBalance(String inputToken) {
        var res = testUtils.callGetApi(balanceAddress, inputToken);
        return testUtils.getResult(res.getBody().getResponse(), WalletResponse.class).getBalance();
    }

}
