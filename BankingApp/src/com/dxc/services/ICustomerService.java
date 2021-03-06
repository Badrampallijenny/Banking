package com.dxc.services;

import java.util.List;

import com.dxc.pojos.Transection;

public interface ICustomerService {
	public boolean login(int id,String password);
	public boolean checkPassword(int id,String password);
	public boolean deposit(int id,double balance);
	public boolean CheckAccountno(int tid);
	public boolean transfer(int id,int tid,double balance);
	public boolean withdraw(int id,double balance);
	public double balance(int id);
	public boolean checkOldPass(int id,String oldPassword);
	public boolean changePassword(int id,String oldPassword);
	public List<Transection> getTransectionDetailes(int id);
}
