package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Address {
	DBConnection db=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	static Connection con=null;
	Statement st=null;
	Statement st1=null;
	static PreparedStatement prep;	

	public Address(){
		// TODO Auto-generated constructor stub
	}
	
	private int addressID;
	private String doorNo;
	private String street;
	private String city;
	private String state;
	private Long pincode;

	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	
	public int generateAddressId(long pin){
		db=new DBConnection();
		con=db.getConnection();
		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int addid;
		addid=(int) (((pin+Math.random()*1000)));
		try {
			rs=st.executeQuery("select addressID from address");
			while(rs.next()){
				int str=rs.getInt("addressID");
				if(str==addid){
					System.out.println(addid+"repeated");
					pin=pin/10;
					generateAddressId(pin);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addid;
	}

	public void insertAddress(){
		db=new DBConnection();
		con=db.getConnection();
		try {
			st = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			prep = con.prepareStatement("insert into address VALUES(?,?,?,?,?,?);");
			prep.setInt(1,addressID);
			prep.setString(2, doorNo);
			prep.setString(3,street); 
			prep.setString(4,city);
			prep.setString(5,state); 
			prep.setLong(6, pincode);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	
}
