package com.smartConManager.smart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class MyOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long myOrderId;
	
	@ManyToOne
	private User user;
	private String orderId;
	private String amount;
	private String receipt;
	private String status;
	private String dateAndTime;
	private String paymentId;
	public Long getMyOrderId() {
		return myOrderId;
	}
	public void setMyOrderId(Long myOrderId) {
		this.myOrderId = myOrderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	@Override
	public String toString() {
		return "MyOrder [myOrderId=" + myOrderId + ", user=" + user + ", orderId=" + orderId + ", amount=" + amount
				+ ", receipt=" + receipt + ", status=" + status + ", dateAndTime=" + dateAndTime + ", paymentId="
				+ paymentId + "]";
	}
	
	
	
}
