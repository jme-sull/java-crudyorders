package local.jmesull.orders.controllers;


import local.jmesull.orders.models.Agent;
import local.jmesull.orders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/agents")
public class AgentController
{
    @Autowired
    private AgentServices agentService;

    // http://localhost:2019/agents/agent/{id}

    @GetMapping(value = "agent/{id}", produces = "application/json")
    public ResponseEntity<?> findAgentById(@PathVariable long id)
    {
            Agent a = agentService.findById(id);
            return new ResponseEntity<>(a, HttpStatus.OK);
    }





}
