package com.cpt.payments.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.cpt.payments.constants.TransactionStatusEnum;
import com.cpt.payments.service.TransactionStatusHandler;
import com.cpt.payments.service.impl.status.handler.CreatedTransactionStatusHandler;

@Component
public class TransactionStatusFactory {
	
	@Autowired
	private ApplicationContext context;
	
	public TransactionStatusHandler getStatusHandler(TransactionStatusEnum transactionStatusEnum) {
		
		System.out.println(" fetching transaction status handler for -> "+transactionStatusEnum);
		switch(transactionStatusEnum) {
		case CREATED:
			return context.getBean(CreatedTransactionStatusHandler.class);
		
		default:
			return null;
		}
	}

}
