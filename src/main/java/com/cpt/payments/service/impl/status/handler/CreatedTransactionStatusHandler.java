package com.cpt.payments.service.impl.status.handler;

import org.springframework.stereotype.Service;

import com.cpt.payments.dto.Transaction;
import com.cpt.payments.service.TransactionStatusHandler;

@Service
public class CreatedTransactionStatusHandler extends TransactionStatusHandler {

	@Override
	public boolean updateStatus(Transaction transaction) {
		
		System.out.println("CreatedTransactionStatusHandler.updateStatus()");
		
		return false;
	}
	

}
