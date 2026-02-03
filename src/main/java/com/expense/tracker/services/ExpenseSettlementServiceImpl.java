package com.expense.tracker.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.ExpenseSettlementRecord;
import com.expense.tracker.models.ExpenseDetailsORM;
import com.expense.tracker.models.ExpenseSettlementORM;
import com.expense.tracker.repositories.ExpenseDetailsRepository;
import com.expense.tracker.repositories.ExpenseSettlementRepository;
import com.expense.tracker.utilities.MappingUtility;

import jakarta.transaction.Transactional;

@Service
public class ExpenseSettlementServiceImpl implements ExpenseSettlementService {

    private final ExpenseSettlementRepository settlementRepository;
    private final ExpenseDetailsRepository expenseDetailsRepository;

    public ExpenseSettlementServiceImpl(ExpenseSettlementRepository settlementRepository,
            ExpenseDetailsRepository expenseDetailsRepository) {
        this.settlementRepository = settlementRepository;
        this.expenseDetailsRepository = expenseDetailsRepository;
    }

    @Override
    @Transactional
    public ExpenseSettlementORM expenseSettlementPaid(ExpenseSettlementRecord record) {
        ExpenseDetailsORM expenseDetails = expenseDetailsRepository.findById(record.expenseDetailsId()).orElseThrow();
        expenseDetails.setAmountToPay(expenseDetails.getAmountToPay().subtract(record.settlementAmount()));
        if (expenseDetails.getAmountToPay().compareTo(BigDecimal.ZERO) <= 0) {
            expenseDetails.setIsSettled(true);
        }
        expenseDetailsRepository.save(expenseDetails);
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
