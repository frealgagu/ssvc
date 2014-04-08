package co.edu.udistrital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udistrital.dao.UserDao;
import co.edu.udistrital.domain.user.User;
import co.edu.udistrital.exception.UserAlreadyExistsException;
import co.edu.udistrital.exception.UserNotFoundException;
import co.edu.udistrital.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	protected UserDao userDao;

	@Override
	public User retrieveUserById(long id) throws UserNotFoundException {
		return userDao.retrieveUserById(id);
	}

	@Override
	public User retrieveUserByUsername(String username) throws UserNotFoundException {
		return userDao.retrieveUserByUsername(username);
	}

	@Override
	public List<User> retrieveUserList() {
        return userDao.retrieveUserList();
	}

	@Override
	public void insertUser(User user) throws UserAlreadyExistsException {
        userDao.insertUser(user);
	}

	@Override
	public void updateUser(User user) throws UserNotFoundException {
        userDao.updateUser(user);
	}

	@Override
	public void deleteUser(long id) throws UserNotFoundException {
        userDao.deleteUser(id);
	}
}
