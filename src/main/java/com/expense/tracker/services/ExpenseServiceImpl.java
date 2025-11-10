package com.expense.tracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.exceptions.ExpenseNotFoundException;
import com.expense.tracker.exceptions.UserNotFoundException;
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
  
	@Override
	public List<ExpenseRecord> getExpenseByUserId(Long userId) {
		List<ExpenseORM> lstExpenseORM = expenseRepository.findByUserId(userId);
		List<ExpenseRecord> lstExpenseRecord = new ArrayList<ExpenseRecord>();
		for(ExpenseORM expense : lstExpenseORM) {
			lstExpenseRecord.add(MappingUtility.mapExpenseORMToRecord(expense));
		}
		return lstExpenseRecord;
	}

	@Override
	public String deleteSingleExpense(Long expenseId) {
		ExpenseORM expenseToDelete= expenseRepository.findById(expenseId).orElseThrow(
				 () -> new ExpenseNotFoundException("Expense not found..."));
				;
		expenseRepository.delete(expenseToDelete);
		return "Expense Deleted Successfuly";
		
	}

	

}
