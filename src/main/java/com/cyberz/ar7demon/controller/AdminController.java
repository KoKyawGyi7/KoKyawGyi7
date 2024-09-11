package com.cyberz.ar7demon.controller;

import com.cyberz.ar7demon.dto.requestDto.CreateEntityRequestDto;
import com.cyberz.ar7demon.dto.user.ResponseDto;
import com.cyberz.ar7demon.dto.user.SignInDto;
import com.cyberz.ar7demon.dto.user.SignInResponseDto;
import com.cyberz.ar7demon.model.entity.Admin;
import com.cyberz.ar7demon.security.JWTService;
import com.cyberz.ar7demon.security.UserDetailServiceForAdmin;
import com.cyberz.ar7demon.service.AdminService;
import com.cyberz.ar7demon.service.AgentService;
import com.cyberz.ar7demon.service.SeniorMasterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController  {

    @Autowired
    private SeniorMasterService seniorMasterService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailServiceForAdmin userDetailServiceForAdmin;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<SignInResponseDto> login(@RequestBody SignInDto signInDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(),signInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailServiceForAdmin.loadUserByUsername(signInDto.getEmail());
        String token = jwtService.generateToken(userDetails);
        String refreshedToken = jwtService.refreshedToken(token);
        SignInResponseDto signInResponseDto = new SignInResponseDto();
        signInResponseDto.setToken(token);
        signInResponseDto.setRefreshedToken(refreshedToken);
        return new ResponseEntity<>(signInResponseDto,HttpStatus.OK);
    }

    @PostMapping("/createSeniorMaster")
    public ResponseEntity<ResponseDto> createSeniorMaster(@RequestBody CreateEntityRequestDto requestDto, HttpServletRequest request){
       String token= jwtService.getJWTFromRequest(request);
        Admin admin = jwtService.getAdminFromToken(token);
        ResponseDto response = seniorMasterService.create(requestDto,admin);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/createMaster")
    public void createMaster(){

    }
    @PostMapping("/createAgent")
    public void createAgent(){

    }
    @PostMapping("/createUser")
    public void createUser(){

    }
    @GetMapping("/listSeniorMaster")
    public void listSeniorMaster(){

    }

    @GetMapping("/listMaster")
    public void listMaster(){

    }

    @GetMapping("/listAgent")
    public void listAgent(){

    }

    @GetMapping("/listUser")
    public void listUser(){

    }


}
