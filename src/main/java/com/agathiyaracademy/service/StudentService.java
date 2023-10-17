package com.agathiyaracademy.service;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.entity.Student;
import com.agathiyaracademy.entity.User;
import com.agathiyaracademy.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserService userService;

    public StudentService(StudentRepository studentRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
    }

    public ConstantRecord.StudentResponse saveStudent(ConstantRecord.StudentRequest studentRequest) {
        if (studentRequest == null) {
            return null;
        }
        ConstantRecord.StudentResponse studentResponse = null;
        if (isValidStudentData(studentRequest) && userService.isEmailIdRegistered(studentRequest.emailId())) {
            User user = null;
            user = userService.fetchUserByEmailId(studentRequest.emailId());
            if (user != null) {
                Student student = null;
                student = Student.builder().rollNumber(studentRequest.rollNumber()).isFeePaid(studentRequest.isFeePaid()).address(studentRequest.address()).tnpscGroup(studentRequest.tnpscGroup()).tnpscRegistrationNumber(studentRequest.tnpscRegistrationNumber()).build();
                studentRepository.save(student);
                student.setUser(user);
//                TODO( we created the user in given entity and also perfectly created student entity but the student will create duplicate)
                studentRepository.save(student);
                studentResponse = ConstantRecord.StudentResponse.builder().name(student.getUser().getName()).emailId(student.getUser().getEmailId()).address(student.getAddress()).phoneNumber(student.getUser().getPhoneNumber()).tnpscGroup(student.getTnpscGroup()).isFeePaid(student.isFeePaid()).rollNumber(student.getRollNumber()).tnpscRegistrationNumber(student.getTnpscRegistrationNumber()).build();
            }
        }
        return studentResponse;
    }

    private boolean isValidStudentData(ConstantRecord.StudentRequest studentRequest) {
        return studentRequest.rollNumber() != null && studentRequest.tnpscGroup() != null && studentRequest.address() != null;
    }
}
