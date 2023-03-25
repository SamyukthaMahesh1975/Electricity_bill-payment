package com.cg.service;

import com.cg.entity.Reading;
import com.cg.exception.NoSuchCustomerException;

public interface IReadingService {
	
	public Reading selfSubmit(Reading reading);
	public Reading findMeterReadingByConsumerNumber(Long consumerNumber)throws NoSuchCustomerException;
	
}



