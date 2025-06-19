package com.expense.tracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.dtos.UserRecord;
import com.expense.tracker.enums.ResponseStatus;
import com.expense.tracker.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserRecord>> registerUser(@RequestBody UserRecord user) {
        user = userService.registerUser(user);
        ApiResponse<UserRecord> apiResponse = new ApiResponse<>(
                "User registered successfully",
                "201",
                user,
                ResponseStatus.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

}
