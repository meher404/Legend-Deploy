package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.legend.lib.Address;
import com.legend.lib.User;

/**
 * Servlet implementation class GetDefaultAddress
 */
@WebServlet("/GetDefaultAddress")
public class GetDefaultAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDefaultAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		response.setContentType("application/json");
		System.out.println("recieved req in default address fetch");
		Address add = u.getAddress(u.getAddressID());
		
		String jsonObj = "{\"name\":\""+u.getName()+"\","
				+ "\"number\":\""+u.getPhne()+"\","
				+ "\"dno\":\""+add.getDoorNo()+"\","
				+ "\"street\":\""+add.getStreet()+"\","
				+ "\"city\":\""+add.getCity()+"\","
				+ "\"state\":\""+add.getState()+"\","
				+ "\"pin\":\""+add.getPincode()+"\""
				+ "}";
		System.out.println("json: "+jsonObj);
		PrintWriter out = response.getWriter();
		out.println(jsonObj);
	}

}
