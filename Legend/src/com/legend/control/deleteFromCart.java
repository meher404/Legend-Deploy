package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.Product;
import com.legend.lib.User;
import com.legend.lib.deleteProduct;
import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class deleteFromCart
 */
@WebServlet("/deleteFromCart")
public class deleteFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		deleteProduct delete=new deleteProduct(); 
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		String pid=request.getParameter("PID");
		
		User u = (User)request.getSession().getAttribute("user");
		HashMap<Product, Integer> c = u.cart;
		Product p = helpFunctions.getProduct(pid);
		delete.deleteFromCart(u.getUserID(), pid);
		
		System.out.println("product found: "+c.containsKey(p));
		System.out.println("value removed: "+c.remove(p));
		System.out.println("is it still there: "+c.containsKey(p));
		u.cart = c;
		request.getSession().setAttribute("user", u);
		out.println("index.html");
		
		
		
	}


}
