package com.cpt.payments.constants;

import lombok.Getter;

public enum ErrorCodeEnum {
	GENERIC_EXCEPTION("20001","Something went wrong, please try later"),
	TRANSACTION_STATUS_HANDLER_NOT_FOUND("20005","transaction status handler not found"),
	TRANSACTION_STATUS_UPDATE_FAILED("20006","transaction status update failed"),
	
	TP_TRUSTLY_ERROR("20010","Unable to process payment at Trustly");
	
	@Getter
	private String errorCode;
	@Getter
	private String errorMessage;
	
	private ErrorCodeEnum(String errorCode, String errorMessage) {
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}
	
}