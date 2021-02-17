package com.lambda.com.demo.repositories;

import com.lambda.com.demo.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agent, Long> {
}
