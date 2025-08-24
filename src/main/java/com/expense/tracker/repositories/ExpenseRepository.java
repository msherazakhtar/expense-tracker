package com.expense.tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.models.ExpenseORM;

public interface ExpenseRepository extends JpaRepository<ExpenseORM, Long>{
	public List<ExpenseORM> findByUserId(Long userId);

}
