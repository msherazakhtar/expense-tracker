package com.expense.tracker.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense_details")
public class ExpenseDetailsORM extends GlobalFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_details_id")
    private Long expenseDetailsId;
    @Column(name = "expense_id")
    private Long expenseId;
    @Column(name = "group_member_id")
    private Long groupMemberId;
    @Column(name = "paid_amount")
    private BigDecimal paidAmount;
    @Column(name = "pending_amount")
    private BigDecimal pendingAmount;

    public ExpenseDetailsORM(Boolean isDeleted, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt,
            String modifiedBy, Long expenseDetailsId, Long expenseId, Long groupMemberId, BigDecimal paidAmount,
            BigDecimal pendingAmount) {
        super(isDeleted, createdAt, createdBy, modifiedAt, modifiedBy);
        this.expenseDetailsId = expenseDetailsId;
        this.expenseId = expenseId;
        this.groupMemberId = groupMemberId;
        this.paidAmount = paidAmount;
        this.pendingAmount = pendingAmount;
    }

    public ExpenseDetailsORM(Long expenseDetailsId, Long expenseId, Long groupMemberId, BigDecimal paidAmount,
            BigDecimal pendingAmount) {
        this.expenseDetailsId = expenseDetailsId;
        this.expenseId = expenseId;
        this.groupMemberId = groupMemberId;
        this.paidAmount = paidAmount;
        this.pendingAmount = pendingAmount;
    }

    public ExpenseDetailsORM() {
        super();
    }

    public Long getExpenseDetailsId() {
        return expenseDetailsId;
    }

    public void setExpenseDetailsId(Long expenseDetailsId) {
        this.expenseDetailsId = expenseDetailsId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(Long groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(BigDecimal pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

}
