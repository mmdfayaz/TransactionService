package com.ncs.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.model.Transaction;
import com.ncs.model.TransactionRs;
import com.ncs.service.TransactionService;
//mark class as Controller
@RestController
public class TransactionController 
{
//autowire the TransactionService class
@Autowired
TransactionService transactionService;
//creating a get mapping that retrieves all the transaction detail from the database 
@GetMapping("/transaction")
private List<Transaction> getAllTransaction() 
{
return transactionService.getAllTransaction();
}
//creating a get mapping that retrieves the detail of a specific transaction
@GetMapping("/transaction/{transactionid}")
private Transaction getTransaction(@PathVariable("transactionid") int transactionid) 
{
return transactionService.getTransactionById(transactionid);
}

//creating post mapping that post the transaction detail in the database
@PostMapping("/transaction")
private TransactionRs createTransaction(@RequestBody Transaction transactionDTO) 
{
	TransactionRs transactionRs=transactionService.validateTransaction(transactionDTO);
		
		if (transactionRs.getErrorCode()!=null) {
			return transactionRs;
		}
		 
	 transactionService.createTransaction(transactionDTO,transactionRs);
return transactionRs;
}
}
