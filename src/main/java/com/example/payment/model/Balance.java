package com.example.payment.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Balance {

	@Id
	public int bid;
	public String currency;
	public double balance;
	public double exchangerate;
	
	

	
	
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

	public double getExchangerate() {
		return exchangerate;
	}
	public void setExchangerate(double exchangerate) {
		this.exchangerate = exchangerate;
	}
	@Override
	public String toString() {
		return   currency + " " + balance 
				+"\t( USD "+(balance*exchangerate)+" )" + "\n";
	}
	
	
	
	
	
}
