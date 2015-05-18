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
 * Servlet implementation class ValidateACode
 */
@WebServlet("/ValidateACode")
public class ValidateACode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateACode() {
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
		String code = request.getParameter("acode");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if(DBHelper.activate(email, accno, code)){
			RequestDispatcher rd = request.getRequestDispatcher("ValidateNetBanking.html");
			rd.include(request, response);
			out.println("<h2>Net Banking services have been successfully Activated for you.</h2>");
			out.println("<form action=\"SetPassword\" method=\"POST\">");
			out.println("Set Your Password: <input type=\"password\" name=\"pwd\" />");
			out.println("<input type=\"submit\" value=\"Set Password\" />");
			out.println("</form>");
		}
		else{
			out.println("<h2>Invalid Activation Code, please re-try.</h2>");
			RequestDispatcher rd = request.getRequestDispatcher("ValidateNetBanking.html");
			rd.include(request, response);
		}
	}

}
