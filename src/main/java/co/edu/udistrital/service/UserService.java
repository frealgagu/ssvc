package co.edu.udistrital.service;

import java.util.List;

import co.edu.udistrital.domain.user.User;
import co.edu.udistrital.exception.UserAlreadyExistsException;
import co.edu.udistrital.exception.UserNotFoundException;

public interface UserService {

    User retrieveUserById(long id) throws UserNotFoundException;
	
    User retrieveUserByUsername(String username) throws UserNotFoundException;
	
    List<User> retrieveUserList();
	
    void insertUser(User user) throws UserAlreadyExistsException;
	
    void updateUser(User user) throws UserNotFoundException;
	
    void deleteUser(long id) throws UserNotFoundException;   
	
}
