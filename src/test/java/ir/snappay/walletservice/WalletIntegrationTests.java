package ir.snappay.walletservice;


import ir.snappay.walletservice.dto.RegisterUserDto;
import ir.snappay.walletservice.service.AuthenticationService;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletServiceApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class WalletIntegrationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
private MockMvc mockMvc;
    @BeforeEach
    public void setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void userSignupSuccessfullyTest(){
        ServletContext w= webApplicationContext.getServletContext();
        RegisterUserDto dto= new RegisterUserDto("09127293017","Ss@885522");


    }
}
