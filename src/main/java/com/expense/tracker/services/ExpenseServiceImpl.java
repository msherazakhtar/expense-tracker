package com.expense.tracker.services;

import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.models.ExpenseORM;
import com.expense.tracker.repositories.ExpenseRepository;
import com.expense.tracker.utilities.MappingUtility;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseRecord addExpense(ExpenseRecord expenseRecord) {
        ExpenseORM expenseORM = MappingUtility.mapExpenseRecordToORM(expenseRecord);
        expenseORM = expenseRepository.save(expenseORM);
        return MappingUtility.mapExpenseORMToRecord(expenseORM);
    }

}
