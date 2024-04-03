package com.cpt.payments.service;

import com.cpt.payments.dto.ProcessPaymentResponse;
import com.cpt.payments.dto.ProcessPayment;

public interface PaymentService {

	ProcessPaymentResponse processPayment(ProcessPayment processingServiceRequest);
	
	void processGetPaymentDetails();
}
