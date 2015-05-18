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
import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class getNewProducts
 */
@WebServlet("/getNewProducts")
public class getNewProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		helpFunctions help=new helpFunctions();
		ArrayList<Product> array=new ArrayList<Product>();
		array=help.getNewProducts();
		for (int i = 0; i < array.size(); i++) {
			Product pro;
			pro=array.get(i);
			System.out.println("image: "+pro.getImagesrc());
			out.println(pro.toString());
		}
	
	}

}
