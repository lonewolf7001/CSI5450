<<<<<<< HEAD
package edu.oakland.csi5450.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.Agent;
import edu.oakland.csi5450.bean.NewAgent;
import edu.oakland.csi5450.bean.NewAgentResponse;
import edu.oakland.csi5450.repository.AgentDao;

@Service
public class AgentService
{
	@Autowired
	AgentDao agentDao;

	/**
	 * returns null if no agent with the given id was found
	 * @param id
	 * @return
	 */
	public Agent getAgentById(int id)
	{
		return agentDao.getAgentById(id);
	}
	
	public NewAgentResponse createAgent(NewAgent agent) {
		int id = agentDao.createAgent(agent);
		NewAgentResponse resp = new NewAgentResponse();
		resp.setId(id);
		return resp;
	}
	
	/**
	 * 
	 * @param agent
	 * @return true if update was successful, false if the id did not exist
	 */
	public boolean updateAgent(Agent agent)
	{
		return agentDao.updateAgent(agent);
	}
	
	public void sanitizeAgent(NewAgent agent) {
		agent.setFirstName(agent.getFirstName().toUpperCase());
		agent.setLastName(agent.getLastName().toUpperCase());
		agent.setEmail(agent.getEmail().toUpperCase());
	}

}
=======
package edu.oakland.csi5450.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.oakland.csi5450.bean.Agent;
import edu.oakland.csi5450.bean.NewAgent;
import edu.oakland.csi5450.bean.NewAgentResponse;
import edu.oakland.csi5450.repository.AgentDao;

@Service
public class AgentService
{
	@Autowired
	AgentDao agentDao;

	/**
	 * returns null if no agent with the given id was found
	 * @param id
	 * @return
	 */
	public Agent getAgentById(int id)
	{
		return agentDao.getAgentById(id);
	}
	
	public NewAgentResponse createAgent(NewAgent agent) {
		int id = agentDao.createAgent(agent);
		NewAgentResponse resp = new NewAgentResponse();
		resp.setId(id);
		return resp;
	}
	
	/**
	 * 
	 * @param agent
	 * @return true if update was successful, false if the id did not exist
	 */
	public boolean updateAgent(Agent agent)
	{
		return agentDao.updateAgent(agent);
	}

	
	/**
	 * validates and sanitizes the given agent. Returns the error message, or null if the bean is valid
	 * @param agent
	 * @return
	 */
	public String validateNewAgent(NewAgent agent) {
		if(isNullOrEmpty(agent.getFirstName()))
			return "firstName is required";
		if(isNullOrEmpty(agent.getLastName()))
			return "lastName is required";
		if(agent.getPhone() < 1000000000 || agent.getPhone() > 9999999999L)
			return "phone must be exactly 10 digits";
		if(isNullOrEmpty(agent.getEmail()))
			return "email is required";
		if(agent.getFirstName().length() > 25)
			return "firstName may be no more than 25 characters";
		if(agent.getLastName().length() > 30)
			return "lastName may be no more than 30 characters";
		if(agent.getEmail().length() > 50)
			return "email may be no more than 50 characters";
		
		agent.setFirstName(agent.getFirstName().toUpperCase());
		agent.setLastName(agent.getLastName().toUpperCase());
		agent.setEmail(agent.getEmail().toUpperCase());
		
		return null;	
	}

	private boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}
	
	public String validateAgent(Agent agent) {
		if(agent.getId() < 0)
			return "invalid agent id";
		return validateNewAgent(agent);
	}

}
>>>>>>> b20760b (Synced with calib backend springboot pages)
