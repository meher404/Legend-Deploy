package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.legend.lib.Product;
import com.legend.lib.User;
import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCartForm")
public class AddToCartForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		PrintWriter out = response.getWriter();
		if(u==null){
			return;
		}
		
		String pid = request.getParameter("PID");
		Product p = helpFunctions.getProduct(pid);
		out.println(p.AddToCartString());
		
		
		
	}

}
