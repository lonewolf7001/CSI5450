package edu.oakland.csi5450.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.Agent;
import edu.oakland.csi5450.bean.Commission;
import edu.oakland.csi5450.bean.NewAgent;
import edu.oakland.csi5450.bean.NewAgentResponse;
import edu.oakland.csi5450.repository.AgentDao;
import edu.oakland.csi5450.repository.CompanyDao;

@Service
public class AgentService
{
	@Autowired
	AgentDao agentDao;

	@Autowired
	CompanyDao companyDao;
	
	public List<Agent> getAllAgents() {
		return agentDao.getAgents();
	}
	
	public List<Agent> getAgentsByCompanyId(int companyId) {
		return agentDao.getAgentsByCompanyId(companyId);
	}
	
	/**
	 * returns null if no agent with the given id was found
	 * @param id
	 * @return
	 */
	public Agent getAgentById(int id)
	{
		return agentDao.getAgentById(id);
	}
	
	@Transactional
	public Commission getCommission(int agentId) {
		if(agentDao.getAgentById(agentId) == null)
			return null;
		int commissionValue = agentDao.getTotalCommission(agentId);
		Commission result = new Commission();
		result.setCommission(commissionValue);
		return result;
	}
	
	@Transactional
	public NewAgentResponse createAgent(NewAgent agent) {
		int id = agentDao.createAgent(agent);
		NewAgentResponse resp = new NewAgentResponse();
		resp.setId(id+1);
		return resp;
	}
	
	/**
	 * 
	 * @param agent
	 * @return true if update was successful, false if the id did not exist
	 */
	@Transactional
	public boolean updateAgent(Agent agent)
	{
		if(agentDao.getAgentById(agent.getId()) == null)
			return false;
		agentDao.updateAgent(agent);
		return true;
	}
	
	public void sanitizeAgent(NewAgent agent) {
		agent.setFirstName(agent.getFirstName().toUpperCase());
		agent.setLastName(agent.getLastName().toUpperCase());
		agent.setEmail(agent.getEmail().toUpperCase());
	}

	@Transactional
	public boolean addAgentCompany(int agentId, int companyId) {
		if(companyDao.getCompany(companyId) == null
				|| agentDao.getAgentById(agentId) == null
				|| agentDao.doesAgentCompanyMappingExist(agentId, companyId))
			return false;
		agentDao.addAgentCompany(agentId, companyId);
		return true;
	}
}
