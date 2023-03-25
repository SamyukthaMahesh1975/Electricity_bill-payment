package com.cg.service;

import java.util.List;

import com.cg.entity.Connection;
import com.cg.entity.Customer;
import com.cg.exception.NoSuchConnectionException;
import com.cg.exception.NoSuchCustomerException;

public interface IConnectionService {
	public Connection newConnectionRequest(Connection newConnection) throws Exception;

	public Customer searchCustomerByConsumerNumber(Long consumerNumber) throws NoSuchCustomerException;

	public List<Connection> findActiveConnectionsByVillage(String village) throws NoSuchConnectionException;

	public List<Connection> findActiveConnectionsByTaluk(String taluk) throws NoSuchConnectionException;

	public List<Connection> findActiveConnectionsByDistrict(String district) throws NoSuchConnectionException;

	public List<Connection> findActiveConnectionsByPincode(String pincode) throws NoSuchConnectionException;
	
	//For incative connections
	
	public List<Connection> findInActiveConnectionsByVillage(String village) throws NoSuchConnectionException;

	public List<Connection> findInActiveConnectionsByTaluk(String taluk) throws NoSuchConnectionException;

	public List<Connection> findInActiveConnectionsByDistrict(String district) throws NoSuchConnectionException;

	public List<Connection> findInActiveConnectionsByPincode(String pincode) throws NoSuchConnectionException;

}
