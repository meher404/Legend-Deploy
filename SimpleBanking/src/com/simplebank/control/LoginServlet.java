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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String em = request.getParameter("email");
		String pwd = request.getParameter("password");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		PrintWriter out = response.getWriter();
		boolean login = DBHelper.login(em.trim(), pwd.trim());
		//System.out.println("login ainda?: "+login);
		System.out.println("email: "+em+" pwd sent to db is: "+pwd);
		if(login){
			request.getSession().setAttribute("email", em.trim());
			String vendor = (String)request.getSession().getAttribute("vendor");
			String amt = (String)request.getSession().getAttribute("amt");
			String accno = DBHelper.getAccountNumber(em);
			request.getSession().setAttribute("accno", accno);
			System.out.println("email: "+em+" amt: "+amt+" in login servlet");
			out.println("<html>");
			out.println("<body align='center'>");
			out.println("<h2>Are You sure you want to proceed with this Transaction?</h2>");
			out.println("<form method='POST'>");
			out.println("<h3>Transfer towards Vendor : "+vendor+"</h3>");
			out.println("<h3>Amount to be transfered : <h1>"+amt+"</h1></h3>");
			out.println("<input type='submit' value='AGREE' formaction='StartTransaction' />");
			out.println("<input type='submit' value='DISAGREE' formaction='CancelTransaction' />");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			
			//RequestDispatcher rd = request.getRequestDispatcher("confirmPayment.html");
			//rd.include(request, response);
		}
		else{
			
			out.println("<h3 style=\"color:red\"> Invalid login Credentials.</h3>");
			out.println("<h2 style=\"color:green\"> Please Make sure you have activated your E-Banking services </h3>");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
		}
	}

}
