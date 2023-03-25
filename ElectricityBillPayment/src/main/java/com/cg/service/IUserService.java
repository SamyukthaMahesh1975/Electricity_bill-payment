package com.cg.service;
import com.cg.entity.User;
import com.cg.exception.DuplicateUserException;
import com.cg.exception.InvalidLoginCredentialException;
import com.cg.exception.NoSuchUserException;

public interface IUserService {

	
	public User registerUser(User user) throws DuplicateUserException;
	public User loginUser(User user) throws InvalidLoginCredentialException;
	public User changePassword(User user);
	public String forgotPassword(String username) throws Exception;

	public User searchUserByUsername(String username)throws NoSuchUserException;
	public User searchUserByUserId(int userId)throws NoSuchUserException;
}
