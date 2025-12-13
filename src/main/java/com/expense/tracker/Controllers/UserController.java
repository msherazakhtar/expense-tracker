package com.expense.tracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.expense.tracker.dtos.ApiResponse;
import com.expense.tracker.dtos.UserProfileRecord;
import com.expense.tracker.dtos.UserRecord;
import com.expense.tracker.enums.ResponseStatus;
import com.expense.tracker.services.UserService;
import com.expense.tracker.utilities.ApiResponseUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
        UserService userService;

        public UserController(UserService userService) {
                this.userService = userService;
        }

        @GetMapping("/sayHello")
        public String sayHello() {
                return "Hello Developer...";
        }

        @PostMapping("/register")
        public ResponseEntity<ApiResponse<UserRecord>> registerUser(@RequestBody UserRecord userRecord) {
                userRecord = userService.registerUser(userRecord);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponseUtil.setApiResponse(ResponseStatus.SUCCESS, userRecord));
        }

        @GetMapping("/verify/{userId}/{verificationCode}")
        public ResponseEntity<ApiResponse<String>> verifyUserEmail(
                        @PathVariable(name = "userId") String userId,
                        @PathVariable(name = "verificationCode") String verificationCode

        ) {
                String respMessage = userService.verifyUser(userId, verificationCode);
                ApiResponse<String> apiResponse = new ApiResponse<>(
                                "User Verification",
                                respMessage,
                                ResponseStatus.SUCCESS);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        }

        @GetMapping("/getUserProfile/{userId}")
        public ResponseEntity<UserProfileRecord> getUserProfile(
                        @PathVariable(name = "userId") String userId) {
                UserProfileRecord userProfileRecord = userService.getUserProfile(userId);

                return ResponseEntity.status(HttpStatus.OK).body(userProfileRecord);
        }

        @PutMapping("/updateUserProfile")
        public ResponseEntity<UserProfileRecord> updateUserProfile(
                        @RequestBody UserProfileRecord userProfileRecord) {
                UserProfileRecord userProfileRecordUpdated = userService.updateUserProfile(userProfileRecord);

                return ResponseEntity.status(HttpStatus.OK).body(userProfileRecordUpdated);
        }

        @PutMapping("/uploadProfilePicture/{userId}")
        public ResponseEntity<UserProfileRecord> uploadProfilePicture(
                        @PathVariable(name = "userId") String userId,
                        @RequestParam("profilePicture") MultipartFile profilePicture) {
                UserProfileRecord userProfileRecordUpdated = userService.uploadProfilePicture(userId, profilePicture);
                return ResponseEntity.status(HttpStatus.OK).body(userProfileRecordUpdated);
        }

}
