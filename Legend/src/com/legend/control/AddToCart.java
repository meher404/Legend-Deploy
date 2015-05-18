package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.legend.lib.Cart;
import com.legend.lib.Product;
import com.legend.lib.User;
import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		String pid = request.getParameter("pid");
		int qty = Integer.parseInt(request.getParameter("qty"));
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		PrintWriter out = response.getWriter();
		
		//PrintWriter out = response.getWriter();
		HashMap<Product, Integer> map = u.cart;
		Product p = helpFunctions.getProduct(pid);
		
		Cart c = new Cart();
		c.setUid(u.getUserID());
		if(!c.crudCart(p, qty)){//inserted product into cart.  
			out.println("<script>alert('Stock not available')</script>");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
			return;
		}
		
		map.put(p,qty);
		u.cart = map;
		session.setAttribute("user", u);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.include(request, response);
		out.println("<script>window.location='#news'</script>");
		return;
		}
		catch(Exception e){
			System.out.println("Error in parsing i guess..");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
