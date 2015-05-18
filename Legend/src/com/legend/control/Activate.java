package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.legend.lib.Registration;

/**
 * Servlet implementation class Activate
 */
@WebServlet("/Activate")
public class Activate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		Registration reg=new Registration();
		PrintWriter out=response.getWriter();
		String code=request.getParameter("verfCode"); 
		int verify=Integer.parseInt(code);
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		if(reg.validateAccount(email, verify)){
			System.out.println("Activated");
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		else{
			System.out.println("Activation failed");
			RequestDispatcher rd=request.getRequestDispatcher("activate.html");
			rd.include(request, response);
		}
	}

}
