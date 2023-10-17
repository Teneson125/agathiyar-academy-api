package com.agathiyaracademy.controller;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("save")
    public ResponseEntity<ConstantRecord.AdminResponse> saveUser(@RequestBody ConstantRecord.AdminRequest adminRequest) {
        return new ResponseEntity<>(adminService.saveAdmin(adminRequest), HttpStatus.OK);
    }
}
