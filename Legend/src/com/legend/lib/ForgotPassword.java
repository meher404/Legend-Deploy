package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.legend.util.Mailer;


public class ForgotPassword {
	DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static ResultSet rs2=null;
	static PreparedStatement prep;	

	public ForgotPassword(){

	}

	public boolean sendMail(String email){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String subject="Legend Password Assistance";
		String pwd = "";
		try {
			rs=st.executeQuery("select password from user where email='"+email+"';");
			if(rs.next()){
				pwd=rs.getString("password");
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String msg="Use this confirmation code to reset your Legend password"+"\n"+"Confirmation code: "+pwd;
		msg=msg+"\n\t\t\t"+"Legend-The new way to shop"+"\n\t\t\t"+"A project of Chanakya Group";
		System.out.println(msg);
		Mailer.send(email, subject, msg);
		return true;
	}

	public boolean verify(String email,String code){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String pwd = "";
		try {
			rs=st.executeQuery("select password from user where email='"+email+"';");
			if(rs.next()){
				pwd=rs.getString("password");
				if(code.equals(pwd)){
					return true;
				}
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void newPassword(String email,String pwd){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		CaesarCipher c=new CaesarCipher();
		String encPwd=c.encrypt(pwd, 2);
		try {
			stmt.executeUpdate("update user set password='"+encPwd+"'where email='"+email+"';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
