package com.expense.tracker.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseRecord(
    Long id,
    String title,
    String details,
    BigDecimal amount,
    String category,
    Long userId,
    String createdBy,
    Boolean isGroup,
    LocalDateTime createdAt
) {}
