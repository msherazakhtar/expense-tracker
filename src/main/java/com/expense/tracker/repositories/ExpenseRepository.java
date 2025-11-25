package com.expense.tracker.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expense.tracker.models.ExpenseORM;
import com.expense.tracker.models.ExpenseSummaryORM;

public interface ExpenseRepository extends JpaRepository<ExpenseORM, Long>{
	public Page<ExpenseORM> findByUserId(Long userId,Pageable pageable);
	  @Query(value = """
        SELECT expense_id, title, details, amount, category, is_group, date_created 
        FROM expenses 
        WHERE user_id = :userId 
        AND (:category IS NULL OR category = :category) 
        AND Date(date_created) BETWEEN Date(:startDate) AND Date(:endDate)
        AND is_deleted = false
        ORDER BY date_created DESC
        """, nativeQuery = true)
    List<ExpenseSummaryORM> getUserExpenseSummary(
        @Param("userId") Long userId,
        @Param("category") String category,
        @Param("startDate") String startDate,
        @Param("endDate") String endDate
    );

}
