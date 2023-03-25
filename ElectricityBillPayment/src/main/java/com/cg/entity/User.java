package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long userId;

	@NotEmpty(message = "User Name is Required")
	@JoinColumn(name = "username")
	//@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*]).{6,14}$", message = "min 6, Max 14 characters,min one Upper case one lower case one special character")
	@Size(min = 3, max = 25, message = "Invalid User Name please enter a vaild User Name!")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the user name")
	private String userName;

	@NotEmpty(message = "Please enter password")
	@Size(min = 8, max = 20, message = "Password has to be of minimum 8 chars!")
	private String password;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}

	public User(Long userId, @NotEmpty(message = "User Name is Required") String userName,
			@NotEmpty(message = "Please enter password") String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
