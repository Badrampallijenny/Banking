package com.dxc.services;

import java.util.List;

import com.dxc.pojos.Customer;

public interface IAdminService {
	public boolean login(String loginid,String password);
	public void addAccount(Customer c);
	public boolean getCustomer(int id);
	public List<Customer> detaileddisplay(int id);
	public boolean findCustomer(int id);
	public void UpdateCustomer(Customer c);
	public boolean delete(int id);
	public double balance(int id);
	public List<Customer> getCustomers();

}
