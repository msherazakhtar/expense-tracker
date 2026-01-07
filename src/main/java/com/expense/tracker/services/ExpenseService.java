package com.expense.tracker.services;

import java.util.List;

import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.models.ExpenseSummaryORM;

public interface ExpenseService {

	ExpenseRecord addExpense(ExpenseRecord expenseRecord);

	List<ExpenseRecord> getExpenseByUserId(SearchCriteria requestCriteria);

	String deleteSingleExpense(Long expenseId);

	List<ExpenseSummaryORM> getExpenseSummary(SearchCriteria requestCriteria);

}
