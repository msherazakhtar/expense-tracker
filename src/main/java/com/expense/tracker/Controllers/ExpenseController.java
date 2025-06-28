package com.expense.tracker.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.enums.ResponseStatus;
import com.expense.tracker.services.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/addExpense")
    public ResponseEntity<ApiResponse<ExpenseRecord>> addExpense(
            @RequestBody ExpenseRecord expenseRecord) {
        expenseRecord = expenseService.addExpense(expenseRecord);
        ApiResponse<ExpenseRecord> apiResponse = new ApiResponse<>(
                "Expense",
                "201",
                expenseRecord,
                ResponseStatus.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }
    //  @PostMapping("/addExpense")
    // public ResponseEntity<ApiResponse<ExpenseRecord>> addExpense(
    //         @RequestBody ExpenseRecord expenseRecord) {
    //     expenseRecord = expenseService.addExpense(expenseRecord);
    //     ApiResponse<ExpenseRecord> apiResponse = new ApiResponse<>(
    //             "Expense",
    //             "201",
    //             expenseRecord,
    //             ResponseStatus.SUCCESS);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    // }

}
