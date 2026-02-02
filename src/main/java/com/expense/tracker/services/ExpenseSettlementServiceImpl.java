package com.expense.tracker.services;

import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.ExpenseSettlementRecord;
import com.expense.tracker.models.ExpenseSettlementORM;
import com.expense.tracker.repositories.ExpenseSettlementRepository;
import com.expense.tracker.utilities.MappingUtility;

@Service
public class ExpenseSettlementServiceImpl implements ExpenseSettlementService {

    private final ExpenseSettlementRepository settlementRepository;

    public ExpenseSettlementServiceImpl(ExpenseSettlementRepository settlementRepository) {
        this.settlementRepository = settlementRepository;
    }

    @Override
    public ExpenseSettlementORM addSettlement(ExpenseSettlementRecord record) {
        return settlementRepository.save(MappingUtility.expenseSettlementRecordToORM(record));
    }

    // @Override
    // public List<ExpenseSettlementORM> getSettlementsByExpenseId(Long expenseId) {
    // return settlementRepository.findByExpenseId(expenseId);
    // }

    // @Override
    // public List<ExpenseSettlementORM> getSettlementsByUserId(Long userId) {
    // List<ExpenseSettlementORM> settlements = new ArrayList<>();
    // settlements.addAll(settlementRepository.findBySettledBy(userId));
    // settlements.addAll(settlementRepository.findBySettledTo(userId));
    // return settlements;
    // }

    // @Override
    // public ExpenseSettlementORM getSettlementById(Long settlementId) {
    // return settlementRepository.findById(settlementId).orElse(null);
    // }

    // @Override
    // public String deleteSettlement(Long settlementId) {
    // settlementRepository.deleteById(settlementId);
    // return "Settlement deleted successfully";
    // }
}
