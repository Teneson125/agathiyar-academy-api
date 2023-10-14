package com.agathiyaracademy.service;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.entity.User;
import com.agathiyaracademy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StudentService studentService;
    private final AdminService adminService;

    public UserService(UserRepository userRepository, StudentService studentService, AdminService adminService) {
        this.userRepository = userRepository;
        this.studentService = studentService;
        this.adminService = adminService;
    }

    public void addUser(ConstantRecord.UserRequest userRequest) {
        User user = User.builder()
                .userName(userRequest.userName())
                .password(userRequest.password())
                .emailId(userRequest.emailId())
                .phoneNumber(userRequest.phoneNumber())
                .build();

        //check the user is student
        if (userRequest.student() != null && (!studentService.saveStudent(userRequest.student(), user))){
                return;
        }
        if(userRequest.admin() != null && (!adminService.saveAdmin(userRequest.admin(), user))){
            return;
        }
        userRepository.save(user);
    }

}
