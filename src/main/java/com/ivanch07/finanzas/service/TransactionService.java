package com.ivanch07.finanzas.service;

import com.ivanch07.finanzas.model.Transaction;
import com.ivanch07.finanzas.model.User;
import com.ivanch07.finanzas.repositoy.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;


    public TransactionService(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    public List<Transaction> getTransactions(){

        User currentUser = userService.getCurrentUser();
        return transactionRepository.findByUser(currentUser);
    }

    public Transaction createTransaction(Transaction transaction){

        User currentUser = userService.getCurrentUser();
        transaction.setUser(currentUser);

        return transactionRepository.save(transaction);

    }

    public Transaction updateTransaction(Long id,Transaction updatedTransaction){

        User currentUser = userService.getCurrentUser();

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        if (!transaction.getUser().getId().equals(currentUser.getId())){
            throw new RuntimeException("No puede actualizar una transacción que no es tuya");
        }

        transaction.setDescription(updatedTransaction.getDescription());
        transaction.setAmount(updatedTransaction.getAmount());

        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {

        User currentUser = userService.getCurrentUser();
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        if (!transaction.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No puede transacción una transacción que no es tuya");

        }
        transactionRepository.save(transaction);
    }
}
