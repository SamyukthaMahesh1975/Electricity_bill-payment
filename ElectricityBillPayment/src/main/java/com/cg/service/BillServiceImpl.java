package com.cg.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Bill;
import com.cg.entity.ConnectionType;
import com.cg.entity.Reading;
import com.cg.exception.InvalidReadingException;
import com.cg.exception.NoSuchConnectionException;
import com.cg.exception.NoSuchCustomerException;
import com.cg.repository.BillRepository;
import com.cg.repository.ReadingRepository;

@Service
public class BillServiceImpl implements IBillService {
	@Autowired
	private BillRepository billRepository;

	@Autowired
	private ReadingRepository readingRepository;

	/*
	 * Method to Generate Bill from a Reading Object,
	 * if reading object is invalid it throws an User Defined Exception 
	 * 
	 */
	@Override
	public Bill generateBill(Reading reading) throws InvalidReadingException {
		Bill bill = new Bill();
		Bill generatedBill = null;
		try {
		Long readingId = reading.getReadingId();
		Reading existingReading = readingRepository.findById(readingId).get();
		Double pricePerUnit = existingReading.getPricePerUnits();
		Double unitsConsumed = existingReading.getUnitsConsumed();
		bill.setBillDate(new Date());
		LocalDate d= LocalDate.now().plusDays(20);
		bill.setBillDueDate(d);
		bill.setBillForReading(existingReading);
		bill.setUnitsConsumed(unitsConsumed);
		bill.setBillAmount(pricePerUnit * unitsConsumed);
		generatedBill=billRepository.save(bill);
		}
		catch(Exception e){
			throw new InvalidReadingException("Invalid Reading is Provided");
		}
		return generatedBill;
	}
	
	/*
	 * Method to fetch bill based on ConsumerNumber,
	 * if ConsumerNumber does not present then it throw an NoSuchConnectionException
	 */

	@Override
	public Bill viewBillByConsumerNumber(Long consumerNumber) throws NoSuchConnectionException {
		return billRepository.viewBillByConsumerNumber(consumerNumber)
				.orElseThrow(() -> new NoSuchConnectionException("No Connection Exist!"));

	}
	/*
	 * Method to fetch bill based on Mobile Number,
	 * if Mobile Number does not present then it throw an NoSuchCustomerException
	 */
	@Override
	public Bill viewBillByMobileNumber(String mobileNumber) throws NoSuchCustomerException {
		return billRepository.viewBillByMobileNumber(mobileNumber)
				.orElseThrow(() -> new NoSuchCustomerException("No Customer Exist!"));
	}
	
	
	/*
	 * Method to fetch bill based on Email,
	 * if Email does not present then it throw an NoSuchCustomerException
	 */
	@Override
	public Bill viewBillByEmail(String email) throws NoSuchCustomerException {
		return billRepository.viewBillByEmail(email)
				.orElseThrow(() -> new NoSuchCustomerException("Bill Is Not available for given email :" + email));
	}
	
	/*
	 * Method which returns list of Bills that were generated between the given dates
	 * 
	 */
	
	@Override
	public List<Bill> viewBillForDateRange(Date from, Date to) throws NoSuchCustomerException {
		try {
			return billRepository.findAllByBillDateBetween(from, to);
		} catch (Exception e) {
			throw new NoSuchCustomerException("Bill Is Not Available For Date from" + from + "to" + to);
		}
	}
	
	/*
	 * Method which sends the bill based on Consumer Number to the given emailId
	 */

	@Override
	public void emailBillToCustomer(Long consumerNumber, String email) throws NoSuchConnectionException {
		
		System.out.println(("email bill to customer" + viewBillByConsumerNumber(consumerNumber)));

	}
	
	
	
	/*
	 * Method to calculate the Amount for energy used for a specific ConnectionType
	 * 
	 * Per Unit Price is taken from Internet
	 * 
	 */
	@Override
	public double energyBillCalculator(ConnectionType connectionType, double units) {
		
		if(connectionType==ConnectionType.AGRICULTURAL) {
			return units*5;
		}
		if(connectionType==ConnectionType.INDUSTRIAL) {
			return units*8;
		}
		else {
			return units*6;
		}
	}

}
