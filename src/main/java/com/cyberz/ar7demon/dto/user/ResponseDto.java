package com.cyberz.ar7demon.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseDto {
    private int status;
    private String message;
    private Date timeStamp;
}
