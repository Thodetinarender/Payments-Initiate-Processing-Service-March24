package com.cpt.payments.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cpt.payments.constants.ErrorCodeEnum;
import com.cpt.payments.constants.TransactionStatusEnum;
import com.cpt.payments.dto.Transaction;
import com.cpt.payments.exception.PaymentProcessingException;
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
		
		if(statusHndler == null) {
			System.out.println(" invalid transaction handler -> " + transaction.getTxnStatusId());
			
//			throw new PaymentProcessingException(HttpStatus.INTERNAL_SERVER_ERROR,
//					ErrorCodeEnum.TRANSACTION_STATUS_HANDLER_NOT_FOUND.getErrorCode(),
//					ErrorCodeEnum.TRANSACTION_STATUS_HANDLER_NOT_FOUND.getErrorMessage());
		}
		
		boolean isUpdated = statusHndler.updateStatus(transaction); 
		
		System.out.println ("Txn Status is upadted| isUpadted: "+ isUpdated + "|statusEnum" + statusEnum);
		 if(!isUpdated) {
			 System.out.println("Faild to update transaction");
	      //exist transaction
			 throw new PaymentProcessingException(HttpStatus.INTERNAL_SERVER_ERROR,
						ErrorCodeEnum.TRANSACTION_STATUS_UPDATE_FAILED.getErrorCode(),
						ErrorCodeEnum.TRANSACTION_STATUS_UPDATE_FAILED.getErrorMessage());
		 }
		 
		 System.out.println("Transaction updated successfully");
		return transaction;
	}

}
