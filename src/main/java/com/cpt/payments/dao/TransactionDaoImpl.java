package com.cpt.payments.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cpt.payments.dto.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao{

	@Autowired
	 private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Transaction createTransaction(Transaction transaction) {
		String sql = "INSERT INTO Transaction (userId, paymentMethodId, providerId, paymentTypeId, amount, currency, txnStatusId, txnReference, txnDetailsId, providerCode, providerMessage, debitorAccount, creditorAccount, providerReference, merchantTransactionReference, retryCount) " +
                "VALUES (:userId, :paymentMethodId, :providerId, :paymentTypeId, :amount, :currency, :txnStatusId, :txnReference, :txnDetailsId, :providerCode, :providerMessage, :debitorAccount, :creditorAccount, :providerReference, :merchantTransactionReference, :retryCount)";

   MapSqlParameterSource params = new MapSqlParameterSource();
   params.addValue("userId", transaction.getUserId());
   params.addValue("paymentMethodId", transaction.getPaymentMethodId());
   params.addValue("providerId", transaction.getProviderId());
   params.addValue("paymentTypeId", transaction.getPaymentTypeId());
   params.addValue("amount", transaction.getAmount());
   params.addValue("currency", transaction.getCurrency());
   params.addValue("txnStatusId", transaction.getTxnStatusId());
   params.addValue("txnReference", transaction.getTxnReference());
   params.addValue("txnDetailsId", transaction.getTxnDetailsId());
   params.addValue("providerCode", transaction.getProviderCode());
   params.addValue("providerMessage", transaction.getProviderMessage());
   params.addValue("debitorAccount", transaction.getDebitorAccount());
   params.addValue("creditorAccount", transaction.getCreditorAccount());
   params.addValue("providerReference", transaction.getProviderReference());
   params.addValue("merchantTransactionReference", transaction.getMerchantTransactionReference());
   params.addValue("retryCount", transaction.getRetryCount());

   try {	
	   KeyHolder keyHolder =new GeneratedKeyHolder();
	   int rowUpdated = jdbcTemplate.update(sql, params, keyHolder);
	   transaction.setId(keyHolder.getKey().intValue());
	   
	   System.out.println("Successfully inserted data| rowUpdated:" + rowUpdated);
   }catch(Exception e) {
	   System.out.println("Exception createTransaction:" +e);
	   return null;
   }
   
   return transaction;
	}

}
