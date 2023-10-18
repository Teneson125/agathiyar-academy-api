package com.agathiyaracademy.service;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.entity.User;
import com.agathiyaracademy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
//     constructor of userRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    Here we save the user details
    public ConstantRecord.UserResponse saveUser(ConstantRecord.UserRequest userRequest) {
//        userRequest is null
        if (userRequest == null) {
            return null;
        }
//         userRequest is null so the userResponse is also null
        ConstantRecord.UserResponse userResponse = null;
//        check the given user-data is valid & given RegisteredEmailId is not null
        if (isValidUserData(userRequest) && !isEmailIdRegistered(userRequest.emailId())) {
            User user = null;
            user = User.builder().password(userRequest.password()).emailId(userRequest.emailId()).phoneNumber(userRequest.phoneNumber()).name(userRequest.name()).build();
            userRepository.save(user);
            userResponse = ConstantRecord.UserResponse.builder().emailId(user.getEmailId()).phoneNumber(user.getPhoneNumber()).name(user.getName()).build();
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
    //    check the given user-data is valid(true or false)
    //    send the UserRequest from ConstantRecord
    private boolean isValidUserData(ConstantRecord.UserRequest userRequest) {
    //    Then we return the userRequest(password,phoneNumber,emailId,name) is compulsory not null
        return userRequest.password() != null && userRequest.phoneNumber() != null && userRequest.emailId() != null && userRequest.name() != null;
    }

    public ConstantRecord.UserResponse getUser(String emailId) {
        if(emailId==null){
            return null;
        }
        ConstantRecord.UserResponse userResponse = null;
        User user = fetchUserByEmailId(emailId);
        if(user!=null){
            ConstantRecord.StudentResponseForUserResponse studentResponse = null;
            if(user.getStudent()!=null){
                studentResponse = ConstantRecord.StudentResponseForUserResponse.builder().rollNumber(user.getStudent().getRollNumber()).tnpscGroup(user.getStudent().getTnpscGroup()).isFeePaid(user.getStudent().isFeePaid()).address(user.getStudent().getAddress()).tnpscRegistrationNumber(user.getStudent().getTnpscRegistrationNumber()).build();
            }
            userResponse = ConstantRecord.UserResponse.builder().name(user.getName()).phoneNumber(user.getPhoneNumber()).emailId(user.getEmailId()).student(studentResponse).build();
        }
        return userResponse;
    }
}