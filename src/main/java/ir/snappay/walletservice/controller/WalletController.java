package ir.snappay.walletservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.snappay.walletservice.dto.*;
import ir.snappay.walletservice.entity.Transaction;
import ir.snappay.walletservice.repository.projection.TransactionSumDto;
import ir.snappay.walletservice.service.WalletService;
import ir.snappay.walletservice.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("v1/transfer")
    @Operation(summary="user can send money from it's wallet to another wallet by mobile number.")
    public void transfer(@Valid @RequestBody TransferDto dto){
        walletService.transfer(dto);
    }

    @GetMapping("v1/balance")
    @Operation(summary="get user balance")
    public ResponseObject<WalletResponse> getWalletBalance(){
        return ResponseUtil.createResponse(walletService.getBalance());
    }

    @GetMapping("v1/total-by-period")
    @Operation(summary="user can get total of each transaction type(withdraw,deposit,send,receive) based on time period of days.")
    public ResponseObject<TransactionSumDto> getTotalByTypeAndFromTime(@RequestParam(defaultValue = "10") long period){
        return ResponseUtil.createResponse(walletService.getTotalByTimePeriod(period));
    }

    @GetMapping("v1/all-transactions")
    @Operation(summary="get all transactions of the user.")
    public ResponseObject<List<TransactionResponse>> getAll(){
        return ResponseUtil.createResponse(walletService.getAll());
    }

}
