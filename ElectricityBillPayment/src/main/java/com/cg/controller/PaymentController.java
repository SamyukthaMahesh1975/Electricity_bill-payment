package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Payment;
import com.cg.entity.PaymentStatus;
import com.cg.exception.NoSuchCustomerException;
import com.cg.service.IPaymentService;

@RestController
@RequestMapping(value="/payment")
public class PaymentController {

	@Autowired
	private IPaymentService paymentService;

	@PostMapping(value = "/payBill")
	public ResponseEntity<PaymentStatus> payBill(@Valid @RequestBody Payment payment)
	{
		PaymentStatus paymentStatus= paymentService.payBill(payment);
		
		return new ResponseEntity<PaymentStatus>(paymentStatus, HttpStatus.CREATED);
	}

	@GetMapping(value="/consumerNumber/{consumerNumber}")
	public ResponseEntity<List<Payment>> viewHistoricalPayment(Long consumerNumber)throws NoSuchCustomerException
	{
		List<Payment> readByconsumerNumber=paymentService.viewHistoricalPayment(consumerNumber);
		return new ResponseEntity<List<Payment>>(readByconsumerNumber,HttpStatus.OK);				
	}
	
	@GetMapping("/sendEmailOnPaymentCompletion")
	public ResponseEntity<?> sendEmailOnPaymentCompletion(@RequestParam Long consumerId, @RequestParam String email){
		paymentService.sendEmailOnPaymentCompletion(consumerId, email);
		return new ResponseEntity<>("Payment was Successufully" + email, HttpStatus.OK);
	}
}
