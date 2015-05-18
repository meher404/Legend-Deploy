package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.Product;
import com.legend.lib.Search;

/**
 * Servlet implementation class Search_Admin
 */
@WebServlet("/Search_Admin")
public class Search_Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		String key=request.getParameter("searchproduct");
		Search search=new Search();
		ArrayList<Product> array=new ArrayList<Product>();
		array=search.search(key);
		if(array.isEmpty()){
			out.println("<h2>No Results found for : "+key+"</h2>");
			return;
		}
		for (int i = 0; i < array.size(); i++) {
			Product pro=new Product();
			pro=array.get(i);
			out.println(pro.toCartString(pro.getQuantity()));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
