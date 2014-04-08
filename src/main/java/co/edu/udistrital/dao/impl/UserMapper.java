package co.edu.udistrital.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.udistrital.domain.user.User;

@Component
public class UserMapper implements RowMapper<User> {

	protected static final String ID = "u_id";
	protected static final String USERNAME = "u_username";
	protected static final String PASSWORD = "u_password";
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(ID));
		user.setUsername(rs.getString(USERNAME));
		user.setPassword(rs.getString(PASSWORD));
		return user;
	}
}
