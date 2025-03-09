package com.example.ing.financial.repository;

import com.example.ing.financial.entity.TransactionIng;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionIngRepository extends JpaRepository<TransactionIng, Long> {
    // Métodos adicionais podem ser definidos aqui
}
