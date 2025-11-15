package com.life.finmate.transaction.service;

import com.life.finmate.transaction.domain.Transaction;
import com.life.finmate.transaction.dto.TransactionDetailResponseDto;
import com.life.finmate.transaction.dto.TransactionListResponseDto;
import com.life.finmate.transaction.dto.TransactionUpdateRequestDto;
import com.life.finmate.transaction.mapper.TransactionMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionService transactionService;

    @DisplayName("단일 조회 테스트")
    @Test
    void findById() {
        //given
        Long id = 1L;
        Transaction transactionEntity = Transaction.builder()
                .userId(1L)
                .accountId(101L)
                .mainCategoryId(10L)
                .subCategoryId(11L)
                .transactionType("expense")              // 또는 "income"
                .amount(45900L)
                .transactionDate(LocalDate.of(2025, 11, 10))
                .description("스타벅스 커피 결제")
                .receiptUrl("https://example.com/receipt/12345")
                .isRecurring(false)
                .recurringId(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        given(transactionMapper.selectTransactionsById(id)).willReturn(Optional.ofNullable(transactionEntity));

        //when
        TransactionDetailResponseDto responseDto = transactionService.findById(id);

        //then
        assertThat(responseDto).isNotNull();
        assertThat(transactionEntity).isNotNull();
        assertThat(responseDto.getId()).isEqualTo(transactionEntity.getId());
    }

    @DisplayName("리스트 조회 테스트")
    @Test
    void findByUserIdTransactionList() {
        // given
        Long userId = 1L;
        List<Transaction> transactionList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            transactionList.add(Transaction.builder()
                    .userId(userId)
                    .accountId(101L)
                    .mainCategoryId((long) i)
                    .subCategoryId(11L)
                    .transactionType("expense")              // 또는 "income"
                    .amount(45900L)
                    .transactionDate(LocalDate.of(2025, 11, 10))
                    .description("스타벅스 커피 결제")
                    .receiptUrl("https://example.com/receipt/12345")
                    .isRecurring(false)
                    .recurringId(null)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build());
        }
        given(transactionMapper.selectTransactionsListWithUserId(userId)).willReturn(transactionList);

        //when
        List<TransactionListResponseDto> byUserIdTransactionList = transactionService.findByUserIdTransactionList(userId);

        //then
        assertThat(byUserIdTransactionList).isNotNull();
        assertThat(byUserIdTransactionList.size()).isEqualTo(5);
        assertThat(byUserIdTransactionList.getFirst().getMainCategoryId()).isEqualTo(transactionList.getFirst().getMainCategoryId());
    }


    @DisplayName("업데이트 테스트")
    @Test
    void updateTransaction() {
        // given
        Long transactionId = 1L;
        Long userId = 1L;

        TransactionUpdateRequestDto requestDto = TransactionUpdateRequestDto.builder()
                .amount(50000L)
                .description("수정된 내역")
                .build();

        Transaction existingTransaction= Transaction.builder()
                .id(transactionId)
                .userId(userId)
                .accountId(101L)
                .mainCategoryId(10L)
                .subCategoryId(11L)
                .transactionType("expense")              // 또는 "income"
                .amount(45900L)
                .transactionDate(LocalDate.of(2025, 11, 10))
                .description("스타벅스 커피 결제")
                .receiptUrl("https://example.com/receipt/12345")
                .isRecurring(false)
                .recurringId(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        given(transactionMapper.selectTransactionsById(transactionId)).willReturn(Optional.of(existingTransaction));

        // when
        // 서비스가 transactionId와 DTO를 받는다고 가정합니다.
        transactionService.updateTransaction(transactionId, requestDto);

        // then
        // 매퍼의 update 메서드가 업데이트된 엔티티로 호출되었는지 확인합니다.
        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionMapper).updateTransaction(captor.capture());
        Transaction updatedTransaction = captor.getValue();

        // 캡처된 엔티티에 업데이트된 값이 있는지 확인합니다.
        assertThat(updatedTransaction.getAmount()).isEqualTo(requestDto.getAmount());
        assertThat(updatedTransaction.getDescription()).isEqualTo(requestDto.getDescription());
    }

    @DisplayName("삭제 테스트")
    @Test
    void deleteTransaction() {
        // given
        Long transactionId = 1L;
        Long userId = 1L;

        Transaction existingTransaction = Transaction.builder()
                .id(transactionId)
                .userId(userId)
                .build();

        given(transactionMapper.deleteTransaction(transactionId)).willReturn(1);

        // when
        int result = transactionService.deleteTransaction(transactionId);

        // then
        verify(transactionMapper).deleteTransaction(transactionId);
        assertThat(result).isEqualTo(1);
    }
}