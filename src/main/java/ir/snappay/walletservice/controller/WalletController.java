package ir.snappay.walletservice.controller;

import ir.snappay.walletservice.dto.DepositTransactionDto;
import ir.snappay.walletservice.dto.SendTransactionDto;
import ir.snappay.walletservice.dto.WalletResponse;
import ir.snappay.walletservice.dto.WithdrawTransactionDto;
import ir.snappay.walletservice.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("v1/deposit")
    public void deposit(@Valid @RequestBody DepositTransactionDto dto){
         walletService.deposit(dto);
    }

    @PostMapping("v1/withdraw")
    public void withdraw(@Valid @RequestBody WithdrawTransactionDto dto){
         walletService.withdraw(dto);
    }

    @PostMapping("v1/send")
    public void send(@Valid @RequestBody SendTransactionDto dto){
        walletService.send(dto);
    }
}
