package com.legend.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Search {
	DBConnection db=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	static Connection con=null;
	static Statement st=null;
	Statement st1=null;
	static PreparedStatement prep;
	
	public ArrayList<Product> search(String key)
	{
		int k = 0;
		try{
			k = Integer.parseInt(key);
			key = k+"";
		}
		catch(Exception e){
			key=key.toLowerCase();
		}
		
		ArrayList<Product> array=new ArrayList<Product>();
		db=new DBConnection();
		con=db.getConnection();
		try {
			st = con.createStatement();
			st1=con.createStatement();

			//****************** Searching Products for key******************//		

			rs=st.executeQuery("select * from product where status='active' ;");
			while(rs.next())
			{
				String pname=rs.getString("name");
				String pid=rs.getString("pid");
				pname=pname.toLowerCase();
		//		pid=pid.toLowerCase();
			//	System.out.println(pname);
				if(pname.length()<key.length()){
					
				}
				
				else if(pname.substring(0,(key.length())).equals(key))
				{
					Product p=new Product();
					p.setCategoryID(rs.getInt("categoryid"));
					p.setDescription(rs.getString("description"));
					p.setDiscount(rs.getDouble("discount"));
					p.setImagesrc(rs.getString("image"));
					p.setManufactureID(rs.getInt("manufactureid"));
					p.setPID(rs.getString("pid"));
					p.setPname(rs.getString("name"));
					p.setPrice(rs.getDouble("price"));
					p.setQuantity(rs.getInt("quantity"));
					p.setRating(rs.getInt("rating"));
					if(!array.contains(p))
						array.add(p);
				}
				else if(pid.length()<key.length()){
					
				}
				else if(pid.substring(0,(key.length())).equals(key))
				{
				//	System.out.println("pid matched");
					Product p=new Product();
					p.setCategoryID(rs.getInt("categoryid"));
					p.setDescription(rs.getString("description"));
					p.setDiscount(rs.getDouble("discount"));
					p.setImagesrc(rs.getString("image"));
					p.setManufactureID(rs.getInt("manufactureid"));
					p.setPID(rs.getString("pid"));
					p.setPname(rs.getString("name"));
					p.setPrice(rs.getDouble("price"));
					p.setQuantity(rs.getInt("quantity"));
					p.setRating(rs.getInt("rating"));
					if(!array.contains(p))
						array.add(p);
				}
			}

			//*************** Searching categories for key******************//

			st=con.createStatement();
			rs=st.executeQuery("select * from category;");
			while(rs.next())
			{
				String cname=rs.getString("categoryname");
				int cid=rs.getInt("categoryid");
				cname=cname.toLowerCase();
				if(cname.length()<key.length()){

				}
				else if(cname.substring(0,(key.length())).equals(key))
				{
					ArrayList<Product> array1=new ArrayList<Product>();
					helpFunctions help=new helpFunctions();
					array1=help.getProductsByCategory(cid);
					for (int i = 0; i < array1.size(); i++) {
						Product p=new Product();
						p=array1.get(i);
						if(!array.contains(p)){
							array.add(p);
						}
					}
				}
			}

			//**** Searching Manufacturers for key******************//

			st=con.createStatement();
			rs=st.executeQuery("select * from manufacturer;");
			while(rs.next())
			{
				String cname=rs.getString("manufacturename");
				int mfgid=rs.getInt("manufactureid");
				cname=cname.toLowerCase();
				//System.out.println(cname);
				if(cname.length()<key.length()){
					//System.out.println("exit");
				}
				else if(cname.substring(0,(key.length())).equals(key))
				{
					//System.out.println("loop");
					st1=con.createStatement();
					rs1=st1.executeQuery("select * from product where manufactureid='"+mfgid+"' and status='active';");
					while(rs1.next())
					{
						//System.out.println("product");
						Product p=new Product();
						p.setCategoryID(rs1.getInt("categoryid"));
						p.setDescription(rs1.getString("description"));
						p.setDiscount(rs1.getDouble("discount"));
						p.setImagesrc(rs1.getString("image"));
						p.setManufactureID(rs1.getInt("manufactureid"));
						p.setPID(rs1.getString("pid"));
						p.setPname(rs1.getString("name"));
						p.setPrice(rs1.getDouble("price"));
						p.setQuantity(rs1.getInt("quantity"));
						p.setRating(rs1.getInt("rating"));
						if(!array.contains(p)){
							array.add(p);
							System.out.println("**add**");
						}
					
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return array;
	}
}
