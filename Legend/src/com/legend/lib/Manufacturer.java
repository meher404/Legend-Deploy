package com.legend.lib;

import java.sql.*;

public class Manufacturer
{
	DBConnection db=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	static Connection con=null;
	Statement st=null;
	Statement st1=null;
	static PreparedStatement prep;	
	public Manufacturer(){

	}



	private String manufacturerName;
	private String manfDescription;
	private int manufactureID;
	public int getManufactureID() {
		return manufactureID;
	}
	
	public void setManufactureID(int manufactureID) {
		this.manufactureID = manufactureID;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getManfDescription() {
		return manfDescription;
	}
	public void setManfDescription(String manfDescription) {
		this.manfDescription = manfDescription;
	}


	public int generatemanufactureID(String manufacturerName)
	{
		db=new DBConnection();
		con=db.getConnection();
		try {
			st = con.createStatement();
			st1 = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			rs=st.executeQuery("SELECT manufactureName FROM manufacturer;");
			while(rs.next())
			{
				String name=rs.getString("manufactureName");
				String manufacturerName1=manufacturerName.toLowerCase();
				String name1=name.toLowerCase();
				System.out.println(name);
				if(manufacturerName1.equals(name1)){
					rs1=st1.executeQuery("SELECT manufactureID FROM manufacturer where manufactureName='"+manufacturerName+"';");
					if(rs1.next())
						manufactureID=rs1.getInt("manufactureID");
					return manufactureID;

				}
			}

			for (int i = manufacturerName.length()-1; i >manufacturerName.length()-3; i--) {
				manufactureID=manufactureID+(int)manufacturerName.charAt(i);
			}
			manufactureID=manufactureID+1000+manufacturerName.length();
			st = con.createStatement();
			rs1=st.executeQuery("SELECT manufactureID FROM manufacturer;");
			while(rs1.next()){
				int temp=rs1.getInt("manufactureID");
				if(temp==manufactureID){
					manufactureID=manufactureID+1;
				}
			}
			setManufacturerName(manufacturerName);
			setManfDescription(manufacturerName);
			setManufactureID(manufactureID);
			insert();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manufactureID;

	}

	public void insert()
	{
		db=new DBConnection();
		con=db.getConnection();
		try {
			st = con.createStatement();
			prep = con.prepareStatement("insert into manufacturer VALUES(?,?,?);");
			prep.setInt(1,manufactureID);
			prep.setString(2, manufacturerName);
			prep.setString(3, manfDescription);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}