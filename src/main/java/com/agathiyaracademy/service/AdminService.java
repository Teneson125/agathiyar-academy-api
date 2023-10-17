package com.agathiyaracademy.service;

import com.agathiyaracademy.constant.ConstantRecord;
import com.agathiyaracademy.entity.Admin;
import com.agathiyaracademy.entity.User;
import com.agathiyaracademy.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserService userService;

    public AdminService(AdminRepository adminRepository, UserService userService) {
        this.adminRepository = adminRepository;
        this.userService = userService;
    }

    public ConstantRecord.AdminResponse saveAdmin(ConstantRecord.AdminRequest adminRequest) {
        if (adminRequest == null) {
            return null;
        }
        ConstantRecord.AdminResponse adminResponse = null;
        if (isValidAdminData(adminRequest) && userService.isEmailIdRegistered(adminRequest.emailId())) {
            User user = null;
            if (user != null) {
                user = userService.fetchUserByEmailId(adminRequest.emailId());
                Admin admin = null;
                admin.setUser(user);
                adminRepository.save(admin);
                adminResponse = ConstantRecord.AdminResponse.builder().emailId(admin.getUser().getName()).build();
            }
        }

        return adminResponse;
    }

    private boolean isValidAdminData(ConstantRecord.AdminRequest adminRequest) {
        return adminRequest.emailId() != null;
    }

}
