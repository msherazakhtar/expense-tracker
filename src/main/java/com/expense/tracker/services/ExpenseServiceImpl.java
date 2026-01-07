package com.expense.tracker.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.dtos.SearchCriteriaParmeters;
import com.expense.tracker.exceptions.ExpenseNotFoundException;
import com.expense.tracker.models.ExpenseORM;
import com.expense.tracker.models.ExpenseSummaryORM;
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
		ExpenseORM expenseORM = MappingUtility.expenseRecordToORM(expenseRecord);
		expenseORM = expenseRepository.save(expenseORM);
		return MappingUtility.expenseORMToRecord(expenseORM);
	}

	@Override
	public List<ExpenseRecord> getExpenseByUserId(SearchCriteria requestCriteria) {
		Sort sort = requestCriteria.getSortingOrder().equalsIgnoreCase("desc")
				? Sort.by(requestCriteria.getSortBy()).descending()
				: Sort.by(requestCriteria.getSortBy()).ascending();

		Page<ExpenseORM> pageExpenses = expenseRepository.findByUserId(requestCriteria.getId(),
				PageRequest.of(requestCriteria.getPageNumber(), requestCriteria.getPageSize(), sort));
		List<ExpenseORM> lstExpenseORM = pageExpenses.getContent();
		List<ExpenseRecord> lstExpenseRecord = new ArrayList<ExpenseRecord>();
		for (ExpenseORM expense : lstExpenseORM) {
			lstExpenseRecord.add(MappingUtility.expenseORMToRecord(expense));
		}
		return lstExpenseRecord;
	}

	@Override
	public List<ExpenseSummaryORM> getExpenseSummary(SearchCriteria requestCriteria) {

		String dateFrom = null;
		String dateTo = null;
		String category = "";
		for (SearchCriteriaParmeters parameter : requestCriteria.getParameters()) {
			if (parameter.getParamName().equals("dateFrom")) {
				dateFrom = parameter.getParamValue();
			} else if (parameter.getParamName().equals("dateTo")) {
				dateTo = parameter.getParamValue();
			} else if (parameter.getParamName().equals("category")) {
				category = parameter.getParamValue();
			}
		}

		return expenseRepository.getUserExpenseSummary(requestCriteria.getId(), category, dateFrom, dateTo);
	}

	@Override
	public String deleteSingleExpense(Long expenseId) {
		ExpenseORM expenseToDelete = expenseRepository.findById(expenseId).orElseThrow(
				() -> new ExpenseNotFoundException("Expense not found..."));
		;
		expenseRepository.delete(expenseToDelete);
		return "Expense Deleted Successfuly";

	}

}
