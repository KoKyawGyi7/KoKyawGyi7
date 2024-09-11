package com.cyberz.ar7demon.controller;

import com.cyberz.ar7demon.dto.user.SignInDto;
import com.cyberz.ar7demon.dto.user.SignInResponseDto;
import com.cyberz.ar7demon.security.JWTService;
import com.cyberz.ar7demon.security.UserDetailServiceForAdmin;
import com.cyberz.ar7demon.security.UserDetailServiceForUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTService jwtService;

    @MockBean
    private UserDetailServiceForAdmin userDetailServiceForAdmin;


    @Test
    public void testLogin(){
       SignInDto signInDto = new  SignInDto();
       signInDto.setEmail("admin@gmai.com");
       signInDto.setPassword("adm12345");

       Authentication authentication = mock(Authentication.class);
       UserDetails userDetail = mock(UserDetails.class);
       when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
       when(userDetailServiceForAdmin.loadUserByUsername(signInDto.getEmail())).thenReturn(userDetail);
       when(jwtService.generateToken(userDetail)).thenReturn("mockedToken");
       when(jwtService.refreshedToken("mockedToken")).thenReturn("mockedRefreshedToken");

        ResponseEntity<SignInResponseDto> response = adminController.login(signInDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("mockedToken", response.getBody().getToken());
        assertEquals("mockedRefreshedToken", response.getBody().getRefreshedToken());
    }






}