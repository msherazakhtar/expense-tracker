package com.expense.tracker.services;

import com.expense.tracker.dtos.ExpenseRecord;

public interface ExpenseService {

    ExpenseRecord addExpense(ExpenseRecord expenseRecord);

}
