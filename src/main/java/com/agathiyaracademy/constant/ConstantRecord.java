package com.agathiyaracademy.constant;

import lombok.Builder;

@Builder
public class ConstantRecord {
    @Builder
    public record UserResponse(String name, String emailId, String phoneNumber, StudentResponseForUserResponse student) {
    }

    @Builder
    public record UserRequest(String name, String password, String emailId, String phoneNumber) {
    }
    @Builder
    public record StudentResponseForUserResponse(String rollNumber, String tnpscGroup, String tnpscRegistrationNumber,
                                                 boolean isFeePaid, String address){

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
