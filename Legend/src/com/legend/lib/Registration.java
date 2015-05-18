package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.legend.util.Mailer;


public class Registration {
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

	public Registration(){
		
	}

	@SuppressWarnings("static-access")
	public void generateCode(String userId,String email){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt = con.createStatement();
			stmt2 = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Mailer mail=new Mailer();
		int n = 6;
		String status="not-activated";
		Random randGen = new Random();
		int startNum = (int) Math.pow(10, n-1);
		int range = (int) (Math.pow(10, n) - startNum + 1);
		int randomNum = randGen.nextInt(range) + startNum;
		try {
			prep = con.prepareStatement("insert into registration VALUES(?,?,?);");
			prep.setString(1,userId);
			prep.setInt(2, randomNum);
			prep.setString(3, status);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String subject="Activation of your Account";
		String msg="Congratulations.. You have succesfully registered with Legend"+"\n"+"Enter this confirmation code to validate your account"+"\n"+"Code : "+randomNum+"";
		msg=msg+"\n\t\t\t"+"Legend-The new way to shop"+"\n\t\t\t"+"A project of Chanakya Group";
		System.out.println(msg);
		mail.send(email, subject, msg);
	}

	public boolean validateAccount(String email,int code){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt = con.createStatement();
			stmt2 = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			int number=0;
			rs1=stmt.executeQuery("select userId from user where email='"+email+"';");
			if(rs1.next()){
				String userId=rs1.getString("userId");
				rs=st.executeQuery("select tempCode from registration where userID='"+userId+"';");
				if(rs.next()){
					number=rs.getInt("tempCode");
					if(number==code){
						stmt2.executeUpdate("update registration set status=\"active\" where userID='"+userId+"';");
						return true;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;		
	}
}
