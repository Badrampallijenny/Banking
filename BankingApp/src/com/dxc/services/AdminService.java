package com.dxc.services;

import java.util.List;

import com.dxc.dao.AdminDao;
import com.dxc.dao.IAdminDao;
import com.dxc.pojos.Customer;

public class AdminService  implements IAdminService {
	IAdminDao dao=new AdminDao();
	
	public boolean login(String loginid,String password)
	{
		return dao.login(loginid,password);
		
	}
	public void addAccount(Customer c)
	{
		dao.addAccount(c);
		
	}
	public boolean getCustomer(int id)
	{
		return dao.getCustomer(id);
	}
	
	
	public List<Customer> detaileddisplay(int id){
		    return dao.detaileddisplay(id);
		}
	public boolean findCustomer(int id)
	{
		return dao.findCustomer(id);
	}
	public void UpdateCustomer(Customer c)
	{
		dao.UpdateCustomer(c);
	}
	public boolean delete(int id)
	{
		return dao.delete(id);
	}
	public double balance(int id)
	{
		return dao.balance(id);
	}
	public List<Customer> getCustomers()
	{
		return dao.getCustomers();
	}

	}

	
	


