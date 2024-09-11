package com.cyberz.ar7demon.service;

import com.cyberz.ar7demon.dto.requestDto.CreateEntityRequestDto;
import com.cyberz.ar7demon.dto.user.ResponseDto;
import com.cyberz.ar7demon.exception.CustomException;
import com.cyberz.ar7demon.model.entity.Admin;
import com.cyberz.ar7demon.model.entity.Role;
import com.cyberz.ar7demon.model.entity.SeniorMaster;
import com.cyberz.ar7demon.repository.SeniorMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SeniorMasterService {
    @Autowired
    private SeniorMasterRepository seniorMasterRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseDto create(CreateEntityRequestDto requestDto, Admin admin){
        if (seniorMasterRepository.existsByEmail(requestDto.getEmail())){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Email is already existed.");
            responseDto.setStatus(HttpStatus.BAD_REQUEST.value());
            responseDto.setTimeStamp(new Date());
            return responseDto;
        }
        SeniorMaster seniorMaster = new SeniorMaster();
        seniorMaster.setAdmin(admin);
        seniorMaster.setName(requestDto.getName());
        seniorMaster.setEmail(requestDto.getEmail());
        seniorMaster.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        seniorMaster.setPhone(requestDto.getPhone());
        seniorMaster.setRole(Role.MASTER);

        seniorMasterRepository.save(seniorMaster);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setTimeStamp(new Date());
        responseDto.setMessage("SeniorMaster Created Success.");
        responseDto.setStatus(HttpStatus.CREATED.value());
        return responseDto;
    }
    public ResponseDto unitUpdate(Integer unitAmount,Integer id,Character operate){
       SeniorMaster seniorMaster= seniorMasterRepository.findById(id).get();
       if (operate.equals('+')) {
           seniorMaster.setUnit(seniorMaster.getUnit()+unitAmount);
       } else if (operate.equals('-')) {
           seniorMaster.setUnit(seniorMaster.getUnit()-unitAmount);
       }else {
           throw new CustomException("Operate is invalid.");
       }

       ResponseDto responseDto = new ResponseDto();
       responseDto.setStatus(HttpStatus.OK.value());
       responseDto.setMessage("Unit Updated Success.");
       responseDto.setTimeStamp(new Date());
       return responseDto;
    }
}
