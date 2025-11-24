package com.life.finmate.transaction.mapper;

import com.life.finmate.transaction.domain.Transaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TransactionMapper {

    // 단일 조회
    Optional<Transaction> selectTransactionsById(Long id);

    //List 조회
    List<Transaction> selectTransactionsListWithUserId(Long userId);

    void insertTransaction(Transaction transaction);

    Optional<Transaction> updateTransaction(Transaction transaction);

    int deleteTransaction(Long id);
}
