package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.Category;
import com.legend.lib.Manufacturer;
import com.legend.lib.Product;
import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class DisplayPid
 */
@WebServlet("/DisplayPid")
public class DisplayPid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		Product pro=new Product();
		Category c=new Category();
		Manufacturer manufacturer=new Manufacturer();
		helpFunctions help=new helpFunctions();
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		String pname=request.getParameter("pname");
		String mfgname=request.getParameter("mfgname");
		String category=request.getParameter("category");
		String pid=help.existingPID(pname);
		
		if(pid.equals("")){
			int catID;
			try {
				catID = c.generateCategoryID(category);
				int mfgID=manufacturer.generatemanufactureID("mfgname");
				//pid=pro.GeneratePID(catID, mfgID);
				pid = (pname.hashCode()+"")+(catID+"")+(mfgID+"");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		out.println(pid);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	
	}

}
