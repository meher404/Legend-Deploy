package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.Category;
import com.legend.lib.Product;
import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class getProductsByCategory
 */
@WebServlet("/CategoryDetail")
public class getProductsByCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		helpFunctions help=new helpFunctions();
		ArrayList<Product> array=new ArrayList<Product>();
		Category c=new Category();
		try {
			String CategoryName=request.getParameter("cat");
			int catId = c.generateCategoryID(CategoryName) ;
			array=help.getProductsByCategory(catId);
			for (int i = 0; i < array.size(); i++) {
				Product pro;//=new Product();
				pro=array.get(i);
				out.println(pro.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	

}
