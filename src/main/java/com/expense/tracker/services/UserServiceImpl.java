package com.expense.tracker.services;

import org.springframework.stereotype.Service;

import com.expense.tracker.models.UsersORM;
import com.expense.tracker.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsersORM registerUser(UsersORM user) {
        return userRepository.save(user);
    }

}
