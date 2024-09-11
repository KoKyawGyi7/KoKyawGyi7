package com.cyberz.ar7demon.security;

import com.cyberz.ar7demon.model.entity.Role;
import com.cyberz.ar7demon.model.entity.User;
import com.cyberz.ar7demon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailServiceForUser implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Email not found."));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), (Collection<? extends GrantedAuthority>) mapRolesToAuthorities(user.getRole()));
    }
    private GrantedAuthority mapRolesToAuthorities(Role role){
        return  new SimpleGrantedAuthority(role.name());
    }
}
