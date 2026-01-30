package com.expense.tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.ExpenseDetailsORM;

public interface ExpenseDetailsRepository extends JpaRepository<ExpenseDetailsORM, Long> {

    List<ExpenseDetailsORM> findByExpenseId(Long expenseId);

}
