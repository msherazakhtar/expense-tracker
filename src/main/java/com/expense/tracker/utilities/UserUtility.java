package com.expense.tracker.utilities;

import com.expense.tracker.dtos.UserRecord;
import com.expense.tracker.models.UsersORM;

public class UserUtility {
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

}
