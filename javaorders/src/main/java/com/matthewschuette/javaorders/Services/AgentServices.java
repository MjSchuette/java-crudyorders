package com.matthewschuette.javaorders.Services;

import com.matthewschuette.javaorders.Models.Agents;

public interface AgentServices {
    Agents save(Agents agents);
    Agents findAgentById(long id);
}
