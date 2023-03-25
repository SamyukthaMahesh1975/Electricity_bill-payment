package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Customer extends User {
  
	public String customerId;
	
	@Column(unique = true, nullable = false)
	@NotNull(message = "Aadhaar no is Required")
    @Size(min = 12, max = 12, message = "Please enter 12 digit AADHAR!")
	private String aadhaarNumber;

	@NotEmpty(message = "Name is Required")
	@Size(min = 3, max = 25, message = "Invalid Customer Name please enter a vaild Customer Name!")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the name")
	private String firstName;

	@NotEmpty(message = "Name is Required")
	@Size(min = 3, max = 25, message = "Invalid Customer Name please enter a vaild Customer Name!")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the name")
	
	private String middleName;

	@NotEmpty(message = "Name is Required")
	@Size(min = 3, max = 25, message = "Invalid Customer Name please enter a vaild Customer Name!")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the name")
	private String lastName;
	
	@Column(unique = true, nullable = false)
	@NotEmpty(message = "Mobile no is Required")
	@Size(min = 10, max = 10, message = "Invalid Phone Number please enter a vaild phone number of minimum 10 digits")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid input:Enter numbers only")
	private String mobileNumber;
	
	@Column(unique = true, nullable = false)
	@Email
	@NotBlank
	@Size(min = 2, max = 30, message = "Invalid Email ID please enter a vaild email ID")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+", message = "enter email in valid format")
	private String email;
	
	@Column
	@NotEmpty(message = "Gender can't be empty!")
	@Size(min = 4, max = 6, message = "Please enter Male/Female/Others")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the gender")
	private String gender;
	
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", aadhaarNumber=" + aadhaarNumber + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber
				+ ", email=" + email + ", gender=" + gender + "]";
	}
	public Customer(@NotNull(message = "Aadhaar no is Required") String aadhaarNumber,
			@NotEmpty(message = "Name is Required") String firstName, String middleName,
			@NotEmpty(message = "Name is Required") String lastName,
			@NotEmpty(message = "Mobile no is Required") String mobileNumber,
			@Email @NotBlank @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+", message = "enter email in valid format") String email,
			String gender,String customerId) {
		super();
		this.aadhaarNumber = aadhaarNumber;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.gender = gender;
		this.customerId=customerId;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
