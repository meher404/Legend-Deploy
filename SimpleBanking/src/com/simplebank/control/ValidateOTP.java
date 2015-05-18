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
 * Servlet implementation class ValidateOTP
 */
@WebServlet("/ValidateOTP")
public class ValidateOTP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateOTP() {
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
		String code = request.getParameter("otp");
		String vendor = (String)request.getSession().getAttribute("vendor");
		String amt = (String)request.getSession().getAttribute("amt");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		
		PrintWriter out = response.getWriter();
		if(amt==null || amt.isEmpty()){
			out.println("<script>window.loaction='http://localhost:8080/Legend/index.html'</script>");
			return;
		}
		
		
		if(DBHelper.confirmOTP(email, accno, code, amt, vendor)){
			session.invalidate();
			out.println("<h2>Transaction Successful. Redirecting back to Vendor in 5 seconds.</h2>");
			out.println("<script>setTimeout(function(){window.location.href='http://localhost:8080/Legend/BankResponse?status=1';},5000);</script>");
			return;
		}
		else{
			out.println("<h2>Invalid OTP, please re-try.</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("CompleteTransaction.html");
			rd.include(request, response);
		}
	}

}
