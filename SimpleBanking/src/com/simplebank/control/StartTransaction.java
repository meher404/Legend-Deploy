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

/**
 * Servlet implementation class StartTransaction
 */
@WebServlet("/StartTransaction")
public class StartTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String vendor = (String)request.getSession().getAttribute("vendor");
		String amt = (String)request.getSession().getAttribute("amt");
		String email = (String)request.getSession().getAttribute("email");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		
		if(DBHelper.validateTransaction(email, amt)){
			String accno = DBHelper.getAccountNumber(email);
			DBHelper.sendActivationCode(accno, email);
			RequestDispatcher rd = request.getRequestDispatcher("CompleteTransaction.html");
			rd.forward(request, response);
		}
		else{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h2>Insufficient Funds. Redirecting back to Vendor in 3 seconds.</h2>");
			out.println("<script>setTimeout(function(){window.location.href='http://localhost:8080/Legend/BankResponse?status=2';},3000);</script>");
		}
	}

}
