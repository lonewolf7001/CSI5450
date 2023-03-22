package edu.oakland.csi5450.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.oakland.csi5450.bean.Agent;
import edu.oakland.csi5450.bean.ErrorResponse;
import edu.oakland.csi5450.bean.NewAgent;
import edu.oakland.csi5450.bean.NewAgentResponse;
import edu.oakland.csi5450.service.AgentService;

@RestController
@RequestMapping("/agent")
public class AgentController
{
	@Autowired
	AgentService agentService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Agent> getAgentById(@PathVariable int id) {
		Agent resp = agentService.getAgentById(id);
		if(resp == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> createAgent(@Valid @RequestBody NewAgent agent) {
		agentService.sanitizeAgent(agent);
		NewAgentResponse resp = agentService.createAgent(agent);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateHomeOwner(@Valid @RequestBody Agent req) {
		agentService.sanitizeAgent(req);
		if(!agentService.updateAgent(req))
			return new ResponseEntity<>(new ErrorResponse("Cannot Update this Agent. Agent does not exist."), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
}