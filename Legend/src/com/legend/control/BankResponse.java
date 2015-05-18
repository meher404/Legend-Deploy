package com.legend.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.legend.lib.MoreHelpFunctions;
import com.legend.lib.OrderDetails;
import com.legend.lib.User;
import com.legend.lib.deleteProduct;
import com.legend.util.Mailer;
import com.sun.mail.imap.protocol.MailboxInfo;


@WebServlet("/BankResponse")
public class BankResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		deleteProduct delete=new deleteProduct();
		
		OrderDetails order=new OrderDetails();
		response.setContentType("text/html");
		response.setHeader("Pragma", "cache");
		response.setHeader("Cache-Control", "private, must-revalidate");
		MoreHelpFunctions help=new MoreHelpFunctions();
		HttpSession ss= request.getSession();
		User user=(User) ss.getAttribute("user");
		String str="";
		int addid;
		String status=request.getParameter("status");

		if(status.equals("1")){
	
			addid=help.bank_Response(user.getUserID());
			try {
				
				str=order.insertOrders(user,addid);
				String subject="Your Payment Update from LEGEND";
				String[] s =str.split("\\$");  //s[0] html display s[1]-mail
				System.out.println("html: "+s[0]);
				s[1]=s[1]+"\n\t\t\t"+"Legend-The new way to shop"+"\n\t\t\t"+"A project of Chanakya Group";
				Mailer mailer=new Mailer();
				mailer.send(user.getEmail(),subject,s[1]);
				//out.println(s[0]);
				s[0] = "\""+s[0]+"\"";
				RequestDispatcher rd = request.getRequestDispatcher("purchase_cart.html");
				rd.include(request, response);
				//out.println("<script>document.getElementById('contact_menu').style.display='none'</script>");
				out.println("<script>document.getElementById('contact_menu').innerHTML=\"<a href='index.html'>Home</a>\"</script>");
				out.println("<script>document.getElementById('contact').style.display='none'</script>");
				out.println("<script>document.getElementById('news').style.display=''</script>");
				out.println("<script>document.getElementById('ordersummary').style.display=''</script>");
				out.println("<script>document.getElementById('orderdetails').innerHTML="+s[0]+"</script>");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(status.equals("2")){
			help.UnsuccessfulTransaction(user.getUserID());
			out.println("Insufficient Funds.. Transaction Failed");
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		else if(status.equals("3")){
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
