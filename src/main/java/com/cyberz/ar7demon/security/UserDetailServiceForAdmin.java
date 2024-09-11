package com.cyberz.ar7demon.security;

import com.cyberz.ar7demon.model.entity.Admin;
import com.cyberz.ar7demon.model.entity.Role;
import com.cyberz.ar7demon.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
@Configuration
public class UserDetailServiceForAdmin implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Email not found."));
        return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), (Collection<? extends GrantedAuthority>) mapRolesToAuthorities(admin.getRole()));
    }
    private GrantedAuthority mapRolesToAuthorities(Role role){
        return  new SimpleGrantedAuthority(role.name());
    }
}
