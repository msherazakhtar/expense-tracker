package com.expense.tracker.services;

import java.util.List;

import com.expense.tracker.dtos.ExpenseCategoryRecord;

public interface ExpenseCategoryService {

    ExpenseCategoryRecord addExpenseCategory(ExpenseCategoryRecord expenseCategoryRecord);

    List<ExpenseCategoryRecord> getExpenseCategories(String userId);

    String deleteExpenseCategories(List<ExpenseCategoryRecord> expenseCategoryRecords);

}
