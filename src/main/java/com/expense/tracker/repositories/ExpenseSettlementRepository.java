package com.expense.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.ExpenseSettlementORM;

public interface ExpenseSettlementRepository extends JpaRepository<ExpenseSettlementORM, Long> {
    // List<ExpenseSettlementORM> findByExpenseId(Long expenseId);

    // List<ExpenseSettlementORM> findBySettledBy(Long settledBy);

    // List<ExpenseSettlementORM> findBySettledTo(Long settledTo);
}
