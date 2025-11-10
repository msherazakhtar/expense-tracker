package com.expense.tracker.services;

import java.util.List;

import com.expense.tracker.dtos.ExpenseRecord;

public interface ExpenseService {

    ExpenseRecord addExpense(ExpenseRecord expenseRecord);

	List<ExpenseRecord> getExpenseByUserId(Long userId);

	String deleteSingleExpense(Long expenseId);


}
