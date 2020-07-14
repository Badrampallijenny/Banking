package com.dxc.pojos;

public class Customer {
	private int id;
	private String name;
	private Double balance;
	private String password;
	public Customer(int id, String name, Double balance, String password) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", balance=" + balance + ", password=" + password + "]";
	}
	
	
	
	
	

}
