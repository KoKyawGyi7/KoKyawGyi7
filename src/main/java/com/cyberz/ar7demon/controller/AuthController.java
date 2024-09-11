package com.cyberz.ar7demon.controller;

import com.cyberz.ar7demon.dto.user.SignInDto;
import com.cyberz.ar7demon.dto.user.SignInResponseDto;
import com.cyberz.ar7demon.dto.user.SignUpDto;
import com.cyberz.ar7demon.model.entity.User;
import com.cyberz.ar7demon.repository.AgentRepository;
import com.cyberz.ar7demon.repository.TokenRepository;
import com.cyberz.ar7demon.repository.UserRepository;
import com.cyberz.ar7demon.security.UserDetailServiceForUser;
import com.cyberz.ar7demon.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private  JWTService jwtService;
    @Autowired
    private UserDetailServiceForUser userDetailServiceForUser;
    @Autowired
    private  TokenRepository tokenRepository;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto){
        if (userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already existed.", HttpStatus.BAD_REQUEST);

        }
        User user = new User();
        user.setName(signUpDto.getFirstName()+" "+ signUpDto.getLastName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        userRepository.save(user);
        return new ResponseEntity<>("Register Success.",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponseDto> login(@RequestBody SignInDto signInDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(),signInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailServiceForUser.loadUserByUsername(signInDto.getEmail());
        String token = jwtService.generateToken(userDetails);
        String refreshedToken = jwtService.refreshedToken(token);
        SignInResponseDto signInResponseDto = new SignInResponseDto();
        signInResponseDto.setToken(token);
        signInResponseDto.setRefreshedToken(refreshedToken);
        return new ResponseEntity<>(signInResponseDto,HttpStatus.OK);
    }
}
