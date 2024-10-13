package ir.snappay.walletservice.controller;

import ir.snappay.walletservice.dto.DepositTransactionDto;
import ir.snappay.walletservice.dto.WalletResponse;
import ir.snappay.walletservice.service.WalletService;
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
    public WalletResponse deposit(@RequestBody DepositTransactionDto dto){
        return walletService.deposit(dto);
    }
}
