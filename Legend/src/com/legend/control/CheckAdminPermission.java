package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckAdminPermission
 */
@WebServlet("/CheckAdminPermission")
public class CheckAdminPermission extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAdminPermission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = (String)request.getSession().getAttribute("admin");
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
        response.setHeader("Cache-Control", "private, must-revalidate");
		PrintWriter out = response.getWriter();
		if(result==null || result.isEmpty())
			return;
		else
			out.println(result);
	}

}
