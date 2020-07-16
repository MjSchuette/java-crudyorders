package com.matthewschuette.javaorders.Controllers;

import com.matthewschuette.javaorders.Models.Agents;
import com.matthewschuette.javaorders.Services.AgentServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/agents")
public class AgentsController {
    @Autowired
    private AgentServices agentservice;

    // http://localhost:2019/agents/agent/9
    @GetMapping(value = "/agent/{agentid}")
    public ResponseEntity<?> getAgentById(@PathVariable long agentid) {
        Agents a = agentservice.findAgentById(agentid);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}
