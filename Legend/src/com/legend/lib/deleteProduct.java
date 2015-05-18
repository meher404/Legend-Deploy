package com.legend.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class deleteProduct {

	static DBConnection db=null;
	static Connection con=null;
	static Statement st=null;
	static Statement st1=null;
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static PreparedStatement prep;	
	
	public void deleteProductAdmin(String pid){
		try {
			db=new DBConnection();
			con=db.getConnection();
			
			st = con.createStatement();
			
			int i=st.executeUpdate("update product set status=\"Inactive\" where PID='"+pid+"';" );
			System.out.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFromCart(String uid,String pid){
		try {
			db=new DBConnection();
			con=db.getConnection();
			
			st = con.createStatement();
			
			int i=st.executeUpdate("delete from cart where userid='"+uid+"' and PID='"+pid+"' ;" );
			System.out.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
