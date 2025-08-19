package com.ivanch07.finanzas.repositoy;

import com.ivanch07.finanzas.model.Transaction;
import com.ivanch07.finanzas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser(User user);
}
