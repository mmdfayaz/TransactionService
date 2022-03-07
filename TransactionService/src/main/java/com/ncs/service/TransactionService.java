package com.ncs.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ncs.model.AccountMapping;
import com.ncs.model.Customer;
import com.ncs.model.Transaction;
import com.ncs.model.TransactionRs;
import com.ncs.repository.TransactionRepository;
//defining the business logic
@Service
public class TransactionService 
{
@Autowired
TransactionRepository transactionRepository;
//getting all transaction record by using the method findaAll() of JpaRepository
@Transactional(readOnly = true)
public List<Transaction> getAllTransaction() 
{
List<Transaction> transaction = new ArrayList<Transaction>();
transactionRepository.findAll().forEach(transaction1 -> transaction.add(transaction1));
return transaction;
}
//getting a specific record by using the method findById() of JpaRepository
@Transactional(readOnly = true)
public Transaction getTransactionById(int id) 
{
return transactionRepository.findById(id).get();
}
//saving a specific record by using the method save() of JpaRepository
@Transactional(propagation = Propagation.REQUIRED)
public Transaction createTransaction(Transaction transaction,TransactionRs transactionRs) 
{
    Date date = new Date();
    String url="http://192.168.1.6:8082/accountMapping";
	transaction.setCreatedDate(date);
	AccountMapping senderDetails=transactionRs.getSenderacctDetails();
	senderDetails.setAmount(senderDetails.getAmount()-transaction.getTransactionAmt());
	AccountMapping ac=new AccountMapping();
	ac.setAccount(senderDetails.getAccount());
	ac.setAccountMappingid(senderDetails.getAccountMappingid());
	ac.setAmount(senderDetails.getAmount());
	ac.setCustomerId(senderDetails.getCustomerId());
	RestTemplate restTemplate=new RestTemplate();
	 restTemplate.postForEntity(url, ac, AccountMapping.class);
	AccountMapping recevierDetails=transactionRs.getRecevieracctDetails();
	recevierDetails.setAmount(recevierDetails.getAmount()+transaction.getTransactionAmt());
	AccountMapping recevierAc=new AccountMapping();
	recevierAc.setAccount(recevierDetails.getAccount());
	recevierAc.setAccountMappingid(recevierDetails.getAccountMappingid());
	recevierAc.setAmount(recevierDetails.getAmount());
	recevierAc.setCustomerId(recevierDetails.getCustomerId());
	restTemplate.postForEntity(url, recevierAc, AccountMapping.class);
	transactionRepository.save(transaction);
	return transaction;
}

public TransactionRs validateTransaction(Transaction transaction) 
{
	RestTemplate restTemplate=null;
	TransactionRs transactionRs =new TransactionRs();
	if(transaction.getSender()<0) {
		transactionRs.setErrorCode("SenderID");
		transactionRs.setErrorDescription("please enter senderId");
	    return transactionRs; 
	}
	if(transaction.getReceiver()<0) {
		transactionRs.setErrorCode("ReceiverID");
		transactionRs.setErrorDescription("please enter RecevierId");
	    return transactionRs; 
	}
	if(transaction.getSenderAcct()<0) {
		transactionRs.setErrorCode("AccountID");
		transactionRs.setErrorDescription("please enter sender AccountID");
	    return transactionRs; 
	}
	if(transaction.getReceiverAcct()<0) {
		transactionRs.setErrorCode("ReceiverID");
		transactionRs.setErrorDescription("please enter receiver AccountID");
	    return transactionRs; 
	}
	if(transaction.getTransactionAmt()<0) {
		transactionRs.setErrorCode("Transaction Amt");
		transactionRs.setErrorDescription("please enter transaction amt");
	    return transactionRs; 
	}
	restTemplate=new RestTemplate();
	String customerUrl="http://192.168.1.6:8081/customer/";
	String senderUrl=customerUrl+transaction.getSender();
	transaction.setSender(restTemplate.getForObject(senderUrl, Customer.class).getCustomerId());
	if(transaction.getSender()<0) {
		transactionRs.setErrorCode("SenderID");
		transactionRs.setErrorDescription("please enter valid senderId");
	    return transactionRs; 
	}
	String receiverUrl=customerUrl+transaction.getReceiver();
	transaction.setReceiver(restTemplate.getForObject(receiverUrl, Customer.class).getCustomerId());
	if(transaction.getReceiver()<0) {
		transactionRs.setErrorCode("ReceiverID");
		transactionRs.setErrorDescription("please enter valid RecevierId");
	    return transactionRs; 
	}
	String accountUrl="http://192.168.1.6:8082/accountMapping/";
	String senderAcctId=accountUrl+transaction.getSenderAcct();
	transactionRs.setSenderacctDetails(restTemplate.getForObject(senderAcctId, AccountMapping.class));
	transaction.setSenderAcct(transactionRs.getSenderacctDetails().getAccountMappingid());
	if(transaction.getSenderAcct()<0) {
		transactionRs.setErrorCode("AccountID");
		transactionRs.setErrorDescription("please enter valid sender AccountID");
	    return transactionRs; 
	}
	String receiverAcctId=accountUrl+transaction.getReceiverAcct();
	transactionRs.setRecevieracctDetails(restTemplate.getForObject(receiverAcctId, AccountMapping.class));
	transaction.setReceiverAcct(transactionRs.getRecevieracctDetails().getAccountMappingid());
	if(transaction.getReceiverAcct()<0) {
		transactionRs.setErrorCode("ReceiverID");
		transactionRs.setErrorDescription("please enter valid receiver AccountID");
	    return transactionRs; 
	}
	if(!((transactionRs.getSenderacctDetails().getAmount()-transaction.getTransactionAmt())>0)) {
		transactionRs.setErrorCode("SenderAccountBalance");
		transactionRs.setErrorDescription("please check sender Account balance");
		return transactionRs;
	}
return transactionRs;
}

}