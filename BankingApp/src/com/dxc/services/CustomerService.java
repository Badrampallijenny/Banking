package com.dxc.services;

import java.util.List;

import com.dxc.dao.CustomerDao; 
import com.dxc.dao.ICustomerDao;
import com.dxc.pojos.Transection;

public class CustomerService implements ICustomerService {
	ICustomerDao dao=new CustomerDao();
	
	public boolean login(int id,String password)
	{
		return dao.login(id,password);
	}
	public boolean checkPassword(int id,String password)
	{
		return dao.checkPassword(id,password);
	}
	public boolean deposit(int id,double balance)
	{
		return dao.deposit(id,balance);
	}
	public boolean CheckAccountno(int tid)
	{
		return dao.CheckAccountno(tid);
	}
	public boolean transfer(int id,int tid,double balance)
	{
		return dao.transfer(id,tid,balance);
	}
	public boolean withdraw(int id,double balance)
	{
		return dao.withdraw(id,balance);
	}
	public double balance(int id)
	{
		return dao.balance(id);
	}
	public boolean checkOldPass(int id,String oldPassword)
	{
		return dao.checkOldPass(id,oldPassword);
	}
	public boolean changePassword(int id,String newPassword)
	{
		return dao.changePassword(id, newPassword);
	}
	public List<Transection> getTransectionDetailes(int id) {
		return dao.getTransectionDetailes(id);
	}

}
