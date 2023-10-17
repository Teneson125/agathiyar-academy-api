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
        ConstantRecord.UserResponse userResponse = null;
        if (isValidUserData(userRequest) && !isEmailIdRegistered(userRequest.emailId())) {
            User user = null;
            user = User.builder().password(userRequest.password()).emailId(userRequest.emailId()).phoneNumber(userRequest.phoneNumber()).build();
            userRepository.save(user);
            userResponse = ConstantRecord.UserResponse.builder().emailId(userRequest.emailId()).phoneNumber(userRequest.phoneNumber()).build();
        }
        return userResponse;
    }

    public boolean isEmailIdRegistered(String emailId) {
        if (emailId == null) {
            return false;
        }
        return userRepository.findByEmailId(emailId) != null;
    }

    public User fetchUserByEmailId(String emailId) {
        if (emailId == null) {
            return null;
        }
        return userRepository.findByEmailId(emailId);
    }

    private boolean isValidUserData(ConstantRecord.UserRequest userRequest) {
        return userRequest.password() != null && userRequest.phoneNumber() != null && userRequest.emailId() != null && userRequest.name() != null;
    }
}