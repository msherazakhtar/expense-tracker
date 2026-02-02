package com.expense.tracker.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.ExpenseDetailsRecord;
import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.dtos.SearchCriteria;
import com.expense.tracker.dtos.SearchCriteriaParmeters;
import com.expense.tracker.exceptions.ExpenseNotFoundException;
import com.expense.tracker.models.ExpenseDetailsORM;
import com.expense.tracker.models.ExpenseORM;
import com.expense.tracker.models.ExpenseSummaryORM;
import com.expense.tracker.repositories.ExpenseDetailsRepository;
import com.expense.tracker.repositories.ExpenseRepository;
import com.expense.tracker.utilities.MappingUtility;
import com.expense.tracker.wrappers.ExpenseWrapper;

import jakarta.transaction.Transactional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	ExpenseRepository expenseRepository;
	ExpenseDetailsRepository expenseDetailsRepository;

	public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseDetailsRepository expenseDetailsRepository) {
		this.expenseRepository = expenseRepository;
		this.expenseDetailsRepository = expenseDetailsRepository;
	}

	@Override
	@Transactional
	public ExpenseWrapper addExpense(ExpenseWrapper saveExpenseWrapper) {
		ExpenseORM expenseORM = MappingUtility.expenseRecordToORM(saveExpenseWrapper.getExpenseRecord());
		BigDecimal amountPerPerson = saveExpenseWrapper.getExpenseRecord().amount()
				.divide(BigDecimal.valueOf(saveExpenseWrapper.getExpenseDetails().size()), 2, RoundingMode.HALF_UP);
		expenseORM.setAmountPerHead(amountPerPerson);
		expenseORM = expenseRepository.save(expenseORM);

		List<ExpenseDetailsRecord> savedExpenseDetails = new ArrayList<>();
		for (ExpenseDetailsRecord expenseDetail : saveExpenseWrapper.getExpenseDetails()) {
			ExpenseDetailsORM expenseDetailsORM = MappingUtility.expenseDetailsRecordToORM(expenseDetail);
			expenseDetailsORM.setPendingAmount(expenseDetailsORM.getPaidAmount().subtract(amountPerPerson));
			BigDecimal pendingAmount = expenseDetailsORM.getPendingAmount();
			if (pendingAmount.compareTo(BigDecimal.ZERO) == 0) {
				expenseDetailsORM.setAmountToGet(BigDecimal.ZERO);
				expenseDetailsORM.setAmountToPay(BigDecimal.ZERO);
				expenseDetailsORM.setIsSettled(true);
			} else if (pendingAmount.compareTo(BigDecimal.ZERO) > 0) {
				expenseDetailsORM.setAmountToGet(pendingAmount);
				expenseDetailsORM.setIsSettled(false);
			} else if (pendingAmount.compareTo(BigDecimal.ZERO) < 0) {
				expenseDetailsORM.setAmountToPay(pendingAmount);
				expenseDetailsORM.setIsSettled(false);
			}
			expenseDetailsORM.setExpenseId(expenseORM.getId());
			expenseDetailsRepository.save(expenseDetailsORM);
			savedExpenseDetails.add(MappingUtility.expenseDetailsORMToRecord(expenseDetailsORM));
		}

		saveExpenseWrapper.setExpenseRecord(MappingUtility.expenseORMToRecord(expenseORM));
		saveExpenseWrapper.setExpenseDetails(savedExpenseDetails);
		return saveExpenseWrapper;
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

	@Override
	public ExpenseWrapper getExpenseById(Long expenseId) {
		ExpenseORM expenseORM = expenseRepository.findById(expenseId).orElseThrow(
				() -> new ExpenseNotFoundException("Expense not found..."));
		List<ExpenseDetailsORM> expenseDetailsORM = expenseDetailsRepository.findByExpenseId(expenseId);
		ExpenseWrapper expenseWrapper = new ExpenseWrapper();
		expenseWrapper.setExpenseRecord(MappingUtility.expenseORMToRecord(expenseORM));
		expenseWrapper.setExpenseDetails(MappingUtility.expenseDetailsORMListToRecordList(expenseDetailsORM));
		return expenseWrapper;
	}

}
