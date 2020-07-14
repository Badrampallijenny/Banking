package com.dxc.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.Transection;
import com.dxc.services.AdminService;
import com.dxc.services.CustomerService;


@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	CustomerService customerservice=new CustomerService();
	private static final long serialVersionUID = 1L;
     private int id;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message;
		String action="";
		String op="";
		String temp=request.getParameter("btn");
		if(temp!=null)
			action=request.getParameter("btn");
			HttpSession session=request.getSession();
			if(action.equals("Customerlogin"))
			{
				 id=Integer.parseInt(request.getParameter("id"));
				String password=(request.getParameter("password"));
				
				
				
				boolean value=customerservice.login(id,password);
				session.setAttribute("id", id);
				session.setAttribute("password", password);
				
				System.out.println(value);
				if(value==true)
				{
					message="login succesfull";
					session.setAttribute("message", message);
					response.sendRedirect("Customeropeations.jsp");
					
				}
				else
				{
					message="login unsuccesfull";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
					System.out.println("login failure");
				}
			}
			else if(temp.equals("Deposit"))
			{
				System.out.println("test1");
				int id1=Integer.parseInt(request.getParameter("id"));
				//String password=(request.getParameter("password"));
				//System.out.println(id1+" "+password);
				//boolean b1=customerservice.checkPassword(id,password);
				//if(b1==true)
				//{
					System.out.println("test2");
				double balance=Double.parseDouble(request.getParameter("balance"));
				System.out.println(id1+" "+balance);
				boolean b=customerservice.deposit(id1,balance);
				System.out.println("test3");
				if(b==true)
				{
					System.out.println("test4");
					session.setAttribute("action", action);
					session.setAttribute("balance", balance);				
					response.sendRedirect("view1.jsp");
				}else
				{
					message="Invalid Accunt number";
					session.setAttribute("message", message);
					response.sendRedirect("view4.jsp");
				}
				
				
			}
			else if(temp.equals("Transfer"))
			{
				System.out.println("test1");
				String password=(request.getParameter("password"));
				int tid=Integer.parseInt(request.getParameter("tid"));
				double balance=Double.parseDouble(request.getParameter("balance"));
				boolean b=customerservice.checkPassword(id,password);
				if(b==true)
				{
					
					boolean b1=customerservice.CheckAccountno(tid);
					System.out.println(b1);
				if(b1==true)
				{
				boolean b2=customerservice.transfer(id,tid,balance);
				System.out.println(b2);
				if(b2==true)
				
				{
					
					session.setAttribute("action", action);
					session.setAttribute("balance", balance);
					response.sendRedirect("Transfersuccesfull.jsp");
					}
					
				else 
				{
					System.out.println("123");

					message="Insufficient Funds To Transfer";
					session.setAttribute("message", message);
					response.sendRedirect("view4.jsp");
				}
				
			}
		}else
		{
			message="Password is Incorrect";
			session.setAttribute("message", message);
			response.sendRedirect("view4.jsp");
		}
	}
				else if(temp.equals("withdraw"))
				{
					System.out.println("test1");
					String password1=(request.getParameter("password"));
					boolean b1=customerservice.checkPassword(id,password1);
					if(b1==true)
					{
					double balance1=Double.parseDouble(request.getParameter("balance"));
					boolean b11=customerservice.withdraw(id,balance1);
					System.out.println("test3");
					if(b11==true)
					{
						session.setAttribute("action", action);
						session.setAttribute("balance", balance1);				
						response.sendRedirect("view1.jsp");
					}else
					{
						message="insufficient balance";
						session.setAttribute("message", message);
						response.sendRedirect("view4.jsp");
					}
					
					}else
					{
						message="Password is Incorrect";
						session.setAttribute("message", message);
						response.sendRedirect("view4.jsp");
					}
				}
				else if(action.equals("Balance"))
				{
					double list=customerservice.balance(id);
					session.setAttribute("list", list);
					response.sendRedirect("displaybalancecustomer.jsp");
					
							
				}
				else if(action.equals("ChangePassword"))
				{
					System.out.println("213");
						String oldPassword=request.getParameter("oldPass");
						String newPassword=request.getParameter("newPass");
						boolean b=customerservice.checkOldPass(id,oldPassword);
						if(b==true)
						{
							boolean b1=customerservice.changePassword(id,newPassword);
							if(b1==true) 
							{
								session.setAttribute("action", action);
								session.setAttribute("message", "succesfully changed");
								//response.sendRedirect("");
								response.getWriter().println("succesfullychanged");
							}else {response.getWriter().println("somhting wrong in change password!!!");}
						}else{response.getWriter().println("Old password is wrong!!!");
						}
						}
	
	
	
				else if(action.equals("transaction_detailes"))
				{
					System.out.println("123");
	
					List<Transection> lst=new ArrayList<Transection>();
						lst=customerservice.getTransectionDetailes(id);
						System.out.println("456");
							session.setAttribute("list", lst);
							response.sendRedirect("view3.jsp");
				}
	

		}
	}