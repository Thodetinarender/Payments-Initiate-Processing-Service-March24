package com.cpt.payments.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpt.payments.constants.Endpoints;
import com.cpt.payments.dto.ProcessPayment;
import com.cpt.payments.dto.ProcessPaymentResponse;
import com.cpt.payments.dto.Transaction;
import com.cpt.payments.pojo.PaymentResponse;
import com.cpt.payments.pojo.ProcessingServiceRequest;
import com.cpt.payments.pojo.TransactionReqRes;
import com.cpt.payments.service.PaymentStatusService;
import com.cpt.payments.util.LogMessage;
import com.cpt.payments.util.TransactionMapper;

@RestController
@RequestMapping(Endpoints.PAYMENTS)
public class PaymentController {
	private static final Logger LOGGER = LogManager.getLogger(PaymentController.class);
	
   @Autowired
   TransactionMapper transactionMapper;
    @Autowired
	PaymentStatusService paymentStatusService;
    @Autowired
    ModelMapper modelMapper;
    
    
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
	
	@PostMapping(value = Endpoints.PROCESS_PAYMENT)
	public ResponseEntity<PaymentResponse> processPayment(
			@RequestBody ProcessingServiceRequest processingServiceRequest) {
		LogMessage.setLogMessagePrefix("/PROCESS_PAYMENT");
		LogMessage.log(LOGGER, " processingServiceRequest is -> " + processingServiceRequest);

		//PaymentResponse response = paymentService.processPayment(processingServiceRequest);
		
		ProcessPayment processPaymentDTO =  modelMapper.map(processingServiceRequest, ProcessPayment.class);
		
		ProcessPaymentResponse serviceeRespon = ProcessPaymentResponse.builder()
				.paymentReference("ref")
				.redirectUrl("http://redirect.s.com")
				.build();	
		
		PaymentResponse response = modelMapper.map(serviceeRespon, PaymentResponse.class);
		
		LogMessage.log(LOGGER, " processPayment response:" + response);
		return ResponseEntity.ok(response);
	}

}
