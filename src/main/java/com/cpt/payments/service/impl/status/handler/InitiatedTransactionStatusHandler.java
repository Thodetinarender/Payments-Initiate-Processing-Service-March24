package com.cpt.payments.service.impl.status.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cpt.payments.constants.TransactionStatusEnum;
import com.cpt.payments.dao.TransactionDao;
import com.cpt.payments.dto.Transaction;
import com.cpt.payments.service.TransactionStatusHandler;

@Component
public class InitiatedTransactionStatusHandler extends TransactionStatusHandler {

	@Autowired
	private TransactionDao transactionDao;

	@Override
	public boolean updateStatus(Transaction transaction) {
		System.out.println(" transaction INITATED -> " + transaction);
		
		transaction.setTxnStatusId(TransactionStatusEnum.INITIATED.getId());
		transaction.setTxnDetailsId(TransactionStatusEnum.INITIATED.getId());
		
		String fromStatus= TransactionStatusEnum
				.getTransactionStatusEnum(transactionDao.getTransactionById(transaction.getId())
						.getTxnStatusId())
						.getName();
		
		boolean transactionStatus = transactionDao.updateTransaction(transaction);
		
		if (!transactionStatus) {
			System.out.println(" updating transaction failed -> " + transaction);
			return false;
		}
		
		updateTxnLog(
				transaction.getId(),
				fromStatus,
				TransactionStatusEnum.INITIATED.getName());

		return true;
	}

}