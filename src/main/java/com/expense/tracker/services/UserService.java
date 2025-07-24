package com.expense.tracker.services;

import com.expense.tracker.dtos.UserProfileRecord;
import com.expense.tracker.dtos.UserRecord;

public interface UserService {

    UserRecord registerUser(UserRecord user);

    String verifyUser(String userId,String verificationCode);

    UserProfileRecord getUserProfile(String userId);

    UserProfileRecord updateUserProfile(UserProfileRecord userProfileRecord);

}
