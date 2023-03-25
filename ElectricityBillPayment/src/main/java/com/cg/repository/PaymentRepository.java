package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

	@Query(value = "SELECT c from Payment c where c.billPayment.billForReading.readingForConnection.consumerNumber=?1")
	public List<Payment> readHistoricalPaymentByConsumerNumber(Long consumerNumber);
	
}
