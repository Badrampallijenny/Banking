package com.dxc.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.Customer;



public class AdminDao implements IAdminDao {
private static Connection conn;

	

	
	static {
		try
		{
			Class.forName("org.postgresql.Driver");
			System.out.println("loaded");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jenny","postgres","1234567");
			System.out.println("established");
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public boolean login(String loginid,String password)
	{
		try
		{
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery("select * from admin1");
			while(rset.next())
			{
				//System.out.println(loginid+" "+password);
				//System.out.println(rset.getString(1).length()+" "+rset.getString(2).length());
				if(loginid.equals(rset.getString(1))&& password.equals(rset.getString(2)))
				{
					return true;
				}
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
		}
											
	public void addAccount(Customer c)
	{
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("insert into Customer values(?,?,?,?)");
			pstmt.setInt(1, c.getId());
			pstmt.setString(2, c.getName());
			pstmt.setDouble(3, c.getBalance());
			pstmt.setString(4, c.getPassword());
			pstmt.executeUpdate();
			}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public boolean getCustomer(int id)
	{
		int flag=0;
		//boolean status=false;
		try
		{
	
	
		PreparedStatement stmt=conn.prepareStatement("select * from customer");
		ResultSet rset=stmt.executeQuery();
		while(rset.next())
		{
			if(id==rset.getInt(1))
			{System.out.println("condition true");
				flag=1;
			}
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	
	}
		if(flag==1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
		public List<Customer> detaileddisplay(int id)
		{
			List<Customer> list=new ArrayList<>();
			try
			{
				PreparedStatement pstmt=conn.prepareStatement("select * from customer");
				ResultSet rset=pstmt.executeQuery();
				while(rset.next())
				{
					if(id==rset.getInt(1))
					{
						list.add(new Customer(rset.getInt(1),rset.getString(2),rset.getDouble(3),rset.getString(4)));

					}
				}
			}catch (Exception e) {
				
			}
			return list;
			}
		
	

public boolean findCustomer(int id)
{
	try {
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from customer");
		while(rs.next())
		{
			if(id==rs.getInt(1))
			{
				return true;
			}
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return false;
}

public void UpdateCustomer(Customer c){
	try {
		PreparedStatement pstmt=conn.prepareStatement("update customer set name=?, balance=?, password=? where id=?");
		pstmt.setString(1, c.getName());
		pstmt.setDouble(2, c.getBalance());
		pstmt.setString(3, c.getPassword());
		pstmt.setInt(4, c.getId());
		pstmt.executeUpdate();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
public boolean delete(int id)
{
	boolean status=false;
	try {
		PreparedStatement pstmt=conn.prepareStatement("delete from customer where id=?");
		pstmt.setInt(1, id);
		status=pstmt.execute();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return !status;
}

@Override
public double balance(int id) {
	// TODO Auto-generated method stub
	;
	double balance=0;
	try
	{
		PreparedStatement pstmt=conn.prepareStatement("select balance from customer where id=?");
		pstmt.setInt(1, id);
		ResultSet rset=pstmt.executeQuery();
		while(rset.next())
		{
			balance=rset.getDouble(1);
			//list.add(balance);
		}
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	return balance;
}

@Override
public List<Customer> getCustomers() {
	List<Customer> list=new ArrayList<>();
	try
	{
		Statement stmt=conn.createStatement();
		ResultSet rset=stmt.executeQuery("select * from customer");
		while(rset.next())
		{
			list.add(new Customer(rset.getInt(1),rset.getString(2),rset.getDouble(3),rset.getString(4)));
		}
		
	}catch (Exception e) {
		// TODO: handle exception
	}return list;
	
}


}










	