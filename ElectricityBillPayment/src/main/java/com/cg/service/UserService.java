package com.cg.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.User;
import com.cg.exception.DuplicateUserException;
import com.cg.exception.InvalidLoginCredentialException;
import com.cg.exception.NoSuchUserException;
import com.cg.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	/*
	 * User is registering, if already exists then it will handled by DuplicateUserException
	 */

	@Override
	public User registerUser(User user) throws DuplicateUserException {
		Optional<User> opt = userRepository.getUserByUserName(user.getUserName());
		if (opt.isPresent()) {
			throw new DuplicateUserException("User Already Exists!");
		} else {
			System.out.println("inside the register");
			return userRepository.save(user);
		}
	}
	
	/*
	 *If  User is already exists can Login , otherwise it will handled by InvalidLoginCredentialException
	 */

	@Override
	public User loginUser(User user) throws InvalidLoginCredentialException {

		
		return userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword())
				.orElseThrow(() -> new InvalidLoginCredentialException("Invalid Credentials"));

	}
	
	/*
	 * User can change the password
	 */

	@Override
	public User changePassword(User user) {
		Optional<User> opt = userRepository.findByUserName(user.getUserName());
		User existingUser = null;
		if (opt.isPresent()) {
			existingUser = opt.get();
			existingUser.setPassword(user.getPassword());
			userRepository.save(existingUser);
		} else {
			throw new NoSuchUserException("No User Exists!");
		}
		return existingUser;
	}
	/*
	 * We can search User by taking user name
	 */

	@Override
	public User searchUserByUsername(String username) throws NoSuchUserException {
		
		return userRepository.findByUserName(username).orElseThrow(() -> new NoSuchUserException("No User Exists!"));
	}
	/*
	 * We can search User by taking user Id 
	 */

	@Override
	public User searchUserByUserId(int userId) throws NoSuchUserException {
		return userRepository.findById((long) userId).orElseThrow(() -> new NoSuchUserException("No User Exists!"));
	}
	
	/*
	 * If user forget the password,he can change the password 
	 */

	@Override
	public String forgotPassword(String username) throws Exception {
		User u = userRepository.findByUserName(username).orElseThrow(() -> new Exception("No User found"));
		return u.getPassword();
	}

	
	


}
