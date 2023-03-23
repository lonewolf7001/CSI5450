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
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.Agent;
import edu.oakland.csi5450.bean.NewAgent;
import edu.oakland.csi5450.util.DaoFailedException;

@Repository
public class AgentDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Agent getAgentById(int id)
	{
		final String query = "SELECT agent_id, first_name, last_name, phone, email from AGENT where agent_id=?";
		Object[] params = { id };
		int[] types = { Types.INTEGER };
		try {
			List<Agent> result = jdbcTemplate.query(query, params, types,  new RowMapper<Agent>(){
	
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
			return result.isEmpty() ? null : result.get(0);
		} catch(DataAccessException e) {
			throw new DaoFailedException(e);
		}
	}

	/**
	 * 
	 * @param agent
	 * @return the id of the newly created agent
	 */
	@Transactional
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

	@Transactional
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
	
}
