package com.lambda.com.demo.services;


import com.lambda.com.demo.models.Agent;
import com.lambda.com.demo.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value="agentservices")
public class AgentServiceImpl  implements AgentService{
    @Autowired
    private AgentsRepository agentsRepository;
    @Transactional
    @Override
    public Agent save(Agent agent) {return agentsRepository.save(agent);}
}
