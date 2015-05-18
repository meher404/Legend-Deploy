package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;



import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legend.lib.Manufacturer;
import com.legend.lib.Product;
import com.legend.lib.helpFunctions;
import com.legend.lib.Category;

/**
 * Servlet implementation class CheckProductId
 */
@WebServlet("/CheckProductId")
public class CheckProductId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		helpFunctions help=new helpFunctions();
		Manufacturer m=new Manufacturer();
		Category c=new Category();
		Product p=new Product();
		String pid="";
		String name=request.getParameter("pname");
		String cname=request.getParameter("category");
		String mfgname=request.getParameter("mfgname");
		if(help.CheckProduct("name")){
		//	System.out.println(help.existingPID("name"));
			pid=help.existingPID("name");
			out.println(pid);
		}
		else{
			try {
				int catid=c.generateCategoryID(cname);
				int manfid=m.generatemanufactureID(mfgname);
				//pid=p.GeneratePID(catid,manfid);
				pid = (name.hashCode()+"")+(catid+"")+(manfid+"");
				out.println(pid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
