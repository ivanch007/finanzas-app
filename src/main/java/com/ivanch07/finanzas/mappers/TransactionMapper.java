package com.ivanch07.finanzas.mappers;

import com.ivanch07.finanzas.dto.transactionDto.TransactionRequestDto;
import com.ivanch07.finanzas.dto.transactionDto.TransactionResponseDto;
import com.ivanch07.finanzas.model.Transaction;
import com.ivanch07.finanzas.model.User;

public class TransactionMapper {

    public static TransactionResponseDto toTransactionDto(Transaction transaction){

        if (transaction == null) return null;

        return new TransactionResponseDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDate(),
                CategoryMapper.toCategoryDto(transaction.getCategory())
        );
    }

    public static Transaction toEntity (TransactionRequestDto transactionRequestDto, User user){

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequestDto.getAmount());
        transaction.setDescription(transactionRequestDto.getDescription());
        transaction.setDate(transactionRequestDto.getDate());
        transaction.setUser(user);

        return transaction;
    }
}
