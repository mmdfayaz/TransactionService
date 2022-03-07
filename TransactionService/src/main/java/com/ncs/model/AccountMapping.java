package com.ncs.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//mark class as an Entity 
@Entity
//defining class name as Table name
@Table
public class AccountMapping
{
//Defining account id as primary key
@Id
@Column
@GeneratedValue(strategy = GenerationType.AUTO)	
private int accountMappingid;
@ManyToOne
private Account account;
@Column
private int customerId;
@Column
private long amount;



public int getAccountMappingid() {
	return accountMappingid;
}
public void setAccountMappingid(int accountMappingid) {
	this.accountMappingid = accountMappingid;
}
public Account getAccount() {
	return account;
}
public void setAccount(Account account) {
	this.account = account;
}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public long getAmount() {
	return amount;
}
public void setAmount(long amount) {
	this.amount = amount;
}





}