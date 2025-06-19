package com.expense.tracker.services;

import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.UserRecord;
import com.expense.tracker.exceptions.UserAlreadyExistException;
import com.expense.tracker.models.UsersORM;
import com.expense.tracker.repositories.UserRepository;
import com.expense.tracker.utilities.UserUtility;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRecord registerUser(UserRecord user) {
        if (userRepository.existsByEmail(user.email())) {
            throw new UserAlreadyExistException("User with email : " + user.email() + " already exists.");
        }
        UsersORM userORM = UserUtility.mapUserRecordToORM(user);
        userORM = userRepository.save(userORM);
        user = UserUtility.mapUserORMToUserRecord(userORM);
        return user;
    }

}
