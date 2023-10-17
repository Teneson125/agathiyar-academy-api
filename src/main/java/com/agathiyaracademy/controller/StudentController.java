package com.agathiyaracademy.controller;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("save")
    public ResponseEntity<ConstantRecord.StudentResponse> saveStudent(@RequestBody ConstantRecord.StudentRequest studentRequest) {
        return new ResponseEntity<>(studentService.saveStudent(studentRequest), HttpStatus.OK);
    }
}
