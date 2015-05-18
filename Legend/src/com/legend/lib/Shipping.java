package com.legend.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Shipping {
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

	private String saleid;
	private String recipient;
	private String userid;
	private int addressid;
	private String contactNo;
	public String getSaleid() {
		return saleid;
	}
	public void setSaleid(String saleid) {
		this.saleid = saleid;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getAddressid() {
		return addressid;
	}
	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void insertSaleid(){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			PreparedStatement prep=con.prepareStatement("insert into shipping (recipient,contactNo,addressid,userid) values(?,?,?,?);");
			prep.setString(1,getRecipient());
			System.out.println("Phone number: "+getContactNo());
			prep.setString(2,getContactNo());
			prep.setInt(3,getAddressid());
			prep.setString(4,getUserid());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
