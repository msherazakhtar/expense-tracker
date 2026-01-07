package com.expense.tracker.utilities;

import com.expense.tracker.dtos.GroupRecord;
import com.expense.tracker.dtos.ExpenseCategoryRecord;
import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.dtos.GroupMembersRecord;
import com.expense.tracker.dtos.UserProfileRecord;
import com.expense.tracker.dtos.UserRecord;
import com.expense.tracker.models.ExpenseCategoryORM;
import com.expense.tracker.models.ExpenseORM;
import com.expense.tracker.models.GroupMembersORM;
import com.expense.tracker.models.GroupsORM;
import com.expense.tracker.models.UsersORM;

public class MappingUtility {
    public static UsersORM userRecordToORM(UserRecord user) {
        UsersORM userOrm = new UsersORM();
        userOrm.setUserId(user.userId());
        userOrm.setFirstName(user.firstName());
        userOrm.setLastName(user.lastName());
        userOrm.setEmail(user.email());
        userOrm.setPassword(user.password());
        userOrm.setIsActive(user.isActive());
        userOrm.setIsVerified(user.isVerified());
        return userOrm;
    }

    public static UserRecord userORMToUserRecord(UsersORM userOrm) {
        return new UserRecord(userOrm.getUserId(), userOrm.getFirstName(), userOrm.getLastName(), userOrm.getEmail(),
                userOrm.getPassword(), userOrm.getIsActive(), userOrm.getIsVerified());
    }

    public static UserProfileRecord userORMToUserProfile(UsersORM userOrm) {
        return new UserProfileRecord(userOrm.getUserId(), userOrm.getFirstName(), userOrm.getLastName(),
                userOrm.getEmail(),
                userOrm.getPassword(), userOrm.getIsVerified());
    }

    public static UsersORM userProfileRecordToORM(UserProfileRecord userProfileRecord) {
        UsersORM userOrm = new UsersORM();
        userOrm.setUserId(userProfileRecord.userId());
        userOrm.setFirstName(userProfileRecord.firstName());
        userOrm.setLastName(userProfileRecord.lastName());
        userOrm.setEmail(userProfileRecord.email());
        userOrm.setIsActive(true);
        userOrm.setIsVerified(userProfileRecord.isVerified());
        return userOrm;
    }

    // Expense Category
    public static ExpenseCategoryRecord expenseCategoryORMToRecord(ExpenseCategoryORM categoryORM) {
        return new ExpenseCategoryRecord(
                categoryORM.getCategoryId(),
                categoryORM.getName(),
                categoryORM.getUserId(),
                categoryORM.getIsGlobal()

        );
    }

    public static ExpenseCategoryORM expenseRecordToCategoryORM(ExpenseCategoryRecord record) {
        ExpenseCategoryORM categoryORM = new ExpenseCategoryORM();
        categoryORM.setCategoryId(record.categoryId()); // Optional: Usually null for inserts
        categoryORM.setName(record.name());
        categoryORM.setUserId(record.userId());
        categoryORM.setIsGlobal(record.isGlobal());
        return categoryORM;
    }

    // Expense
    public static ExpenseORM expenseRecordToORM(ExpenseRecord record) {
        ExpenseORM orm = new ExpenseORM();
        orm.setId(record.id());
        orm.setTitle(record.title());
        orm.setDetails(record.details());
        orm.setAmount(record.amount());
        orm.setCategory(record.category());
        orm.setCreatedBy(record.createdBy());
        orm.setIsGroup(record.isGroup());
        orm.setCreatedAt(record.createdAt());
        orm.setUserId(record.userId());
        return orm;
    }

    public static ExpenseRecord expenseORMToRecord(ExpenseORM orm) {
        return new ExpenseRecord(
                orm.getId(),
                orm.getTitle(),
                orm.getDetails(),
                orm.getAmount(),
                orm.getCategory(),
                orm.getUserId(),
                orm.getCreatedBy(),
                orm.getIsGroup(),
                orm.getCreatedAt());
    }

    // Groups
    public static GroupsORM groupRecordToORM(GroupRecord record) {
        return new GroupsORM(record.groupId(), record.name(), record.userId(), false, record.createdAt(),
                record.createdBy(), record.modifiedAt(), record.modifiedBy());
    }

    public static GroupRecord groupORMToRecord(GroupsORM orm) {
        return new GroupRecord(orm.getGroupId(), orm.getName(), orm.getUserId(), orm.getCreatedAt(),
                orm.getCreatedBy(), orm.getModifiedAt(), orm.getModifiedBy());
    }

    // Group Memebrs
    public static GroupMembersORM groupMemebrRecordToORM(GroupMembersRecord record) {
        return new GroupMembersORM(record.groupMemebrId(), record.name(), record.email(), record.phone(), false,
                record.createdAt(),
                record.createdBy(), record.modifiedAt(), record.modifiedBy());
    }

    public static GroupMembersRecord groupMemberORMToRecord(GroupMembersORM orm) {
        return new GroupMembersRecord(orm.getGroupMemberId(), orm.getName(), orm.getEmail(), orm.getPhone(),
                orm.getCreatedAt(), orm.getCreatedBy(), orm.getModifiedAt(), orm.getModifiedBy());
    }
}
