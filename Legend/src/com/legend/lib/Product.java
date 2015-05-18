package com.legend.lib;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Product {

	DBConnection db=null;
	static Connection con=null;
	Statement st=null;

	static Connection conn = null;
	static Statement stmt = null;
	ResultSet rs=null;
	static PreparedStatement prep;	

	private  String pName;
	private  String PID;
	private  String imagesrc;
	private  double price;
	private  int quantity;
	private  String description;
	private  int manufactureID;
	private  int rating;
	private  double discount;
	private  int categoryID;
	//private Category cat;//=new Category();
	//private Manufacturer man;//=new Manufacturer();

	public Product(){


	}

	public int getManufactureID() {
		return manufactureID;
	}

	public void setManufactureID(int manufactureID) {
		this.manufactureID = manufactureID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getPname() {
		return pName;
	}
	
	public void setPname(String pName) {

		this.pName = pName;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getImagesrc() {
		return imagesrc;
	}
	
	public void setImagesrc(String imagesrc) {
		this.imagesrc = imagesrc;
	}
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}




	public Category getCategory(int categoryID) throws SQLException{
		db=new DBConnection();
		con=db.getConnection();
		st = con.createStatement();

		Category c=new Category();
		String name=null;
		try {
			rs=st.executeQuery("select categoryName from Category where categoryID='"+categoryID+"';");
			if(rs.next())
			{
				name=rs.getString("categoryName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		c.setCategoryName(name);
		c.setCategoryID(categoryID);

		//con.close();
		return c;
	}

	public Manufacturer getManufacturer(int ManufactureID) throws SQLException{
		db=new DBConnection();
		con=db.getConnection();
		st = con.createStatement();
		Manufacturer m=new Manufacturer();
		String name=null,desc=null;
		try {
			rs=st.executeQuery("select manufactureName,description from manufacturer where ManufactureID='"+ManufactureID+"';");
			if(rs.next())
			{
				name=rs.getString("manufactureName");
				desc=rs.getString("description");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.setManfDescription(desc);
		m.setManufactureID(ManufactureID);
		m.setManufacturerName(name);
		//con.close();
		return m;

	}

	public String GeneratePID(int catID,int manfID){
		db=new DBConnection();
		con=db.getConnection();
		String id="";
		id=catID+"";
		id=id+manfID+"";
		try {
			st = con.createStatement();
			rs=st.executeQuery("select pid from product");
			while(rs.next()){
				String temp=rs.getString("pid");
				if(temp.equals(id)){
					int i=Integer.parseInt(id);
					GeneratePID(i,5);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

	}

	@Override
	public int hashCode() {
		return PID.hashCode();
	}

	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Product other = (Product) obj;
		if(this.getPID().equals(other.getPID()))
			return true;
		else
			return false;
	}

	void insertDB() throws SQLException
	{
		db=new DBConnection();
		con=db.getConnection();
		st = con.createStatement();

		try {
			prep = con.prepareStatement("insert into product VALUES(?,?,?,?,?,?,?,?,?,?,?);");
			prep.setString(1,pName);
			prep.setString(2, PID);
			prep.setString(3,imagesrc); 
			prep.setDouble(4,price);
			prep.setInt(5,quantity); 
			prep.setString(6, description);
			prep.setInt(7,manufactureID); 
			prep.setInt(8, rating);
			prep.setDouble(9,discount); 
			prep.setInt(10,categoryID);
			prep.setString(11,"active");
			prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//con.close();
		} 
		//con.close();
	}

	void deleteDB() throws SQLException
	{
		db=new DBConnection();
		con=db.getConnection();
		st = con.createStatement();
		try {
			Statement st=conn.createStatement();
			String s="delete *from product where pid='"+PID+"';";
			st.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//con.close();
		}
		//con.close();
	}


	public String toString(){
		String prod;
		try {
			prod = "<div class=\"span4\">"+
					"<div class=\"mask2\" onclick=\"unHide('"+PID+"')\"><img width='225' height='225' src=\""+imagesrc+"\" alt=\"\"></a></div>"+
					"<div class=\"inside\" id=\""+PID+"\" name=\""+PID+"\">"+
					"<hgroup><h4>"+pName+"</h4></hgroup>"+
					"<div class=\"entry-content\">"+
					"<table class=\"table\">"+
					"<tr><td>Name: </td><td>"+pName+"</td></tr>"+
					"<tr><td>Price: </td><td>Rs. "+price+"</td></tr>"+
					"<tr><td>Category: </td><td>"+getCategory(categoryID).getCategoryName()+"</td></tr>"+
					"</table>"+
					"<a class=\"more-link\" onclick=\"unHide('"+PID+"')\">ADD TO CART</a> </div>"+
					"</div></div>";
		} catch (SQLException e) {
			return "Product failed to load";
		}
		return prod;
	}
	
	public String toCartString(int qty){
		String prod;
		try {
			prod = "<div class=\"span4\">"+
					"<div class=\"mask2\"><a onclick=\"retrieveDetails('"+PID+"')\"><img width='225' height='225' src=\""+imagesrc+"\" alt=\"\"></a></div>"+
					"<div class=\"inside\" id=\""+PID+"_1\" name=\""+PID+"_1\">"+
					"<hgroup><h4>"+pName+"</h4></hgroup>"+
					"<div class=\"entry-content\">"+
					"<table class=\"table\">"+
					"<tr><td>Name: </td><td>"+pName+"</td></tr>"+
					"<tr><td>Price: </td><td>Rs. "+price+"</td></tr>"+
					"<tr><td>Category: </td><td>"+getCategory(categoryID).getCategoryName()+"</td></tr>"+
					"<tr><td>Quantity: </td><td>"+qty+"</td></tr>"+
					"<tr><td><h4>Total: </h4></td><td><h3>Rs. "+(qty*price)+"</h3></td></tr>"+
					"<tr><td><a onclick=\"retrieveDetails('"+PID+"')\"><img height='40' width='40' src=\"img/edit.png\" />EDIT Product</a></td><td><a onclick=\"deleteProd('"+PID+"')\"><img height='40' width='40' src=\"img/delete.jpg\" />REMOVE Product</a></td></tr>"+
					"</table>"+
					"</div></div></div>";
		} catch (SQLException e) {
			return "Product failed to load";
		}
		return prod;
	}
	
	public String AddToCartString(){
		String prod;
		try {
			prod = "<div class=\"span4\">"+
					"<div class=\"mask2\" \"><img width='150' height='150' src=\""+imagesrc+"\" alt=\"\"></a></div>"+
					"<div class=\"inside\" id=\""+PID+"_1\" name=\""+PID+"_1\">"+
					"<hgroup><h4>"+pName+"</h4></hgroup>"+
					"<div class=\"entry-content\">"+
					"<table class=\"table\">"+
					"<tr><td>Name: </td><td>"+pName+"</td></tr>"+
					"<tr><td>Price: </td><td>Rs. <span id=\"price\">"+price+"</span></td></tr>"+
					"<tr><td>Discription: </td><td>"+description+"</td></tr>"+
					"<tr><td>Category: </td><td>"+getCategory(categoryID).getCategoryName()+"</td></tr>"+
					"<tr><td>Manufacturer: </td><td>"+getManufacturer(manufactureID).getManufacturerName()+"</td></tr>"+
					"<tr><td>Quantity: </td><td><input type=\"text\" class='span2' name=\"qty\" id=\"qty\" maxlength=\"4\" data-validation=\"number\" data-validation-error-msg=\"Invalid Quantity value\"  /></td></tr>"+
					"</table>"+
					"<input type=\"hidden\" name=\"pid\" value='"+PID+"' />"+
					"<div class='span2' align='center'><input type='submit' value='ADD' /></div>"+
					"</div></div></div>";
		} catch (SQLException e) {
			return "Product failed to load";
		}
		return prod;
	}
	

}








