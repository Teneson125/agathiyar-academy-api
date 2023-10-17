package com.agathiyaracademy.controller;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("save")
    public ResponseEntity<ConstantRecord.UserResponse> saveUser(@RequestBody ConstantRecord.UserRequest userRequest) {
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.OK);
    }
    @GetMapping("get")
    public ResponseEntity<ConstantRecord.UserResponse> getUser(@RequestParam String emailId){
        return new ResponseEntity<>(userService.getUser(emailId), HttpStatus.OK);
    }
}
