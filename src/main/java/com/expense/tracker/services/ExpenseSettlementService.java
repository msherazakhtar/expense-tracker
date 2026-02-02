package com.expense.tracker.services;

import com.expense.tracker.dtos.ExpenseSettlementRecord;
import com.expense.tracker.models.ExpenseSettlementORM;

public interface ExpenseSettlementService {
    ExpenseSettlementORM addSettlement(ExpenseSettlementRecord record);

    // List<ExpenseSettlementORM> getSettlementsByExpenseId(Long expenseId);

    // List<ExpenseSettlementORM> getSettlementsByUserId(Long userId);

    // ExpenseSettlementORM getSettlementById(Long settlementId);

    // String deleteSettlement(Long settlementId);
}
