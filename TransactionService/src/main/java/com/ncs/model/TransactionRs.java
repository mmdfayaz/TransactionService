package com.ncs.model;

public class TransactionRs {

	private String errorCode;
	private String errorDescription;

	private Customer senderDetails;
	private AccountMapping senderacctDetails;
	private Customer recevierDetails;
	private AccountMapping recevieracctDetails;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public Customer getSenderDetails() {
		return senderDetails;
	}
	public void setSenderDetails(Customer senderDetails) {
		this.senderDetails = senderDetails;
	}
	public AccountMapping getSenderacctDetails() {
		return senderacctDetails;
	}
	public void setSenderacctDetails(AccountMapping senderacctDetails) {
		this.senderacctDetails = senderacctDetails;
	}
	public Customer getRecevierDetails() {
		return recevierDetails;
	}
	public void setRecevierDetails(Customer recevierDetails) {
		this.recevierDetails = recevierDetails;
	}
	public AccountMapping getRecevieracctDetails() {
		return recevieracctDetails;
	}
	public void setRecevieracctDetails(AccountMapping recevieracctDetails) {
		this.recevieracctDetails = recevieracctDetails;
	}
	
	
}
