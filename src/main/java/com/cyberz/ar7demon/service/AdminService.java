package com.cyberz.ar7demon.service;

import com.cyberz.ar7demon.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
}
