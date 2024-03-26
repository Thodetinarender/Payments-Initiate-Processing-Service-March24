package com.cpt.payments.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpt.payments.constants.TransactionStatusEnum;
import com.cpt.payments.dto.Transaction;
import com.cpt.payments.service.PaymentStatusService;
import com.cpt.payments.service.TransactionStatusHandler;
import com.cpt.payments.service.factory.TransactionStatusFactory;

@Service
public class PaymentStatusServiceImpl implements PaymentStatusService {

	@Autowired
	TransactionStatusFactory transactionStatusFactory;
	
	@Override
	public Transaction updatePaymentStatus(Transaction transaction) {
		System.out.println("System invoked service class: updatePaymentStatus");
		
		
		TransactionStatusEnum statusEnum = TransactionStatusEnum.getTransactionStatusEnum(
				transaction.getTxnStatusId());
		
		//Factory
		
		TransactionStatusHandler statusHndler= transactionStatusFactory.getStatusHandler(statusEnum);
		
		boolean isUpdated = statusHndler.updateStatus(transaction);             
		            System.out.println ("Status is upadted| isUpadted: "+ isUpdated + "|statusEnum" + statusEnum);
		return transaction;
	}

}
