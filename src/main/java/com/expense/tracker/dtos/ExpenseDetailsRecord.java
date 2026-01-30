package com.expense.tracker.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseDetailsRecord(
        Long expenseDetailsId,
        Long expenseId,
        Long groupMemberId,
        BigDecimal paidAmount,
        BigDecimal pendingAmount,
        String createdBy,
        LocalDateTime createdAt) {

}
