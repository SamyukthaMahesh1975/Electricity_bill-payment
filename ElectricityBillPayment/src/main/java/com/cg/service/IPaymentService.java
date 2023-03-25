package com.cg.service;

import java.util.List;

import com.cg.entity.Payment;
import com.cg.entity.PaymentStatus;
import com.cg.exception.NoSuchCustomerException;

public interface IPaymentService
{
	public PaymentStatus payBill(Payment payment);
	public void sendEmailOnPaymentCompletion(Long consumerId, String email);
	public List<Payment> viewHistoricalPayment(Long consumerNumber)throws NoSuchCustomerException;
	

}
