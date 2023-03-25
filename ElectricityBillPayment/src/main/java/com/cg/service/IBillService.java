package com.cg.service;

import java.util.Date;
import java.util.List;

import com.cg.entity.Bill;
import com.cg.entity.ConnectionType;
import com.cg.entity.Reading;
import com.cg.exception.InvalidReadingException;
import com.cg.exception.NoSuchConnectionException;
import com.cg.exception.NoSuchCustomerException;

public interface IBillService 
{
	public Bill generateBill(Reading reading) throws InvalidReadingException;
	public Bill viewBillByConsumerNumber(Long consumerNumber) throws NoSuchConnectionException;
	public Bill viewBillByMobileNumber(String mobile) throws NoSuchCustomerException;
	public Bill viewBillByEmail(String email) throws NoSuchCustomerException;
	public List<Bill> viewBillForDateRange(Date from, Date to) throws NoSuchCustomerException;
	public double energyBillCalculator(ConnectionType connectionType ,double units);
	public void emailBillToCustomer(Long consumerNumber, String email) throws NoSuchConnectionException;
	
	

}
