package co.edu.udistrital.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spinn3r.log5j.Logger;

import co.edu.udistrital.dao.UserDao;
import co.edu.udistrital.exception.UserAlreadyExistsException;
import co.edu.udistrital.exception.UserNotFoundException;
import co.edu.udistrital.domain.user.User;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	protected static final Logger logger = Logger.getLogger();
	
	protected static final String SELECT =
			"SELECT "
			+ "u_id,"
			+ "u_username,"
			+ "u_password "
			+ "FROM users ";

	protected static final String BY_ID =
			"WHERE u_id = ? ";
	
	protected static final String BY_USERNAME =
			"WHERE u_username = ? ";
	
	protected static final String SELECT_BY_ID =
			SELECT 
			+ BY_ID;
	
	protected static final String SELECT_BY_USERNAME =
			SELECT 
			+ BY_USERNAME;
	
	protected static final String SELECT_ALL =
			SELECT;
	
	protected static final String INSERT =
			"INSERT INTO users ("
			+ "u_username,"
			+ "u_password"
			+ ") VALUES (?,?) ";
	
	protected static final String UPDATE =
			"UPDATE users SET "
			+ "u_password = ? "
			+ "WHERE u_id = ? ";
	
	protected static final String DELETE =
			"DELETE FROM users "
			+ "WHERE u_id = ? ";
	
	@Autowired
	protected UserMapper userMapper;
	@Autowired
	protected DataSource dataSource;
	
	@PostConstruct
	public void loadDataSource() {
		setDataSource(dataSource);
	}
	
	public User retrieveUserById(long id) throws UserNotFoundException {
		try {
			return getJdbcTemplate().queryForObject(
					SELECT_BY_ID, 
					userMapper,
					id
			);
		} catch (EmptyResultDataAccessException ex) {
			throw new UserNotFoundException("User not found in database", ex);
		}
	}

	public User retrieveUserByUsername(String username) throws UserNotFoundException {
		try {
			return getJdbcTemplate().queryForObject(
					SELECT_BY_USERNAME, 
					userMapper,
					username
			);
		} catch (EmptyResultDataAccessException ex) {
			throw new UserNotFoundException("User not found in database", ex);
		}
	}

	public List<User> retrieveUserList() {
		return getJdbcTemplate().query(
				SELECT_ALL, 
				userMapper
		);
	}

	@Transactional
	public void insertUser(User user) throws UserAlreadyExistsException {
		try {
			getJdbcTemplate().update(
					INSERT, 
					user.getUsername(), 
					user.getPassword()
			);
		} catch (DuplicateKeyException ex) {
			throw new UserAlreadyExistsException("User already exists in database", ex);
		}
	}

	public void updateUser(User user) throws UserNotFoundException {
		int updatedRows = getJdbcTemplate().update(
				UPDATE,
				user.getPassword(),
				user.getId()
		);
		if(updatedRows <= 0) {
			throw new UserNotFoundException("User not found in database");
		}
	}

	public void deleteUser(long id) throws UserNotFoundException {
		int deletedRows = getJdbcTemplate().update(
				DELETE,
				id
		);
		if(deletedRows <= 0) {
			throw new UserNotFoundException("User not found in database");
		}
	}

}
