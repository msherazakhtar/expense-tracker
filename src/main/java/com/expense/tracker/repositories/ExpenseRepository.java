package com.expense.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.ExpenseORM;

public interface ExpenseRepository extends JpaRepository<ExpenseORM, Long>{

}
