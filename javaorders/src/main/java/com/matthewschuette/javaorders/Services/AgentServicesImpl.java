package com.matthewschuette.javaorders.Services;

import com.matthewschuette.javaorders.Models.Agents;
import com.matthewschuette.javaorders.Repositories.AgentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "agentService")
public class AgentServicesImpl implements AgentServices {
    @Autowired
    private AgentsRepo agentsrepos;

    @Override
    public Agents save(Agents agents) {
        return agentsrepos.save(agents);
    }

    @Override
    public Agents findAgentById(long id) throws EntityNotFoundException {
        return agentsrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent Id " + id + " Not Found"));
    }
}
