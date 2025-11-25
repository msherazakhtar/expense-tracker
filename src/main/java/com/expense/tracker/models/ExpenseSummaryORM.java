package com.expense.tracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ExpenseSummaryORM {
    @Id
    @Column(name = "expense_id")
    private Long expenseId;
    private String title;
    private String details;
    private Double amount;
    private String category;
    @Column(name = "is_group")
    private Boolean isGroup;
    @Column(name = "date_created") 
    private String dateCreated;
    public Long getExpenseId() {
        return expenseId;
    }
    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Boolean getIsGroup() {
        return isGroup;
    }
    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }
    public String getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    

}
