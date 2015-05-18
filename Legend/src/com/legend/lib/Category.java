package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Category
{
	DBConnection db=null;
	static ResultSet rs=null;
	ResultSet rs1=null;
	static Connection con=null;
	static Statement st=null;
	Statement st1=null;
	static PreparedStatement prep;	
	
	
	public Category() {
		
	}

	
	private int categoryID=0;
	private String categoryName;
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public int generateCategoryID(String CategoryName) throws SQLException{
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			st1=con.createStatement();
			rs=st.executeQuery("SELECT categoryName FROM category;");
			while(rs.next())
			{
				String name=rs.getString("categoryName");
				String name1=name.toLowerCase();
		//		System.out.println("cat name : "+name);
				String CategoryName1=CategoryName.toLowerCase();
				if(CategoryName1.equals(name1)){
					rs1=st1.executeQuery("SELECT categoryID FROM category where categoryName='"+CategoryName+"';");
					System.out.println("category name exists");
					if(rs1.next())
						categoryID=rs1.getInt("categoryID");
					return categoryID;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 2; i++) {
			categoryID=categoryID+(int)CategoryName.charAt(i);
			
		}
		categoryID=categoryID+1000+CategoryName.length();
		st = con.createStatement();
		rs1=st.executeQuery("SELECT categoryID FROM category;");
		while(rs1.next()){
			int temp=rs1.getInt("categoryID");
			if(temp==categoryID){
				categoryID=categoryID+1;
			}
		}
		setCategoryName(CategoryName);
		setCategoryID(categoryID);
		insert();
		return categoryID;
	}
	
	
	public void  insert()
	{
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			st1=con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			st=con.createStatement();
			prep = con.prepareStatement("insert into Category VALUES(?,?);");
			prep.setInt(1,categoryID);
			prep.setString(2,categoryName);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}