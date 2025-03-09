
package com.example.ing.financial.controller;

import com.example.ing.financial.entity.TransactionIng;
import com.example.ing.financial.service.TransactionIngService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionIngController {

    private final TransactionIngService transactionIngService;

    public TransactionIngController(TransactionIngService transactionIngService) {
        this.transactionIngService = transactionIngService;
    }

    @GetMapping
    public List<TransactionIng> getAllTransactions() {
        return transactionIngService.getAllTransactions();
    }

    @PostMapping
    public TransactionIng addTransaction(@RequestBody TransactionIng transaction) {
        return transactionIngService.addTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        return transactionIngService.deleteTransaction(id);
    }

    @GetMapping("/paged")
    public Page<TransactionIng> getTransactionsPaged(Pageable pageable) {
        return transactionIngService.getTransactionsPaged(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionIng> getTransactionById(@PathVariable Long id) {
        return transactionIngService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionIng> updateTransaction(@PathVariable Long id, @RequestBody TransactionIng updatedTransaction) {
        return transactionIngService.updateTransaction(id, updatedTransaction)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}