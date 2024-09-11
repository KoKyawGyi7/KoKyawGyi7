package com.cyberz.ar7demon.service;

import com.cyberz.ar7demon.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterService {
    @Autowired
    private MasterRepository masterRepository;

}
