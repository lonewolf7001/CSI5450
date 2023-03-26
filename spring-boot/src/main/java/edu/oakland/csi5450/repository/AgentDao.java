package edu.oakland.csi5450.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.oakland.csi5450.bean.Agent;
import edu.oakland.csi5450.bean.NewAgent;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository
public class AgentDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Agent> getAgents() {
		final String query = "SELECT agent_id, first_name, last_name, phone, email from AGENT";
		try {
			return jdbcTemplate.query(query, new RowMapper<Agent>(){
	
				@Override
				public Agent mapRow(ResultSet rs, int i) throws SQLException
				{
					Agent resp = new Agent();
					resp.setId(rs.getInt("agent_id"));
					resp.setFirstName(rs.getString("first_name"));
					resp.setLastName(rs.getString("last_name"));
					resp.setPhone(rs.getLong("phone"));
					resp.setEmail(rs.getString("email"));
					return resp;
				}
			});
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public Agent getAgentById(int id)
	{
		final String query = "SELECT agent_id, first_name, last_name, phone, email from AGENT where agent_id=?";
		Object[] params = { id };
		int[] types = { Types.INTEGER };
		try {
			List<Agent> result = jdbcTemplate.query(query, params, types,  getRowMapper());
			return result.isEmpty() ? null : result.get(0);
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	public List<Agent> getAgentsByCompanyId(int companyId) {
		final String query = "SELECT a.agent_id, a.first_name, a.last_name, a.phone, a.email from AGENT a, AGENT_COMPANY_MAPPING m where m.agent_id=a.agent_id and m.company_id=?";
		Object[] params = { companyId };
		int[] types = { Types.INTEGER };
		try {
		return jdbcTemplate.query(query,  params, types, getRowMapper());
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

	/**
	 * 
	 * @param agent
	 * @return the id of the newly created agent
	 */
	public int createAgent(NewAgent agent)
	{
		final String query = "INSERT INTO AGENT (first_name, last_name, phone, email) values (?,?,?,?) RETURNING agent_id";
		Object[] params = {agent.getFirstName(), agent.getLastName(), agent.getPhone(), agent.getEmail()};
		int[] types = {Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.VARCHAR};
		return jdbcTemplate.queryForObject(query, params, types, new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet rs, int i) throws SQLException
			{
				return rs.getInt(1);
			}
		});
	}

	public boolean updateAgent(Agent agent)
	{
		if(getAgentById(agent.getId()) == null)
			return false;
		final String query = "UPDATE AGENT SET first_name = ?, last_name = ?, phone = ?, email = ? WHERE agent_id = ?";
		int result = jdbcTemplate.update(query, agent.getFirstName(), agent.getLastName(), agent.getPhone(), agent.getEmail(), agent.getId());
		if(result != 1)
			throw new DaoFailedException("Error while updating agent");
		return true;
	}
	
	public void addAgentCompany(int agentId, int companyId) {
		final String query = "INSERT INTO AGENT_COMPANY_MAPPING (agent_id, company_id) VALUES (?,?)";
		try {
			int result = jdbcTemplate.update(query, agentId, companyId);
			if(result != 1)
				throw new DaoFailedException("Error occurred while adding an agent-company mapping");
			
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

	public boolean doesAgentCompanyMappingExist(int agentId, int companyId) {
		final String query = "SELECT agent_id, company_id from AGENT_COMPANY_MAPPING where agent_id=? and company_id=?";
		Object[] params = { agentId, companyId };
		int[] types = { Types.INTEGER, Types.INTEGER };
		try {
			List<Integer> result = jdbcTemplate.query(query, params, types, new RowMapper<Integer>(){

				@Override
				public Integer mapRow(ResultSet arg0, int arg1) throws SQLException
				{
					return 1;
				}
				
			});
			return !result.isEmpty();
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}
	
	private RowMapper<Agent> getRowMapper() {
		return new RowMapper<Agent>(){
			
			@Override
			public Agent mapRow(ResultSet rs, int i) throws SQLException
			{
				Agent resp = new Agent();
				resp.setId(rs.getInt("agent_id"));
				resp.setFirstName(rs.getString("first_name"));
				resp.setLastName(rs.getString("last_name"));
				resp.setPhone(rs.getLong("phone"));
				resp.setEmail(rs.getString("email"));
				return resp;
			}
		};
	}
}
