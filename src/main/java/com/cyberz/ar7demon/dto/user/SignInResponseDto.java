package com.cyberz.ar7demon.dto.user;

import lombok.Data;

@Data
public class SignInResponseDto {
    private String token;
    private String refreshedToken;
}
