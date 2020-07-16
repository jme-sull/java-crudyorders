package local.jmesull.orders.repositories;

import local.jmesull.orders.models.Agent;
import org.springframework.data.repository.CrudRepository;

//Database Object Access
public interface AgentRepository extends CrudRepository<Agent, Long>
{
}
