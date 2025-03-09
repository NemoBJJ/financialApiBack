package com.example.ing.financial.service;

import com.example.ing.financial.entity.TransactionIng;
import com.example.ing.financial.repository.TransactionIngRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionIngService {

    private final TransactionIngRepository transactionIngRepository;

    public TransactionIngService(TransactionIngRepository transactionIngRepository) {
        this.transactionIngRepository = transactionIngRepository;
    }

    // Operações CRUD básicas
    public List<TransactionIng> getAllTransactions() {
        return transactionIngRepository.findAll();
    }

    public TransactionIng addTransaction(TransactionIng transaction) {
        return transactionIngRepository.save(transaction);
    }

    public String deleteTransaction(Long id) {
        if (transactionIngRepository.existsById(id)) {
            transactionIngRepository.deleteById(id);
            return "Transaction removed successfully!";
        }
        return "Transaction not found!";
    }

    public Page<TransactionIng> getTransactionsPaged(Pageable pageable) {
        return transactionIngRepository.findAll(pageable);
    }

    public Optional<TransactionIng> getTransactionById(Long id) {
        return transactionIngRepository.findById(id);
    }

    // Atualiza uma transação
    public Optional<TransactionIng> updateTransaction(Long id, TransactionIng updatedTransaction) {
        return transactionIngRepository.findById(id).map(existingTransaction -> {
            existingTransaction.setDescription(updatedTransaction.getDescription());
            existingTransaction.setAmount(updatedTransaction.getAmount());
            existingTransaction.setDate(updatedTransaction.getDate());
            existingTransaction.setType(updatedTransaction.getType());
            existingTransaction.setTransactionCategory(updatedTransaction.getTransactionCategory());
            return transactionIngRepository.save(existingTransaction);
        });
    }
}
