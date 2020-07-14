package com.dxc.controllers;

import java.io.IOException; 
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.Customer;

import com.dxc.services.AdminService;

@WebServlet("/adminServlet")
public class adminServlet extends HttpServlet {
	
	AdminService adminservice=new AdminService();
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message;
		String action="";
		String temp=request.getParameter("btn");
		if(temp!=null)
			action=request.getParameter("btn");
			HttpSession session=request.getSession();
			if(action.equals("Adminlogin"))
			{
				String loginid=(request.getParameter("loginid"));
				String password=(request.getParameter("password"));
				
				boolean value=adminservice.login(loginid,password);
				session.setAttribute("loginid", loginid);
				session.setAttribute("password", password);
				System.out.println(value);
				if(value==true)
				{
												
					//session.setAttribute("message", message);
					response.sendRedirect("Adminlogin.jsp");
					
				}
				else
				{
					System.out.println("login failure");
				}
			}
			else if(action.equals("addAccount"))
			{
				int id=Integer.parseInt(request.getParameter("id"));
				String name=request.getParameter("name");
				double balance=Double.parseDouble(request.getParameter("balance"));
				String password=request.getParameter("password");
				Customer c=new Customer(id,name,balance,password);
				adminservice.addAccount(c);
				System.out.println("sdg");
				message="account added";							
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
				
		     }
			else if(action.equals("search"))
			{
				System.out.println("test1");
				int id=Integer.parseInt(request.getParameter("id"));
				boolean b=adminservice.getCustomer(id);
				if(b==true)
				{
					System.out.println("jenny");
					message="search detailes";
					List<Customer> list=adminservice.detaileddisplay(id);
					session.setAttribute("list",list);
					response.sendRedirect("detaileddisplay.jsp");
				}
				else
				{
					message="search details not found";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
				}
				
			}
			else if(action.equals("find"))
			{
				int id=Integer.parseInt(request.getParameter("id"));
				session.setAttribute("id", id);
				boolean findStatus=adminservice.findCustomer(id);
				if(findStatus==true)
				{
					response.sendRedirect("Update2.jsp");
				}
				else {
					message="  not found";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");	
				}}
			
			else if(action.equals("update")) 
			{
				int id=(int)session.getAttribute("id");
				//String name=(String)session.getAttribute("name");
				//int rollno=Integer.parseInt(request.getParameter("roll"));
				String name=request.getParameter("name");
				Double balance=Double.parseDouble(request.getParameter("balance"));
				String password=request.getParameter("password");
				Customer c=new Customer(id,name,balance,password);
				adminservice.UpdateCustomer(c);
				message=" detailes  updated";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
				
			}
	
			else if(action.equals("delete")) 
			{
				int id=Integer.parseInt(request.getParameter("id"));
				boolean removeStatus=adminservice.delete(id);
				if(removeStatus==true)
				{
					message=" removed";
				}
				else 
				{
					message=" not found";
					}
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");	
				}
			else if(action.equals("balance"))
			{
				int id=Integer.parseInt(request.getParameter("id"));
				double list=adminservice.balance(id);
				session.setAttribute("list", list);
				response.sendRedirect("displaybalance.jsp");
				
						
			}
			
			else
			{
				List<Customer> list=adminservice.getCustomers();
				session.setAttribute("list", list);
				response.sendRedirect("detaileddisplay.jsp");
			}
				
			
			
		}
	}

