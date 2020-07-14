package com.dxc.pojos;

public class Transection {
	private int accno;
	private String typeOfTran;
	private double balance;
	
	public Transection() {
	}

	public Transection(int accno, String typeOfTran, double balance) {
		super();
		this.accno = accno;
		this.typeOfTran = typeOfTran;
		this.balance = balance;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getTypeOfTran() {
		return typeOfTran;
	}

	public void setTypeOfTran(String typeOfTran) {
		this.typeOfTran = typeOfTran;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return accno+" "+typeOfTran+" "+balance;
	}
	
	
}
