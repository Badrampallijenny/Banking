package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.Transection;

public class CustomerDao implements ICustomerDao {
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

	@Override
	public boolean login(int id, String password) {
		try
		{
			Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery("select * from customer");
			while(rset.next())
			{
				int a=rset.getInt(1);
							
				if(id==a && password.equals(rset.getString(4)))
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

	@Override
	public boolean checkPassword(int id, String password) {
	
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("select password from customer where id=?");
			pstmt.setInt(1, id);
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				if(password.equals(rset.getString(1)))
				{
					return true;
				}
			}
			pstmt.close();

			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean deposit(int id, double balance) {
		boolean b=false;
		double bal=0;
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("select balance from customer where id=?");
			pstmt.setInt(1, id);
			ResultSet rset=pstmt.executeQuery();
			rset.next();
			bal=rset.getDouble(1);
			pstmt.close();
			System.out.println("1.....");
			bal=bal+balance;
			PreparedStatement pstmt1=conn.prepareStatement("update customer set balance=? where id=?");
			pstmt1.setDouble(1, bal);
			pstmt1.setInt(2, id);
			pstmt1.executeUpdate();
			pstmt1.close();
			System.out.println("2....");
			PreparedStatement pstmt2=conn.prepareStatement("insert into transctiondetails values(?,?,?)");
			pstmt2.setInt(1, id);
			pstmt2.setString(2, "deposit");
			pstmt2.setDouble(3, balance);
			pstmt2.executeUpdate();
			pstmt2.close();
			System.out.println("2....");
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean transfer(int id, int tid, double balance) {
		if(balance>this.balance(id))
		{
			return false;
		}
	double bal=0;
	double tbal=0;
	System.out.println("test4");
	try
	{
		PreparedStatement pstmt=conn.prepareStatement("select * from customer");
		ResultSet rset=pstmt.executeQuery();
		while(rset.next())
		{
			System.out.println(rset.getDouble(3));
			if(id==rset.getInt(1))
			{
				bal=rset.getDouble(3);
				System.out.println(rset.getDouble(3));
				
			}else if(tid==rset.getInt(1))
			{
				tbal=rset.getDouble(3);
				System.out.println(rset.getDouble(3));
				 
			}
		}
			pstmt.close();
		/*	if(bal<balance)
			{
				return false;
			}*/
			System.out.println(tbal+" "+bal+" "+balance);
			bal=bal-balance;
			tbal=tbal+balance;
			System.out.println(tbal+" "+bal+" "+balance);
			PreparedStatement pstmt1=conn.prepareStatement("update customer set balance=? where id=?");
			pstmt1.setDouble(1, bal);
			pstmt1.setInt(2, id);
			pstmt1.executeUpdate();
			pstmt.close();
			System.out.println("test6");
			PreparedStatement pstmt2=conn.prepareStatement("update customer set balance=? where id=?");
			pstmt2.setDouble(1, tbal);
			pstmt2.setInt(2, tid);
			pstmt2.executeUpdate();
			pstmt2.close();
			
			PreparedStatement pstmt4=conn.prepareStatement("insert into transctiondetails values(?,?,?)");
			pstmt4.setInt(1, id);
			pstmt4.setString(2, "transfer");
			pstmt4.setDouble(3, balance);
			pstmt4.executeUpdate();
			pstmt4.close();
			
			
			
			System.out.println("test7");
			return true;
		}catch (Exception e) {
		// TODO: handle exception
}
		return false;
	}

	@Override
	public boolean CheckAccountno(int tid) {
		
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("select * from customer");
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				if(tid==rset.getInt(1))
				{
					return true;
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		
		}
		return false;
	}

	public boolean withdraw(int id, double balance) {
		if(balance>this.balance(id))
		{
			return false;
		}
		boolean b=false;
		double bal=0;
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("select balance from customer where id=?");
			pstmt.setInt(1, id);
			ResultSet rset=pstmt.executeQuery();
			rset.next();
			bal=rset.getDouble(1);
			pstmt.close();
			System.out.println("1.....");
			bal=bal-balance;
			PreparedStatement pstmt1=conn.prepareStatement("update customer set balance=? where id=?");
			pstmt1.setDouble(1, bal);
			pstmt1.setInt(2, id);
			pstmt1.executeUpdate();
			pstmt1.close();
			System.out.println("2....");
			PreparedStatement pstmt2=conn.prepareStatement("insert into transctiondetails values(?,?,?)");
			pstmt2.setInt(1, id);
			pstmt2.setString(2, "withdraw");
			pstmt2.setDouble(3, balance);
			pstmt2.executeUpdate();
			pstmt2.close();
			System.out.println("2....");
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public double balance(int id) {
		
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
	public boolean checkOldPass(int id, String oldPassword) {
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("select password from customer where id=?");
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				if(oldPassword.equals(rs.getString(1)))
				{
					return true;
				}
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean changePassword(int id, String newPassword) {
		try {
			PreparedStatement pstmt=conn.prepareStatement("update customer set password=? where id=?");
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Transection> getTransectionDetailes(int id) {
		List<Transection> list =new ArrayList<Transection>();
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from transctiondetails where id=?");
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				list.add(new Transection(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		return list;
	}

	}
	

		
			
		
	
	


