package com.cpt.payments.service.impl.provider.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cpt.payments.dto.ProcessPayment;
import com.cpt.payments.dto.ProcessPaymentResponse;
import com.cpt.payments.dto.Transaction;
import com.cpt.payments.service.ProviderHandler;
import com.cpt.payments.util.LogMessage;

@Service
public class StripeProviderHandler implements ProviderHandler {
	private static final Logger LOGGER = LogManager.getLogger(StripeProviderHandler.class);

	
	@Override
	public ProcessPaymentResponse processPayment(Transaction transaction, ProcessPayment processPayment) {
		// TODO Auto-generated method stub
		
		LogMessage.debug(LOGGER, "Ivoking Stripe Provider handler to call Stripe Provider service || transaction: " +transaction +"|processPayment:" +processPayment);
		
		ProcessPaymentResponse serviceeRespon = ProcessPaymentResponse.builder()
				.paymentReference("ref")
				.redirectUrl("http://redirect.s.com") //
				.build();	
		
		return serviceeRespon;
	}

}
