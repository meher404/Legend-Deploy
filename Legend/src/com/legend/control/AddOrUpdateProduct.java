package com.legend.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
//import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class AddOrUpdateProduct
 */
@WebServlet("/AddOrUpdateProduct")
public class AddOrUpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		response.setContentType("text/html");

		String fileSavePath="",fileName="",path="";
		String pname = "",mfgname="",category="",description="",cost="",qty="",disc="";




		try {
			/* TODO output your page here   */
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Pragma", "cache");
			response.setHeader("Cache-Control", "private, must-revalidate");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			int maxMemSize = 40000*1000;
			// maximum size that will be stored in memory
			factory.setSizeThreshold(maxMemSize);
			// Location to save data that is larger than maxMemSize.

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			long maxFileSize = 4000*1000;
			// maximum file size to be uploaded.
			upload.setSizeMax(maxFileSize );
		
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			System.out.println("isMultipart: "+isMultipart);
			
			if(!isMultipart){
				pname = request.getParameter("pname");
				category = request.getParameter("category");
				mfgname=request.getParameter("mfgname");
				description=request.getParameter("desc");
				cost=request.getParameter("price");
				qty=request.getParameter("qty");
				disc=request.getParameter("disc");
				helpFunctions.crudProduct(pname,"",cost,description,qty, disc, category, mfgname);
				RequestDispatcher rd=request.getRequestDispatcher("admin_home.html");
				rd.include(request, response);
				out.println("<script>alert('Updated "+pname+" Product');</script>");
				return;
			}
			List<?> fileItems = upload.parseRequest(request);
			// Process the uploaded file items
			Iterator<?> i = fileItems.iterator();
			//+  File.separator;



		
			

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (fi.isFormField())
				{

					if(fi.getFieldName().equals("pname"))
					{   
						pname=fi.getString();
					}
					System.out.println(pname);
					if(fi.getFieldName().equals("mfgname"))
						mfgname=fi.getString();
					if(fi.getFieldName().equals("category"))
						category=fi.getString();
					if(fi.getFieldName().equals("desc"))
						description=fi.getString();
					if(fi.getFieldName().equals("price"))
						cost=fi.getString();
					if(fi.getFieldName().equals("qty"))
						qty=fi.getString();
					if(fi.getFieldName().equals("disc"))
						disc=fi.getString();
					category = category.toLowerCase();
					
					fileSavePath = request.getServletContext().getRealPath("");
					fileSavePath =fileSavePath+"\\img\\products\\"+category+"\\"; //+  File.separator;
					path="img\\products\\"+category+"\\"+fileName;
					System.out.println(path);
					/*File file = new File(fileSavePath+"\\img\\products\\"+category);
					if(!file.exists()){
						if(file.mkdir()){
							System.out.println("New Dir "+category+" created.");
						}
						else{
							System.out.println("Failed to create: "+category);
						}
					}*/
					
					//String realPath="/images/";
					// String fileSavePath=getServletContext().getRealPath(realPath);


					System.out.println("fileSavePath: "+fileSavePath);
					request.setAttribute("filePath", fileSavePath);
				}
				else {
					// Get the uploaded file parameters
					fileName = fi.getName();
					System.out.println("File Name is : " + fileName);
					File file;
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(fileSavePath
								+ fileName.substring(fileName.lastIndexOf("\\")));
					} else {
						file = new File(fileSavePath
								+ fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					factory.setRepository(file);
					fi.write(file);
					System.out.println(fileSavePath);
					System.out.println("<h3>Uploaded Filename: " + fileName + "<br></h3>");
					request.setAttribute("fileName", fileName);
				}


			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//String path=fileSavePath+fileName;

		helpFunctions.crudProduct(pname,path,cost,description,qty, disc, category, mfgname);
		RequestDispatcher rd=request.getRequestDispatcher("admin_home.html");
		rd.include(request, response);
		out.println("<script>alert(' "+pname+" Product Saved');</script>");
	}

}
