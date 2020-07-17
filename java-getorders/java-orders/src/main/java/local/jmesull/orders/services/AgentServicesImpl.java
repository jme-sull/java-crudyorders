package local.jmesull.orders.services;


import local.jmesull.orders.models.Agent;
import local.jmesull.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "agentService")
public class AgentServicesImpl implements AgentServices
{
    @Autowired
    AgentRepository agentrepos;

    @Transactional
    @Override
    public Agent save(Agent agent)
    {
        return agentrepos.save(agent);
    }

    @Override
    public Agent findById(long id)
    {
        Agent a = new Agent();
        a = agentrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found"));
        return a;
    }
}
