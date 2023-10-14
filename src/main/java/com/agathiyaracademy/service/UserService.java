package com.agathiyaracademy.service;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.entity.Admin;
import com.agathiyaracademy.entity.Student;
import com.agathiyaracademy.entity.User;
import com.agathiyaracademy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(ConstantRecord.UserRequest userRequest) {

        User user = User.builder()
                .userName(userRequest.userName())
                .password(userRequest.password())
                .emailId(userRequest.emailId())
                .phoneNumber(userRequest.phoneNumber())
                .roles(null)
                .build();
        userRepository.save(user);

        if (userRequest.student() != null) {
            Student student = getStudent(userRequest.student(), userRequest);
        }
        if (userRequest.admin() != null) {
            Admin admin = getAdmin(userRequest.admin(), userRequest);
        }


    }

    private Student getStudent(Student student, ConstantRecord.UserRequest userRequest) {
        return null;
    }

    private Admin getAdmin(Admin admin, ConstantRecord.UserRequest userRequest) {
        return null;
    }
}
