package com.ivanch07.finanzas.controller;

import com.ivanch07.finanzas.dto.transactionDto.TransactionRequestDto;
import com.ivanch07.finanzas.dto.transactionDto.TransactionResponseDto;
import com.ivanch07.finanzas.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> getMyTransaction(){
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDto> createTransaction(
            @RequestBody TransactionRequestDto transactionRequestDto){
        return ResponseEntity.ok(transactionService.createTransaction(
                transactionRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> updateTransaction(@PathVariable Long id,
                                                         @RequestBody TransactionRequestDto transactionRequestDto){
        return ResponseEntity.ok(transactionService.updateTransaction(id, transactionRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
