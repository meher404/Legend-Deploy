package com.legend.control;

import java.awt.print.Printable;
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

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		ForgotPassword pass=new ForgotPassword();
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		String password=request.getParameter("password"); 
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("email");
		System.out.println("email  :"+email);
		pass.newPassword(email,password);
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		out.println("<html><body>");
	    out.println("<script type=\"text/javascript\">");
	    out.println("var popwin = window.open(\"Password_Change.html\")");
	    out.println("setTimeout(function(){ popwin.close(); window.location.href='index.html#contact';},5000)");
	    out.println("registerToSignIn();");
	    out.println("</script>");
	    out.println("</body></html>");
	}

}
