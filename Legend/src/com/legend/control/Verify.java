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

import com.legend.lib.ForgotPassword;
import com.legend.lib.Registration;


@WebServlet("/Verify")
public class Verify extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		ForgotPassword pass=new ForgotPassword();
		PrintWriter out=response.getWriter();
		String code=request.getParameter("verfCode"); 
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("forgotemail");
		System.out.println("email  :"+email);
		if(pass.verify(email, code)){
			RequestDispatcher rd=request.getRequestDispatcher("NewPassword.html");
			rd.include(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("VerifyCode.html");
			rd.include(request, response);
			out.println("<script>Invalid verification code</script>");
		}
	}

}
