package com.agathiyaracademy.service;

import com.agathiyaracademy.entity.Student;
import com.agathiyaracademy.entity.User;
import com.agathiyaracademy.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean saveStudent(Student student, User user) {
        if (isValidStudentData(student)) {
            student.setUser(user);
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    private boolean isValidStudentData(Student student) {
        return student.getAddress() != null && student.getTnpscGroup() != null && student.getTnpscRegistrationNumber() != null;
    }
}
