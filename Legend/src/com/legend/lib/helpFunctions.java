package com.legend.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class helpFunctions {

	static DBConnection db=null;
	static Connection con=null;
	static Statement st=null;
	static Statement st1=null;
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static PreparedStatement prep;	

	public helpFunctions(){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			st1=con.createStatement();
			stmt=con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	public User getFilledUser(String email){
		HashMap<Product, Integer> cart=new HashMap<Product, Integer>();
		ArrayList<Bills> bills=new ArrayList<Bills>();
		ArrayList<OrderDetails> orders=new ArrayList<OrderDetails>();
		UserObject userObj=new UserObject();

		User user=new User();
		user=userObj.userObject(email);
		try {
			bills=user.getBills();
			orders=user.getOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cart=user.getCart();

		user.bills = bills;
		user.cart = cart;
		user.orders = orders;

		return user;
	}
	
	

	public static boolean Login(String email,String pwd){
		String encryptedPwd,status = "";
		CaesarCipher cd=new CaesarCipher();
		encryptedPwd=cd.encrypt(pwd,2);
		try {
			rs=st.executeQuery("select password from user where email='"+email+"';");
			if(rs==null)return false;
			
				rs.next();
				String passwrd=rs.getString("password");
				if(passwrd==null||passwrd.isEmpty()) return false;
				
				if(passwrd.equals(encryptedPwd))return true;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean CheckProductQuantity(String pid,int number){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			rs=st.executeQuery("select quantity from product where pid='"+pid+"';");
			if(rs.next()){
				int quan=rs.getInt("quantity");
				if(quan>=number){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean updateProductQuantity(String pid,int number){
		try{
			int quan = 0,value;
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			rs=st.executeQuery("select quantity from product where pid='"+pid+"';");
			if(rs.next()){
				quan=rs.getInt("quantity");
			}
			value=quan-number;
			st.executeUpdate("update product set quantity='"+value+"'where pid='"+pid+"';");
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

	public static boolean checkStatus(String email){
		String status="";
		try {
			st=con.createStatement();
			rs=st.executeQuery("select userid from user where email='"+email+"';");
			while(rs.next()){
				String uid=rs.getString("userid");
				stmt=con.createStatement();
				rs1=stmt.executeQuery("select status from registration where userid='"+uid+"';");
				if(rs1.next()){
					status=rs1.getString("status");
				}
				if(status.equals("active")){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public static boolean checkExistingEmail(String email) throws SQLException{
		st=con.createStatement();
		rs=st.executeQuery("select email from user");
		while(rs.next()){
			String mail=rs.getString("email");
			if(mail.equals(email)){
				return true;
			}
		}
		return false;
	}

	public static String generateSaleid(){
		String temp="sale00";
		int tem=1+(int)(Math.random() *500);
		temp=temp+tem+"";
		return temp;
	}
	
	public static void crudProduct(String name,String imagepath,String price,String description,String quantity,String discount,String catName,String manName)
	{  
		try {
			Category c= new Category();
			Manufacturer m= new Manufacturer();
			System.out.println(catName);
			int categoryId=c.generateCategoryID(catName);
			int manufacturerId=m.generatemanufactureID(manName);
			Product p=new Product();
			boolean existing=true;
			String PID;
			PID=existingPID(name);
			if(PID.equals("")){
				//PID=p.GeneratePID(categoryId, manufacturerId);
				PID = (name.hashCode()+"")+(categoryId+"")+(manufacturerId+"");
				existing=false;
			}
			double price1 = 0,discount1 = 0;
			int quantity1 = 0;
			st=con.createStatement();
			boolean flag=false;
			if(existing){
				rs=st.executeQuery("select * from product where pid='"+PID+"';");

				if(rs.next())
				{
					String proId=rs.getString("pid");
					String pname=rs.getString("name");
					String pimg=rs.getString("image");
					double pprice=rs.getDouble("price");
					int pquantity=rs.getInt("quantity");
					String pdescription=rs.getString("description");
					double pdiscount=rs.getDouble("discount");
					int manfId=rs.getInt("manufactureid");
					int catId=rs.getInt("categoryid");
					int rating=rs.getInt("rating");
					//	System.out.println("From main : "+PID+"\nFrom database: "+proId);

					if(name.equals("")){
						name=pname;
					}
					if(description.equals("")){
						description=pdescription;
					}
					if(imagepath.equals("")){
						imagepath=pimg;
					}
					if(price.equals(""))
					{
						price1=pprice;
					}
					else
					{
						price1=Double.parseDouble(price);
					}
					if(quantity.equals(""))
					{
						quantity1=pquantity;
					}
					else
					{
						quantity1=Integer.parseInt(quantity);
					}
					if(discount.equals(""))
					{
						discount1=pdiscount;
					}
					else
					{
						discount1=Double.parseDouble(discount);
						//System.out.println("discount1: "+discount1);
					}

					flag=true;
					String sql        = "UPDATE product SET name = ?,image=?,price=?,quantity=?,description=?,discount=? "
							+ " WHERE pid = ?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1,name);
					pst.setString(2,imagepath);
					pst.setDouble(3,price1);
					pst.setInt(4,quantity1);
					pst.setString(5,description);
					pst.setDouble(6,discount1);
					pst.setString(7,proId);
					pst.executeUpdate();
				}
			}
			else if(!existing)
			{
				System.out.println("price:"+price);
				price1=Double.parseDouble(price);
				discount1=Double.parseDouble(discount);
				quantity1=Integer.parseInt(quantity);
				prep=con.prepareStatement("insert into product values(?,?,?,?,?,?,?,?,?,?,?);");
				prep.setString(1,name);
				prep.setString(2, PID);
				prep.setString(3,imagepath);
				prep.setDouble(4,price1);
				prep.setInt(5,quantity1);
				prep.setString(6,description);
				prep.setInt(7,manufacturerId);
				prep.setInt(8,4);
				prep.setDouble(9,discount1);
				prep.setInt(10,categoryId);
				prep.setString(11,"active");
				prep.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getProductsByCategory(int catId) throws SQLException{
		ArrayList<Product> array=new ArrayList<Product>();
		try {
			st=con.createStatement();
			rs=st.executeQuery("select * from product where categoryid='"+catId+"' and status='active' ;");
			while(rs.next())
			{
				Product pro=new Product();
				pro.setCategoryID(rs.getInt("categoryId"));
				pro.setDescription(rs.getString("description"));
				pro.setDiscount(rs.getDouble("discount"));
				pro.setImagesrc(rs.getString("image"));
				pro.setManufactureID(rs.getInt("manufactureId"));
				pro.setPID(rs.getString("pid"));
				pro.setPname(rs.getString("name"));
				pro.setPrice(rs.getDouble("price"));
				pro.setQuantity(rs.getInt("quantity"));
				pro.setRating(rs.getInt("rating"));
				array.add(pro);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}

	public ArrayList<Product> getNewProducts(){
		ArrayList<Product> array=new ArrayList<Product>();
		try {
			st=con.createStatement();
			rs=st.executeQuery("select * from product where status='active'  limit 3;");
			while(rs.next())
			{
				Product pro=new Product();
				pro.setCategoryID(rs.getInt("categoryId"));
				pro.setDescription(rs.getString("description"));
				pro.setDiscount(rs.getDouble("discount"));
				pro.setImagesrc(rs.getString("image"));
				pro.setManufactureID(rs.getInt("manufactureId"));
				pro.setPID(rs.getString("pid"));
				pro.setPname(rs.getString("name"));
				pro.setPrice(rs.getDouble("price"));
				pro.setQuantity(rs.getInt("quantity"));
				pro.setRating(rs.getInt("rating"));
				array.add(pro);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}

	public boolean CheckProduct(String pname){
		try {
			st=con.createStatement();
			pname=pname.toLowerCase();
			rs=st.executeQuery("select name from product;");
			while(rs.next()){
				String name=rs.getString("name");
				name=name.toLowerCase();
				if(name.equals(pname)){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String existingPID(String pname){
		String pid="";
		try {

			st=con.createStatement();
			rs=st.executeQuery("select pid from product where name='"+pname+"';");
			if(rs.next()){
				String str=rs.getString("pid");
				pid=str;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pid;
	}

	public String getManufactureName(int manufactureID){
		db=new DBConnection();
		con=db.getConnection();
		String name="";
		try {
			st=con.createStatement();
			rs=st.executeQuery("select manufacturename from manufacturer where manufactureid='"+manufactureID+"';");
			while(rs.next()){
				name=rs.getString("manufacturename");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	public String getCategoryName(int CategoryID){
		db=new DBConnection();
		con=db.getConnection();
		String name="";
		try {
			st=con.createStatement();
			rs=st.executeQuery("select Categoryname from category where CategoryID='"+CategoryID+"';");
			while(rs.next()){
				name=rs.getString("Categoryname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	public static Product getProduct(String pid){
		Product pro=new Product();
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			//st=con.createStatement();
			rs1=st.executeQuery("select * from product where pid='"+pid+"';");
			rs1.next();

			pro.setPname(rs1.getString("name"));
			pro.setCategoryID(rs1.getInt("categoryid"));
			pro.setDescription(rs1.getString("description"));
			pro.setImagesrc(rs1.getString("image"));
			pro.setDiscount(rs1.getDouble("discount"));
			pro.setManufactureID(rs1.getInt("manufactureid"));
			pro.setPID(pid);
			pro.setPrice(rs1.getDouble("price"));
			pro.setQuantity(rs1.getInt("quantity"));
			pro.setRating(rs1.getInt("rating"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return pro;
	}

}
