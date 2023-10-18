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

    //    constructor of StudentService,studentRepository
    public StudentService(StudentRepository studentRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
    }
    //    check the condition and then get the request of details from student & return send our response
    public ConstantRecord.StudentResponse saveStudent(ConstantRecord.StudentRequest studentRequest) {
    //        checking the studentRequest(details) is null or not
        if (studentRequest == null) {
            return null;
        }
        ConstantRecord.StudentResponse studentResponse = null;
        if (isValidStudentData(studentRequest) && userService.isEmailIdRegistered(studentRequest.emailId())) {
            User user = null;
            user = userService.fetchUserByEmailId(studentRequest.emailId());
            if (user != null && user.getStudent() == null) {
                Student student = null;
                student = Student.builder().rollNumber(studentRequest.rollNumber()).isFeePaid(studentRequest.isFeePaid()).address(studentRequest.address()).tnpscGroup(studentRequest.tnpscGroup()).tnpscRegistrationNumber(studentRequest.tnpscRegistrationNumber()).build();
                // save the student to the database
                studentRepository.save(student);
                // after we save the student now we're set the user to the student
                student.setUser(user);
                // save the student to update in database
                studentRepository.save(student);
                studentResponse = ConstantRecord.StudentResponse.builder().name(student.getUser().getName()).emailId(student.getUser().getEmailId()).address(student.getAddress()).phoneNumber(student.getUser().getPhoneNumber()).tnpscGroup(student.getTnpscGroup()).isFeePaid(student.isFeePaid()).rollNumber(student.getRollNumber()).tnpscRegistrationNumber(student.getTnpscRegistrationNumber()).build();
            }
        }
        return studentResponse;
    }
    //    check the given student-data is valid(true or false)
    //    send the StudentRequest from ConstantRecord
    private boolean isValidStudentData(ConstantRecord.StudentRequest studentRequest) {
    //     Then we return the studentRequest(rollNumber ,tnpscGroup ,address) is compulsory not null
        return studentRequest.rollNumber() != null && studentRequest.tnpscGroup() != null && studentRequest.address() != null;
    }
}
