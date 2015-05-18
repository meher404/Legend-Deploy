package com.legend.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserObject {

	DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static Statement stmt2 = null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static ResultSet rs2=null;
	static PreparedStatement prep;	
	
	public User userObject(String email){
		User user=new User();
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			
			rs=st.executeQuery("select * from user where email='"+email+"';");
			while(rs.next()){
				user.setAddressID(rs.getInt("addressid"));
				user.setDob(rs.getDate("dob"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setName(rs.getString("name"));
				user.setPhne(rs.getLong("phone"));
				user.setPwd(rs.getString("password"));
				user.setUserID(rs.getString("userId"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
