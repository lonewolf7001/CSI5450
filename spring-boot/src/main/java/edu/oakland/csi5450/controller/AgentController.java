package edu.oakland.csi5450.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.oakland.csi5450.bean.Agent;
import edu.oakland.csi5450.bean.Commission;
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
	
	@GetMapping("/")
	public List<Agent> getAllAgents() {
		return agentService.getAllAgents();
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Agent> getAgentById(@PathVariable int id) {
		Agent resp = agentService.getAgentById(id);
		if(resp == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	@GetMapping("/commission/{id}")
	public ResponseEntity<Commission> getCommissionForAgent(@PathVariable @Min(1) int id) {
		Commission c = agentService.getCommission(id);
		if(c == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> createAgent(@Valid @RequestBody NewAgent agent) {
		agentService.sanitizeAgent(agent);
		NewAgentResponse resp = agentService.createAgent(agent);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateAgent(@Valid @RequestBody Agent req) {
		agentService.sanitizeAgent(req);
		if(!agentService.updateAgent(req))
			return new ResponseEntity<>(new ErrorResponse("Cannot Update this Agent. Agent does not exist."), HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/company/add")
	public ResponseEntity<Object> addAgentCompanyMapping(@RequestParam @NotNull @Min(1) Integer agentId, @RequestParam @NotNull @Min(1) Integer companyId) {
		if(!agentService.addAgentCompany(agentId, companyId))
			return new ResponseEntity<>(new ErrorResponse("Cannot add this agent to this company. Either the agent or the company doesn't exist or the mapping already exists."), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/company/{companyId}")
	public ResponseEntity<List<Agent>> getAgentByCompanyId(@PathVariable int companyId) {
		List<Agent> agents = agentService.getAgentsByCompanyId(companyId);
		if(agents.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(agents, HttpStatus.OK);
	}
}
