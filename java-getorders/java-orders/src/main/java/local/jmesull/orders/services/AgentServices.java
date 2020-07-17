package local.jmesull.orders.services;

import local.jmesull.orders.models.Agent;

public interface AgentServices
{
    Agent save(Agent agent);

    Agent findById(long id);

}
