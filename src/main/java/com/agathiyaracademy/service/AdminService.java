package com.agathiyaracademy.service;

import com.agathiyaracademy.entity.Admin;
import com.agathiyaracademy.entity.User;
import com.agathiyaracademy.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean saveAdmin(Admin admin, User user) {
        admin.setUser(user);
        adminRepository.save(admin);
        return true;
    }
}
