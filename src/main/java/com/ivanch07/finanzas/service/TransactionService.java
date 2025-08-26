package com.ivanch07.finanzas.service;


import com.ivanch07.finanzas.dto.TransactionRequestDto;
import com.ivanch07.finanzas.dto.TransactionResponseDto;
import com.ivanch07.finanzas.mappers.TransactionMapper;
import com.ivanch07.finanzas.model.Transaction;
import com.ivanch07.finanzas.model.User;
import com.ivanch07.finanzas.repositoy.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;


    public TransactionService(TransactionRepository transactionRepository,
                              UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    public List<TransactionResponseDto> getTransactions() {
        User currentUser = userService.getCurrentUser();
        return transactionRepository.findByUser(currentUser)
                .stream()
                .map(TransactionMapper :: toTransactionDto)
                .collect(Collectors.toList());
    }

    public TransactionResponseDto createTransaction(
            TransactionRequestDto transactionRequestDto){

        User currentUser = userService.getCurrentUser();

        Transaction transaction = TransactionMapper.toEntity(
                transactionRequestDto, currentUser);

        Transaction savedTransaction = transactionRepository.save(transaction);

       return TransactionMapper.toTransactionDto(savedTransaction);
    }

    public TransactionResponseDto updateTransaction(Long id,TransactionRequestDto transactionRequestDto){

        User currentUser = userService.getCurrentUser();

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        if (!transaction.getUser().getId().equals(currentUser.getId())){
            throw new RuntimeException("No puede actualizar una transacción que no es tuya");
        }

        transaction.setDescription(transactionRequestDto.getDescription());
        transaction.setAmount(transactionRequestDto.getAmount());
        transaction.setDate(transactionRequestDto.getDate());
        transaction.setCategory(transactionRequestDto.getCategory());

        Transaction transactionUpdated = transactionRepository.save(transaction);

        return TransactionMapper.toTransactionDto(transactionUpdated);
    }

    public void deleteTransaction(Long id) {

        User currentUser = userService.getCurrentUser();
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        if (!transaction.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No puede transacción una transacción que no es tuya");

        }
        transactionRepository.delete(transaction);
    }
}
