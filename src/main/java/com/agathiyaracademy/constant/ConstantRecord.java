package com.agathiyaracademy.constant;

import com.agathiyaracademy.entity.Admin;
import com.agathiyaracademy.entity.Student;

public class ConstantRecord {
    public record UserResponse(String userName, String emailId, String phoneNumber) {
    }
    public record UserRequest(String userName, String password, String emailId, String phoneNumber, Student student, Admin admin){
    }
}
