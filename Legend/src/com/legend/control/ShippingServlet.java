package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.legend.lib.Address;
import com.legend.lib.MoreHelpFunctions;
import com.legend.lib.Shipping;
import com.legend.lib.User;

/**
 * Servlet implementation class ShippingServlet
 */
@WebServlet("/ShippingServlet")
public class ShippingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		Address add=new Address();
		Shipping ship=new Shipping();
		MoreHelpFunctions help=new MoreHelpFunctions();
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		int addId;
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		String uid=user.getUserID();
		String recipientName=request.getParameter("your-name");
		String contactNumber=request.getParameter("your-phone");
		//long contact=Long.parseLong(contactNumber);
		String doorno=request.getParameter("your-door-num");
		String street=request.getParameter("your-street");
		String city=request.getParameter("your-city");
		String state=request.getParameter("your-state");
		String pincode=request.getParameter("your-pin-code");
		long pin=Long.parseLong(pincode);
		addId=help.checkAddressId(doorno, street, city);
		if(addId==0){
			addId=add.generateAddressId(pin);
			//insert in address table
			add.setAddressID(addId);
			add.setDoorNo(doorno);
			add.setStreet(street);
			add.setCity(city);
			add.setState(state);
			add.setPincode(pin);
			add.insertAddress();
		}
		
		//insert into shipping table
		ship.setAddressid(addId);
		ship.setContactNo(contactNumber);
		ship.setRecipient(recipientName);
		ship.setUserid(uid);
		ship.insertSaleid();
		double total=help.calculateAmount(user);
		System.out.println(total);
		//total should be calculated and should be sent to bank here.
		response.sendRedirect("http://localhost:8080/SimpleBanking/ConfirmPayment?vendor=Legend&amt="+total);
		
	}

	
	

}
