package com.lambda.com.demo.services;

import com.lambda.com.demo.models.Agent;

public interface AgentService {
    Agent save(Agent agent);

    Agent findAgentById(long id);
}
