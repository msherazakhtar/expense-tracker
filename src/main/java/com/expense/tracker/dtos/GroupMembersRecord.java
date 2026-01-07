package com.expense.tracker.dtos;

import java.time.LocalDateTime;

public record GroupMembersRecord(Long groupMemebrId, String name, String email, String phone, LocalDateTime createdAt,
        String createdBy, LocalDateTime modifiedAt, String modifiedBy) {

}
