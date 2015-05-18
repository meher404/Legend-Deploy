package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.Address;
import com.legend.lib.User;
import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		Address add;
		try {
			add = new Address();
			User user=new User();
			helpFunctions help=new helpFunctions();
			String name,email,password,gender,doorno,street,city,state,pin,phone,date;
			Long phone_no,pincode;
			Date dob = null;
			name=request.getParameter("your-name");
			System.out.println("Name: "+name);
			String name1=request.getParameter("yourname");
			System.out.println("Name1: "+name1);
			email=request.getParameter("your-email");
			if(help.checkExistingEmail(email)){
				out.print("Email Id already exists");
				return;
			}
			password=request.getParameter("pass_confirmation");
			phone=request.getParameter("your-phone");
			phone_no=Long.parseLong(phone);
			gender=request.getParameter("your-gender");
			date=request.getParameter("your-dob");
			SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
			try{
				java.util.Date var=format.parse(date);
				dob=new Date(var.getTime());
			}
			catch(Exception e){
				e.printStackTrace();
			}
			doorno=request.getParameter("your-door-num");
			street=request.getParameter("your-street");
			city=request.getParameter("your-city");
			state=request.getParameter("your-state");
			pin=request.getParameter("your-pin-code");
			//pincode=(long) Integer.parseInt(pin);
			pincode=Long.parseLong(pin);
			add.setDoorNo(doorno);
			add.setStreet(street);
			add.setCity(city);
			add.setState(state);
			add.setPincode(pincode);
			int addid=add.generateAddressId(pincode);
			add.setAddressID(addid);
			add.insertAddress();
			user.setAddressID(addid);
			user.setName(name);
			user.setEmail(email);
			user.setPwd(password);
			user.setPhne(phone_no);
			user.setGender(gender);
			user.setDob(dob);
			String userid=user.generateUserId(user.getName(), user.getAddressID());
			user.setUserID(userid);
			user.insertUser();
			
			response.setContentType("text/html");
			response.setHeader("Pragma", "cache");
			response.setHeader("Cache-Control", "private, must-revalidate");
			//PrintWriter out = response.getWriter();
			out.println("<html><body>");
		    out.println("<script type=\"text/javascript\">");
		    out.println("var popwin = window.open(\"registered_sucess.html\")");
		    out.println("setTimeout(function(){ popwin.close(); window.location.href='index.html#contact';},5000)");
		    out.println("registerToSignIn();");
		    out.println("</script>");
		    out.println("</body></html>");
			//RequestDispatcher rd= request.getRequestDispatcher("index.html");
			//rd.include(request, response);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
