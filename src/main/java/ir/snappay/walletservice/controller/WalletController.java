package ir.snappay.walletservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.snappay.walletservice.dto.*;
import ir.snappay.walletservice.enums.TransactionType;
import ir.snappay.walletservice.repository.projection.TransactionSumDto;
import ir.snappay.walletservice.service.WalletService;
import ir.snappay.walletservice.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@Tag(name = "wallet api",description = "wallet operations.")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("v1/deposit")
    @Operation(summary="deposit to wallet")
    public void deposit(@Valid @RequestBody DepositTransactionDto dto){
         walletService.deposit(dto);
    }

    @PostMapping("v1/withdraw")
    @Operation(summary="withdraw from wallet")
    public void withdraw(@Valid @RequestBody WithdrawTransactionDto dto){
         walletService.withdraw(dto);
    }

    @PostMapping("v1/send")
    @Operation(summary="user can send money from it's wallet to another wallet by mobile number.")
    public void send(@Valid @RequestBody SendTransactionDto dto){
        walletService.send(dto);
    }

    @GetMapping("v1/balance")
    @Operation(summary="get user balance")
    public ResponseObject<WalletResponse> getWalletBalance(){
        return ResponseUtil.createResponse(walletService.getBalance());
    }

    @GetMapping("v1/total-by-period")
    @Operation(summary="user can get total of each type based on time period of days.")
    public ResponseObject<TransactionSumDto> getTotalByTypeAndFromTime(@RequestParam(defaultValue = "10") long period){
        return ResponseUtil.createResponse(walletService.getTotalByTimePeriod(period));
    }

}
