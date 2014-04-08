package co.edu.udistrital.dao;

import java.util.List;

import co.edu.udistrital.exception.UserAlreadyExistsException;
import co.edu.udistrital.exception.UserNotFoundException;
import co.edu.udistrital.domain.user.User;

public interface UserDao {

	User retrieveUserById(long id) throws UserNotFoundException;
	
    User retrieveUserByUsername(String username) throws UserNotFoundException;
	
    List<User> retrieveUserList();
	
    void insertUser(User user) throws UserAlreadyExistsException;
	
    void updateUser(User user) throws UserNotFoundException;
	
    void deleteUser(long id) throws UserNotFoundException;
}
