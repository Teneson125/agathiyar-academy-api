package com.agathiyaracademy.service;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.entity.Student;
import com.agathiyaracademy.entity.User;
import com.agathiyaracademy.repository.StudentRepository;
import com.agathiyaracademy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    public UserService(UserRepository userRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    public ConstantRecord.UserResponse saveUser(ConstantRecord.UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        ConstantRecord.UserResponse userResponse = null;
        //check the given user-data is valid & given RegisteredEmailId is not null
        if (isValidUserData(userRequest) && !isEmailIdRegistered(userRequest.emailId())) {
            User user = null;
            user = User.builder().password(userRequest.password()).emailId(userRequest.emailId()).phoneNumber(userRequest.phoneNumber()).name(userRequest.name()).build();
            userRepository.save(user);
            userResponse = ConstantRecord.UserResponse.builder().emailId(user.getEmailId()).phoneNumber(user.getPhoneNumber()).name(user.getName()).build();
        }
        return userResponse;
    }

    public ConstantRecord.UserResponse updateUser(ConstantRecord.UserRequestForUserUpdate userRequest) {
        if (userRequest == null) {
            return null;
        }
        ConstantRecord.UserResponse userResponse = null;
        ConstantRecord.StudentResponseForUserResponse studentResponse = null;
        if (isValidUserData(userRequest) && isEmailIdRegistered(userRequest.userEmailId())) {
            //check do we need to update email id (if both same no need to update email)
            User user = null;
            user = userRepository.findByEmailId(userRequest.userEmailId());
            if (!userRequest.emailId().equals(userRequest.userEmailId())) {
                //check if the new email id is registered or not
                if (isEmailIdRegistered(userRequest.emailId())) {
                    //return if the new email id is registered user
                    return userResponse;
                }
                //set the new email id ( this will remove the old email id and replace new email id_
                user.setEmailId(userRequest.emailId());
            }
            user.setName(userRequest.name());
            user.setPhoneNumber(userRequest.phoneNumber());

            //check do we need to update student
            if (userRequest.student() != null) {
                //check the student data is valid or not
                if (isValidStudentData(userRequest.student())) {
                    Student student = null;
                    student = user.getStudent();
                    student.setAddress(userRequest.student().address());
                    student.setRollNumber(userRequest.student().rollNumber());
                    student.setTnpscGroup(userRequest.student().tnpscGroup());
                    student.setFeePaid(userRequest.student().isFeePaid());
                    if (userRequest.student().tnpscRegistrationNumber() != null) {
                        // update the tnpsc registration number only if not null
                        student.setTnpscRegistrationNumber(userRequest.student().tnpscRegistrationNumber());
                    }
                    studentRepository.save(student);

                    studentResponse = ConstantRecord.StudentResponseForUserResponse.builder()
                            .address(student.getAddress())
                            .rollNumber(student.getRollNumber())
                            .isFeePaid(student.isFeePaid())
                            .tnpscGroup(student.getTnpscGroup())
                            .tnpscRegistrationNumber(student.getTnpscRegistrationNumber())
                            .build();
                } else {
                    return userResponse;
                }
            }

            userRepository.save(user);
            userResponse = ConstantRecord.UserResponse.builder()
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .emailId(user.getEmailId())
                    .student(studentResponse)
                    .build();
        }

        return userResponse;
    }

    private boolean isValidStudentData(ConstantRecord.StudentRequestForUserUpdate student) {
        return student.rollNumber() != null && student.address() != null && student.tnpscGroup() != null;
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

    private boolean isValidUserData(ConstantRecord.UserRequestForUserUpdate userRequest) {
        return userRequest.userEmailId() != null && userRequest.emailId() != null && userRequest.name() != null && userRequest.phoneNumber() != null;
    }

    public ConstantRecord.UserResponse getUser(String emailId) {
        if (emailId == null) {
            return null;
        }
        ConstantRecord.UserResponse userResponse = null;
        User user = fetchUserByEmailId(emailId);
        if (user != null) {
            ConstantRecord.StudentResponseForUserResponse studentResponse = null;
            if (user.getStudent() != null) {
                studentResponse = ConstantRecord.StudentResponseForUserResponse.builder().rollNumber(user.getStudent().getRollNumber()).tnpscGroup(user.getStudent().getTnpscGroup()).isFeePaid(user.getStudent().isFeePaid()).address(user.getStudent().getAddress()).tnpscRegistrationNumber(user.getStudent().getTnpscRegistrationNumber()).build();
            }
            userResponse = ConstantRecord.UserResponse.builder().name(user.getName()).phoneNumber(user.getPhoneNumber()).emailId(user.getEmailId()).student(studentResponse).build();
        }
        return userResponse;
    }
}