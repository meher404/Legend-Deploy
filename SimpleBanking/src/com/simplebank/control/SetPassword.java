package com.simplebank.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simplebank.util.DBHelper;

/**
 * Servlet implementation class SetPassword
 */
@WebServlet("/SetPassword")
public class SetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		String accno = (String)session.getAttribute("accno");
		String pwd = request.getParameter("pwd");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if(DBHelper.setPassword(email, accno, pwd)){
			out.println("<h2>Password has been Set. Please Login now to continue with your Transaction</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
		}
		else{
			out.println("<h2>Password could not be Set. Please try again.</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("Activate.html");
			rd.include(request, response);
		}
		
		
	}

}
