package com.expense.tracker.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/expense")
public class Expense {
    @GetMapping("/test")
    public String getMethodName() {
        return new String("Testing Expense Controller");
    }

}
