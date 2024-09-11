package com.cyberz.ar7demon.service;

import com.cyberz.ar7demon.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
    @Autowired
    private AgentRepository agentRepository;

}
