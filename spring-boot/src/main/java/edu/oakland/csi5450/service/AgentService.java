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
