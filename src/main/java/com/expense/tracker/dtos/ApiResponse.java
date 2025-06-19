package com.expense.tracker.dtos;

import com.expense.tracker.enums.ResponseStatus;

public record ApiResponse<T>(String responseMessage, String responseCode, T responseData,
                ResponseStatus responseStatus) {

}
