package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class CheckValidEmailLogin
 */
@WebServlet("/CheckValidEmailLogin")
public class CheckValidEmailLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckValidEmailLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		helpFunctions help=new helpFunctions();
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		//System.out.println("email: "+email);
		try {
			if(help.checkExistingEmail(email)||email.equals("admin@gmail.com")){
				//System.out.println("email exists");
				return;
			}
			else{
				//System.out.println("email doesn't exist");
				out.println("<span style='color:red' align='center'>Invalid Email</span>");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
