package com.agathiyaracademy.service;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.entity.User;
import com.agathiyaracademy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ConstantRecord.UserResponse saveUser(ConstantRecord.UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        if (isValidUserData(userRequest) && !isEmailIdRegistered(userRequest.emailId()) && !isUserNameRegistered(userRequest.userName())) {
            User user = User.builder().userName(userRequest.userName()).password(userRequest.password()).emailId(userRequest.emailId()).phoneNumber(userRequest.phoneNumber()).build();
            userRepository.save(user);
        }
        return ConstantRecord.UserResponse.builder().emailId(userRequest.emailId()).userName(userRequest.userName()).phoneNumber(userRequest.phoneNumber()).build();
    }

    private boolean isEmailIdRegistered(String emailId) {
        if (emailId == null) {
            return false;
        }
        return userRepository.findByEmailId(emailId) != null;
    }

    private boolean isUserNameRegistered(String userName) {
        if (userName == null) {
            return false;
        }
        return userRepository.findByUserName(userName) != null;
    }

    private boolean isValidUserData(ConstantRecord.UserRequest userRequest) {
        return userRequest.userName() != null && userRequest.password() != null && userRequest.phoneNumber() != null && userRequest.emailId() != null && userRequest.name() != null;
    }
}