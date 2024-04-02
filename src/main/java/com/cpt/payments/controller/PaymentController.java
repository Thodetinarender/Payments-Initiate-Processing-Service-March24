package com.cpt.payments.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpt.payments.constants.Endpoints;
import com.cpt.payments.dto.Transaction;
import com.cpt.payments.pojo.TransactionReqRes;
import com.cpt.payments.service.PaymentStatusService;
import com.cpt.payments.util.TransactionMapper;

@RestController
@RequestMapping(Endpoints.PAYMENTS)
public class PaymentController {
	private static final Logger LOGGER = LogManager.getLogger(PaymentController.class);

   
   @Autowired
   TransactionMapper transactionMapper;
    @Autowired
	PaymentStatusService paymentStatusService;
   
	@PostMapping(value = Endpoints.STATUS_UPDATE)
	public ResponseEntity<TransactionReqRes> processPaymentStatus(
			@RequestBody TransactionReqRes transactionReqRes) {
		
		
		//Transaction texDTO =  modelMapper.map(transactionReqRes, Transaction.class);
		
		//System.out.println(" payment request is -> " + transactionReqRes);
		LOGGER.debug(" payment request is -> " + transactionReqRes);
		
		Transaction transaction = transactionMapper.toDTO(transactionReqRes);
		//System.out.println("Convaerted to textDTO:" + transaction);
		LOGGER.info("Convaerted to textDTO:" + transaction);
		
         Transaction response = paymentStatusService.updatePaymentStatus(transaction);
//        
		TransactionReqRes responseObject = 
				transactionMapper.toResponseObject(transaction);
		return ResponseEntity.ok(responseObject);
		
		//return ResponseEntity.ok(transactionReqRes); 
	
	}

}
