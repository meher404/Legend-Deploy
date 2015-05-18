package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.Product;
import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class RetrieveProductDetails
 */
@WebServlet("/RetrieveProductDetails")
public class RetrieveProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveProductDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("PID");
		Product pro = helpFunctions.getProduct(pid);
		response.setContentType("application/json");
		System.out.println("recieved query...");
		if(pro==null){
			return;
		}
		PrintWriter out = response.getWriter();
		try {
			String jsonObj="{\"category\":\""+pro.getCategory(pro.getCategoryID()).getCategoryName()+"\","
					+ "\"pname\":\""+pro.getPname()+"\","
					+ "\"mfgname\":\""+pro.getManufacturer(pro.getManufactureID()).getManufacturerName()+"\","
					+ "\"pid\":\""+pro.getPID()+"\","
					+ "\"desc\":\""+pro.getDescription()+"\","
					+ "\"price\":\""+pro.getPrice()+"\","
					+ "\"qty\":\""+pro.getQuantity()+"\","
					+ "\"disc\":\""+pro.getDiscount()+"\""
					+ "}";
			//System.out.println("json: "+jsonObj);
			out.println(jsonObj);
		} catch (SQLException e) {
			System.out.println("Error in setting json");
		}
	}

}
