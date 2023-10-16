package com.agathiyaracademy.constant;

import com.agathiyaracademy.entity.Admin;
import com.agathiyaracademy.entity.Student;
import lombok.Builder;

@Builder
public class ConstantRecord {
    @Builder
    public record UserResponse(String userName, String emailId, String phoneNumber) {
    }

    public record UserRequest(String name, String userName, String password, String emailId, String phoneNumber,
                              Student student, Admin admin) {
    }
}
