package com.agathiyaracademy.constant;

import com.agathiyaracademy.entity.Admin;
import com.agathiyaracademy.entity.Student;
import lombok.Builder;

@Builder
public class ConstantRecord {
    @Builder
    public record UserResponse(String name, String emailId, String phoneNumber) {
    }

    @Builder
    public record UserRequest(String name, String password, String emailId, String phoneNumber,
                              Student student, Admin admin) {
    }

    @Builder
    public record StudentResponse(String rollNumber, String tnpscGroup, String tnpscRegistrationNumber,
                                  boolean isFeePaid, String address, String emailId, String name, String phoneNumber) {

    }

    @Builder
    public record StudentRequest(String rollNumber, String tnpscGroup, String tnpscRegistrationNumber,
                                 boolean isFeePaid, String address, String emailId) {

    }

    @Builder
    public record AdminRequest(String emailId) {

    }

    @Builder
    public record AdminResponse(String emailId) {

    }
}
