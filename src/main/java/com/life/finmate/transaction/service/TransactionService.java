package com.life.finmate.transaction.service;

import com.life.finmate.transaction.domain.Transaction;
import com.life.finmate.transaction.dto.*;
import com.life.finmate.transaction.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper transactionMapper;


    // Create
    public TransactionCreateResponseDto createTransaction(TransactionCreateRequestDto createRequestDto) {
        Transaction transactionEntity = createRequestDto.toEntity();
        transactionMapper.insertTransaction(transactionEntity);
        return TransactionCreateResponseDto.from(transactionEntity);
    }

    // Read - one
    public TransactionDetailResponseDto findById(Long id) {
        Transaction transaction = transactionMapper.selectTransactionsById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND : " + id)
        );
        return TransactionDetailResponseDto.from(transaction);
    }


    //Read - List
    public List<TransactionListResponseDto> findByUserIdTransactionList(Long userId) {
        List<Transaction> transactions = transactionMapper.selectTransactionsListWithUserId(userId);
        return transactions.stream().map(TransactionListResponseDto::from).toList();
    }

    // Update
    public TransactionDetailResponseDto updateTransaction(Long id, TransactionUpdateRequestDto updateRequestDto) {
        Transaction transaction = transactionMapper.selectTransactionsById(id).orElseThrow(() -> new IllegalArgumentException("NOT FOUND : " + id));
        System.out.println("origin transaction: " + updateRequestDto.getAmount());

        transaction.update(
                updateRequestDto.getAccountId(),
                updateRequestDto.getMainCategoryId(),
                updateRequestDto.getSubCategoryId(),
                updateRequestDto.getAmount(),
                updateRequestDto.getTransactionDate(),
                updateRequestDto.getIsRecurring(),
                updateRequestDto.getDescription()
        );

        Optional<Transaction> transaction1 = transactionMapper.updateTransaction(transaction);

        return TransactionDetailResponseDto.from(transaction);
    }

    // Delete
    public int deleteTransaction(Long id) {
        return transactionMapper.deleteTransaction(id);
    }
}


