package com.expense.tracker.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.expense.tracker.dtos.UserProfileRecord;
import com.expense.tracker.dtos.UserRecord;
import com.expense.tracker.exceptions.MailConfigurationNotFoundException;
import com.expense.tracker.exceptions.UserAlreadyExistException;
import com.expense.tracker.exceptions.UserNotFoundException;
import com.expense.tracker.models.MailConfigurationORM;
import com.expense.tracker.models.UsersORM;
import com.expense.tracker.repositories.MailConfigurationRepository;
import com.expense.tracker.repositories.UserRepository;
import com.expense.tracker.utilities.EmailUtility;
import com.expense.tracker.utilities.GeneralUtilities;
import com.expense.tracker.utilities.MappingUtility;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private MailConfigurationRepository mailConfigurationRepository;

    public UserServiceImpl(UserRepository userRepository, MailConfigurationRepository mailConfigurationRepository) {
        this.userRepository = userRepository;
        this.mailConfigurationRepository = mailConfigurationRepository;
    }

    @Override
    public UserRecord registerUser(UserRecord user) {
        if (userRepository.existsByEmail(user.email())) {
            throw new UserAlreadyExistException("User with email : " + user.email() + " already exists.");
        }
        UsersORM userORM = MappingUtility.mapUserRecordToORM(user);
        Optional<MailConfigurationORM> mailConfiguration = Optional.ofNullable(mailConfigurationRepository
                .findBymailServer("Gmail")
                .orElseThrow(
                        () -> new MailConfigurationNotFoundException("Mail Configuration for your server not found")));
        userORM.setVerificationCode(GeneralUtilities.generateVerificationCode());
        userORM.setPassword(new BCryptPasswordEncoder().encode(userORM.getPassword()));
        userORM = userRepository.save(userORM);
        EmailUtility.sendUserVerificationEmail(mailConfiguration.get(), userORM.getEmail(),
                userORM.getVerificationCode());
        return MappingUtility.mapUserORMToUserRecord(userORM);

    }

    @Override
    public String verifyUser(String userId, String verificationCode) {
        UsersORM user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(
                        () -> new UserNotFoundException("User not found..."));
        if (user.getVerificationCode().equals(verificationCode)) {
            userRepository.save(user);
            return "User Verified Successfully.";
        }
        return "Invalid Verification Code... Please Try Again...";
    }

    @Override
    public UserProfileRecord getUserProfile(String userId) {
        UsersORM usersORM = userRepository.findById(Long.parseLong(userId)).orElseThrow(
                () -> new UserNotFoundException("User Not Found"));
        return MappingUtility.mapUserORMToUserProfile(usersORM);
    }

    @Override
    public UserProfileRecord updateUserProfile(UserProfileRecord userProfileRecord) {
        UsersORM usersORM = MappingUtility.mapUserProfileRecordToORM(userProfileRecord);
        usersORM.setPassword(new BCryptPasswordEncoder().encode(usersORM.getPassword()));
        usersORM =  userRepository.save(usersORM);
        return MappingUtility.mapUserORMToUserProfile(usersORM);

    }

}
