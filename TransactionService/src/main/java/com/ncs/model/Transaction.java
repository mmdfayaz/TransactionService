package com.ncs.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//mark class as an Entity 
@Entity
//defining class name as Table name
@Table
public class Transaction
{
//Defining transaction id as primary key
@Id
@Column
@GeneratedValue(strategy = GenerationType.AUTO)
private long transactionId;
@Column
private long sender;
@Column
private long receiver;
@Column
private long senderAcct;
@Column
private long receiverAcct;
@Column
private long transactionAmt;
@Column
private Date createdDate;
@Column
private Date modifiedDate;

public long getTransactionId() {
	return transactionId;
}
public void setTransactionId(long transactionId) {
	this.transactionId = transactionId;
}
public long getSender() {
	return sender;
}
public void setSender(long sender) {
	this.sender = sender;
}
public long getReceiver() {
	return receiver;
}
public void setReceiver(long receiver) {
	this.receiver = receiver;
}
public long getSenderAcct() {
	return senderAcct;
}
public void setSenderAcct(long senderAcct) {
	this.senderAcct = senderAcct;
}
public long getReceiverAcct() {
	return receiverAcct;
}
public void setReceiverAcct(long receiverAcct) {
	this.receiverAcct = receiverAcct;
}
public long getTransactionAmt() {
	return transactionAmt;
}
public void setTransactionAmt(long transactionAmt) {
	this.transactionAmt = transactionAmt;
}
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public Date getModifiedDate() {
	return modifiedDate;
}
public void setModifiedDate(Date modifiedDate) {
	this.modifiedDate = modifiedDate;
}


}