package com.expense.tracker.utilities;

import com.expense.tracker.dtos.ExpenseCategoryRecord;
import com.expense.tracker.dtos.ExpenseRecord;
import com.expense.tracker.dtos.UserProfileRecord;
import com.expense.tracker.dtos.UserRecord;
import com.expense.tracker.models.ExpenseCategoryORM;
import com.expense.tracker.models.ExpenseORM;
import com.expense.tracker.models.UsersORM;

public class MappingUtility {
    public static UsersORM mapUserRecordToORM(UserRecord user) {
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

    public static UserRecord mapUserORMToUserRecord(UsersORM userOrm) {
        return new UserRecord(userOrm.getUserId(), userOrm.getFirstName(), userOrm.getLastName(), userOrm.getEmail(),
                userOrm.getPassword(), userOrm.getIsActive(), userOrm.getIsVerified());
    }

    public static UserProfileRecord mapUserORMToUserProfile(UsersORM userOrm) {
        return new UserProfileRecord(userOrm.getUserId(), userOrm.getFirstName(), userOrm.getLastName(),
                userOrm.getEmail(),
                userOrm.getPassword(), userOrm.getIsVerified());
    }

    public static UsersORM mapUserProfileRecordToORM(UserProfileRecord userProfileRecord) {
        UsersORM userOrm = new UsersORM();
        userOrm.setUserId(userProfileRecord.userId());
        userOrm.setFirstName(userProfileRecord.firstName());
        userOrm.setLastName(userProfileRecord.lastName());
        userOrm.setEmail(userProfileRecord.email());
        userOrm.setPassword(userProfileRecord.password());
        userOrm.setIsActive(true);
        userOrm.setIsVerified(userProfileRecord.isVerified());
        return userOrm;
    }

    // Expense Category
    public static ExpenseCategoryRecord mapExpenseCategoryORMToRecord(ExpenseCategoryORM categoryORM) {
        return new ExpenseCategoryRecord(
                categoryORM.getCategoryId(),
                categoryORM.getName(),
                categoryORM.getUserId(),
                categoryORM.getIsGlobal()

        );
    }

    public static ExpenseCategoryORM mapExpenseRecordToCategoryORM(ExpenseCategoryRecord record) {
        ExpenseCategoryORM categoryORM = new ExpenseCategoryORM();
        categoryORM.setCategoryId(record.categoryId()); // Optional: Usually null for inserts
        categoryORM.setName(record.name());
        categoryORM.setUserId(record.userId());
        categoryORM.setIsGlobal(record.isGlobal());
        return categoryORM;
    }

    // Expense
    public static ExpenseORM mapExpenseRecordToORM(ExpenseRecord record) {
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

    public static ExpenseRecord mapExpenseORMToRecord(ExpenseORM orm) {
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

}
