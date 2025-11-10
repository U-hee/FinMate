package com.life.finmate.transaction.mapper;

import com.life.finmate.transaction.domain.Transaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TransactionMapper {

    // 단일 조회
    Optional<Transaction> selectTransactionsById();

    //List 조회
    List<Transaction> selectTransactionsListWithUserId();

    Optional<Transaction> insertTransaction();

    void updateTransaction();

    void deleteTransaction();
}
