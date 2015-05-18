package com.simplebank.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CancelTransaction
 */
@WebServlet("/CancelTransaction")
public class CancelTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>You will be taken back to your vendor in 5 seconds.</h1>");
		//response.sendRedirect("http://localhost:8080/Legend/index.html?status=3");
		
		out.println("<script>setTimeout(function(){window.location.href='http://localhost:8080/Legend/BankResponse?status=3';},5000);</script>");
		
		return;
	}

}
