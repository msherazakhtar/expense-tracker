package com.expense.tracker.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expense.tracker.dtos.UserProfileRecord;
import com.expense.tracker.dtos.UserRecord;
import com.expense.tracker.exceptions.UserAlreadyExistException;
import com.expense.tracker.exceptions.UserNotFoundException;
import com.expense.tracker.models.UsersORM;
import com.expense.tracker.repositories.UserRepository;
import com.expense.tracker.utilities.FileUtils;
import com.expense.tracker.utilities.MappingUtility;

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
        UsersORM userORM = MappingUtility.userRecordToORM(user);
        userORM.setPassword(new BCryptPasswordEncoder().encode(userORM.getPassword()));
        userORM = userRepository.save(userORM);
        return MappingUtility.userORMToUserRecord(userORM);

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
        return MappingUtility.userORMToUserProfile(usersORM);
    }

    @Override
    public UserProfileRecord updateUserProfile(UserProfileRecord userProfileRecord) {
        UsersORM usersORM = MappingUtility.userProfileRecordToORM(userProfileRecord);
        if (usersORM.getPassword() != null &&
                !usersORM.getPassword().equals("")) {
            usersORM.setPassword(new BCryptPasswordEncoder().encode(usersORM.getPassword()));
        }
        usersORM = userRepository.save(usersORM);
        return MappingUtility.userORMToUserProfile(usersORM);

    }

    @Override
    public UserProfileRecord uploadProfilePicture(String userId, MultipartFile profilePicture) {
        // finding the user by ID
        UsersORM usersORM = userRepository.findById(Long.parseLong(userId)).orElseThrow(
                () -> new UserNotFoundException("User Not Found"));
        // uploading the profile picture and getting the URL
        String profilePictureUrl = FileUtils.uploadFile(profilePicture);
        usersORM.setProfilePictureUrl(profilePictureUrl);
        usersORM = userRepository.save(usersORM);
        return MappingUtility.userORMToUserProfile(usersORM);
    }

}
