package com.ivanch07.finanzas.mappers;

import com.ivanch07.finanzas.dto.TransactionRequestDto;
import com.ivanch07.finanzas.dto.TransactionResponseDto;
import com.ivanch07.finanzas.model.Transaction;
import com.ivanch07.finanzas.model.User;

public class TransactionMapper {

    public static TransactionResponseDto toTransactionDto(Transaction transaction){

        return new TransactionResponseDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDate(),
                transaction.getCategory()
        );
    }

    public static Transaction toEntity (TransactionRequestDto transactionRequestDto, User user){

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequestDto.getAmount());
        transaction.setDescription(transactionRequestDto.getDescription());
        transaction.setDate(transactionRequestDto.getDate());
        transaction.setCategory(transactionRequestDto.getCategory());
        transaction.setUser(user);

        return transaction;
    }
}
