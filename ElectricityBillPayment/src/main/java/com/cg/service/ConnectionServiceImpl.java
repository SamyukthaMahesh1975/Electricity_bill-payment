package com.cg.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Connection;
import com.cg.entity.Customer;
import com.cg.exception.NoSuchConnectionException;
import com.cg.exception.NoSuchCustomerException;
import com.cg.repository.ConnectionRepository;
import com.cg.repository.CustomerRepository;


@Service
public class ConnectionServiceImpl implements IConnectionService {

	@Autowired
	private ConnectionRepository connectionRepository;

	
	/*
	 * If connection does not exists,User can get the new connection by registering,
	 * If Users connection already exists it throws an exception 
	 */
	@Override
	public Connection newConnectionRequest(Connection newConnection) throws Exception {

		Optional<Connection> c = connectionRepository.findById(newConnection.getConnectionId());
		if (c.isPresent()) {
			throw new Exception("Connection already exist with id :" + newConnection.getConnectionId());
		}

		Customer customer = newConnection.getCustomerConnection();
		Long userId = customer.getUserId();
		Connection con;
		if (userId == 0) {
			newConnection.setConnectionDate(new Date());
			Long random1 = (long)Math.abs(new Random().nextInt(1000000));
			newConnection.setConsumerNumber(random1);
			newConnection.setApplicationDate(new Date());
			con = connectionRepository.save(newConnection);
		} else {
			Customer existingCustomer = newConnection.getCustomerConnection();
			newConnection.setCustomerConnection(existingCustomer);
			newConnection.setConnectionDate(new Date());
			newConnection.setConsumerNumber((long)Math.abs(new Random().nextInt(1000000)));
			newConnection.setApplicationDate(new Date());
			con = connectionRepository.save(newConnection);

		}

		return con;
	}
	
	
	/*
	 * User can search by taking consumer number,
	 * If customer does not exists it throws no such customer exception
	 */
	@Override
	public Customer searchCustomerByConsumerNumber(Long consumerNumber) throws NoSuchCustomerException {
		Connection c = connectionRepository.findByConsumerNumber(consumerNumber)
				.orElseThrow(() -> new NoSuchConnectionException("No Connection Exists!"));
		Customer c1 = new Customer();
		c1.setAadhaarNumber(c.getCustomerConnection().getAadhaarNumber());
		c1.setEmail(c.getCustomerConnection().getEmail());
		c1.setFirstName(c.getCustomerConnection().getFirstName());
		c1.setGender(c.getCustomerConnection().getGender());
		c1.setLastName(c.getCustomerConnection().getLastName());
		c1.setMiddleName(c.getCustomerConnection().getMiddleName());
		c1.setMobileNumber(c.getCustomerConnection().getMobileNumber());
		c1.setPassword(c.getCustomerConnection().getPassword());
		c1.setUserId(c.getCustomerConnection().getUserId());
		c1.setUserName(c.getCustomerConnection().getUserName());
		return c1;

	}
	
	
	/*
	 * We can Search Active connection by taking village name,
	 * If the village does not exits it throws no such connection exception
	 */
	@Override
	public List<Connection> findActiveConnectionsByVillage(String village) throws NoSuchConnectionException {
		List<Connection> connection = connectionRepository.getActiveConnectionsByVillage(village);
		if (connection == null) {
			throw new NoSuchConnectionException("connections are not available for this village name:" + village);
		} else {
			return connection;
		}

	}
	
	/*
	 *  We can Search Active connection by taking Taluk name,
	 * If the village does not exits it throws no such connection exception
	 */
	@Override
	public List<Connection> findActiveConnectionsByTaluk(String taluk) throws NoSuchConnectionException {
		return connectionRepository.getActiveConnectionsByTaluk(taluk);
	}
	
	/*
	 *  We can Search Active connection by taking district name,
	 * If the village does not exits it throws no such connection exception
	 */
	@Override
	public List<Connection> findActiveConnectionsByDistrict(String district) throws NoSuchConnectionException {
		return connectionRepository.getActiveConnectionsByDistrict(district);
	}
	
	/*
	 *  We can Search Active connection by taking pin code ,
	 * If the village does not exits it throws no such connection exception
	 */
	@Override
	public List<Connection> findActiveConnectionsByPincode(String pincode) throws NoSuchConnectionException {
		return connectionRepository.getActiveConnectionsByPincode(pincode);
	}
	
	
	/*
	 *  We can Search InActive connection by taking village name,
	 * If the village does not exits it throws no such connection exception
	 */
	@Override
	public List<Connection> findInActiveConnectionsByVillage(String village) throws NoSuchConnectionException {
		return connectionRepository.getInActiveConnectionsByVillage(village);
	}
	
	
	/*
	 *  We can Search InActive connection by taking Taluk name,
	 * If the village does not exits it throws no such connection exception
	 */
	@Override
	public List<Connection> findInActiveConnectionsByTaluk(String taluk) throws NoSuchConnectionException {
		return connectionRepository.getInActiveConnectionsByTaluk(taluk);
	}
	
	
	/*
	 *  We can Search InActive connection by taking district name,
	 * If the village does not exits it throws no such connection exception
	 */

	@Override
	public List<Connection> findInActiveConnectionsByDistrict(String district) throws NoSuchConnectionException {
		return connectionRepository.getInActiveConnectionsByDistrict(district);
	}
	
	
	/*
	 *  We can Search InActive connection by taking pin code,
	 * If the village does not exits it throws no such connection exception
	 */

	@Override
	public List<Connection> findInActiveConnectionsByPincode(String pincode) throws NoSuchConnectionException {
		return connectionRepository.getInActiveConnectionsByPincode(pincode);
	}

}
