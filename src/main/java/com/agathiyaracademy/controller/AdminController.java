package com.agathiyaracademy.controller;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("save")
    public ResponseEntity<ConstantRecord.AdminResponse> saveUser(@RequestBody ConstantRecord.AdminRequest adminRequest) {
        return new ResponseEntity<>(adminService.saveAdmin(adminRequest), HttpStatus.OK);
    }
}
