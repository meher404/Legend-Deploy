package com.legend.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.legend.lib.ReportsAdmin;
import com.legend.util.App;

/**
 * Servlet implementation class ReportDetail
 */
@WebServlet("/ReportDetail")
public class ReportDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//PrintWriter out=response.getWriter();
		response.setContentType("application/pdf");

		ReportsAdmin reports=new ReportsAdmin();
		String str,str1,str2,str3;
		App app=new App();
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		String fileSavePath = request.getServletContext().getRealPath("")+File.separator;;
		if(request.getParameter("cat").equals("product")){
			str=reports.ReportsByProduct();
			app.ganeratePDF(str,fileSavePath+"ReportsByProduct","REPORTS BY PRODUCT");
			String path="E:\\ReportsByProduct.pdf";
			
			RequestDispatcher rd=request.getRequestDispatcher("ReportsByProduct.pdf");
			rd.forward(request, response);
		}
		else if(request.getParameter("cat").equals("category")){
			str1=reports.ReportsByCategory();
			app.ganeratePDF(str1, fileSavePath+"ReportsByCategory","REPORTS BY CATEGORY");
			String path="E:\\ReportsByCategory.pdf";
			RequestDispatcher rd=request.getRequestDispatcher("ReportsByCategory.pdf");
			rd.forward(request, response);
		}
		else if(request.getParameter("cat").equals("month")){
			str2=reports.MonthlyReports();
			app.ganeratePDF(str2, fileSavePath+"MonthlyReports","MONTHLY REPORTS");
			String path="E:\\MonthlyReports.pdf";
			RequestDispatcher rd=request.getRequestDispatcher("MonthlyReports.pdf");
			rd.forward(request, response);
		}
		else if(request.getParameter("cat").equals("year")){
			str3=reports.YearlyReports();
			app.ganeratePDF(str3, fileSavePath+"YearlyReports","YEARLY REPORTS");
			String path="E:\\YearlyReports.pdf";
			//response.sendRedirect(path);
			RequestDispatcher rd=request.getRequestDispatcher("YearlyReports.pdf");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
