package com.legend.lib;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Bills {
	DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static PreparedStatement prep;	

	public Bills(){
	
	}

	private String saleid;
	private int addressID;
	private java.util.Date timeStamp;
	private double totalAmount;
	private String DeliveryStatus;

	public String getSaleid() {
		return saleid;
	}
	public void setSaleid(String saleid) {
		this.saleid = saleid;
	}
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public java.util.Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(java.util.Date timeStamp2) {
		this.timeStamp = timeStamp2;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getDeliveryStatus() {
		return DeliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.DeliveryStatus = deliveryStatus;
	}
	
	public double calculateTotal(String saleid)
	{
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		double total=0;
		try {
			rs=st.executeQuery("select pid,quantity from orderdetails where saleid='"+saleid+"';");
			while(rs.next()){
				String pid=rs.getString("pid");
				int quantity=rs.getInt("quantity");
				rs1=stmt.executeQuery("select price from product where pid='"+pid+"';");
				if(rs1.next()){
					double amt=rs1.getDouble("price");
					total=total+(amt*quantity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	void insertBills(OrderDetails Od){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		saleid=Od.getSaleid();
		totalAmount = calculateTotal(saleid);
		try {
			prep = con.prepareStatement("insert into bills values(?,?,?,?,?);");
			prep.setDate(1,(Date) timeStamp);
			prep.setInt(2, addressID);
			prep.setString(3,saleid);
			prep.setDouble(4, totalAmount);
			prep.setString(5, DeliveryStatus);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
