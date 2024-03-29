package com.cpt.payments.dao;

import com.cpt.payments.dto.Transaction;

public interface TransactionDao {
	public Transaction createTransaction(Transaction transaction);

}
