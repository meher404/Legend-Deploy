package com.legend.lib;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Cart {

	DBConnection db=null;
	static Connection con=null;
	static Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs=null;
	static ResultSet rs1=null;
	static PreparedStatement prep;	

	public Cart(){
	
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	private int quantity;
	private String uid;
	private String pid;
	private ArrayList<Product> pids;
	
	
	public ArrayList<Product> getProducts() {
		return pids;
	}

	public void setProducts(ArrayList<Product> pids) {
		this.pids = pids;
	}

	boolean insertCart(Product p){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			boolean validate;
			pid=p.getPID();
			validate=helpFunctions.CheckProductQuantity(pid, quantity);
			if(!validate){
				return false;
			}
			prep = con.prepareStatement("insert into cart values(?,?,?);");
			prep.setString(1,uid);
			prep.setString(2,pid);
			prep.setInt(3,quantity);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	boolean updateCart(int number) throws SQLException{
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean validate=helpFunctions.CheckProductQuantity(getPid(), number);
		if(!validate){
			return false;
		}
		rs=st.executeQuery("select quantity from cart where userID='"+uid+"' and pid='"+pid+"';");
		int quan=rs.getInt("quantity");
		quan=quan+number;
		st.executeUpdate("update cart set quantity='"+quan+"' where userID='"+uid+"' and pid='"+pid+"';");
		return true;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ArrayList<Product> getProducts(String uid){
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<Product> products=new ArrayList<Product>();
		try {
			String proid="";
			String name,pid,image,description;
			double price,discount;
			int quantity,manufactureid,rating,categoryid;
			st=con.createStatement();
			rs=st.executeQuery("select pid from cart where userID='"+uid+"';");
			while(rs.next()){
				proid=rs.getString("pid");
				stmt=con.createStatement();
				rs1=stmt.executeQuery("select * from product where pid='"+proid+"';");
				while(rs1.next()){
					name=rs1.getString("name");
					pid=rs1.getString("pid");
					image=rs1.getString("image");
					price=rs1.getDouble("price");
					quantity=rs1.getInt("quantity");
					description=rs1.getString("description");
					manufactureid=rs1.getInt("manufactureid");
					rating=rs1.getInt("rating");
					discount=rs1.getDouble("discount");
					categoryid=rs1.getInt("categoryid");
					Product pro=new Product();
					pro.setPname(name);
					pro.setCategoryID(categoryid);
					pro.setDescription(description);
					pro.setImagesrc(image);
					pro.setDiscount(discount);
					pro.setManufactureID(manufactureid);
					pro.setPID(pid);
					pro.setPrice(price);
					pro.setQuantity(quantity);
					pro.setRating(rating);
					products.add(pro);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public boolean crudCart(Product p,int quantity)
	{
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();
		
			boolean validate;
			pid=p.getPID();
			validate=helpFunctions.CheckProductQuantity(pid, quantity);
			if(!validate)
			{
				return false;
			}
			rs=st.executeQuery("select * from cart where pid='"+pid+"'and userid='"+uid+"';");
			if(rs.next())
			{
				stmt.executeUpdate("update cart set quantity='"+quantity+"'where pid='"+pid+"'and userid='"+uid+"';");
				helpFunctions.updateProductQuantity(pid,quantity);
				return true;
			}
			prep = con.prepareStatement("insert into cart values(?,?,?);");
			prep.setString(1,uid);
			prep.setString(2,pid);
			prep.setInt(3,quantity);
			helpFunctions.updateProductQuantity(pid,quantity);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	

}
