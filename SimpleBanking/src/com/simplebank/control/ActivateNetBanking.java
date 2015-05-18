package com.simplebank.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplebank.util.DBHelper;
import com.simplebank.util.Mailer;

/**
 * Servlet implementation class ActivateNetBanking
 */
@WebServlet("/ActivateNetBanking")
public class ActivateNetBanking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivateNetBanking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accno = request.getParameter("accno");
		String email = request.getParameter("email");
		response.setContentType("text/html");
		String code = DBHelper.sendActivationCode(accno, email);
		if(code!=null){
			//Mailer.send(email, "Activation Code of Simple Banking Solutions", "Activation Code of Simple Banking Solutions \n  Code is: "+code+"\n This code will not be effective afterwards.");
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("accno", accno);
			RequestDispatcher rd = request.getRequestDispatcher("ValidateNetBanking.html");
			rd.include(request, response);
		}
		else{
			PrintWriter out = response.getWriter();
			out.println("<h3 style=\"color:red\"> Invalid Credentials.</h3>");
			out.println("<h2 style=\"color:green\"> Please Make sure you have given correct account number and the email associated with that account. </h3>");
			RequestDispatcher rd = request.getRequestDispatcher("Activate.html");
			rd.include(request, response);
		}
		
	}

}
