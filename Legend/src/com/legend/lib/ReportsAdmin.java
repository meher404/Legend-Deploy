package com.legend.lib;

//package com.legend.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

public class ReportsAdmin
{
	DBConnection db=null;
	Connection con=null;
	Statement st=null;
	Statement st1=null;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs=null;
	ResultSet rs1=null;
	ResultSet rs2=null;
	PreparedStatement prep;	

	public String ReportsByProduct()
	{
		//	String str="---------------------------------------------------------------------"+"\n";
		String str="Product;PID;Total Items;Items Sold;Stock;Revenue(INR)?";
		//str=str+"| Product    |"+"PID   |"+"Total Items |"+"Items Sold |"+"Stock |"+"Revenue(INR) |"+"\n";

		//	str=str+"---------------------------------------------------------------------"+"\n";
		int i=0;
		double totalRevenue=0;
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();

			rs1=stmt.executeQuery("select pid,name,price,quantity from product;");
			while(rs1.next())
			{
				String pid=rs1.getString("pid");
				double price=rs1.getDouble("price");
				String name=rs1.getString("name");
				int quantity=rs1.getInt("quantity");
				rs=st.executeQuery("select sum(quantity) from orderdetails where pid='"+pid+"';");
				while(rs.next()){
					int quan=rs.getInt(1);
					int available=quantity-quan;
					double revenue=(price*quan);
					totalRevenue=totalRevenue+revenue;
					str=str+name+";"+pid+";"+quantity+";"+quan+";"+available+";"+revenue+"?";

				}
			}
			//	str=str+"---------------------------------------------------------------------"+"\n";
			str=str+"TOTAL REVENUE : "+totalRevenue+"  INR";
			//	str=str+"---------------------------------------------------------------------"+"\n";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}

	public String ReportsByCategory()
	{
		int sumCategory=0,sale=0;
		double revenueSum=0;
		double totalRevenue=0;
		String str;
		// str="Product;PID;Total Items;Items Sold;Stock;Revenue(INR)?";
		str="Category;CategoryId;Total Items;Items Sold;Stock;Revenue(INR)?";

		//str=str+"---------------------------------------------------------------------"+"\n";
		try {
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();
			st1=con.createStatement();

			rs=st.executeQuery("select * from category");
			while(rs.next()){
				sumCategory=0;
				sale=0;
				revenueSum=0;
				String categoryName=rs.getString("categoryName");
				int categoryId=rs.getInt("categoryid");
				rs1=stmt.executeQuery("select pid,name,price,quantity from product where categoryid='"+categoryId+"';");
				while(rs1.next())
				{
					String pid=rs1.getString("pid");
					double price=rs1.getDouble("price");
					String name=rs1.getString("name");
					int quantity=rs1.getInt("quantity");
					sumCategory=sumCategory+quantity;
					rs2=st1.executeQuery("select sum(quantity) from orderdetails where pid='"+pid+"';");
					while(rs2.next()){
						int quan=rs2.getInt(1);
						sale=sale+quan;

						double revenue=(price*quan);
						revenueSum=revenueSum+revenue;

					}
				}
				totalRevenue=totalRevenue+revenueSum;
				int available=sumCategory-sale;
				str=str+categoryName+";"+categoryId+";"+sumCategory+";"+sale+";"+available+";"+revenueSum+"?";
			}
			str=str+"TOTAL REVENUE : "+totalRevenue+"  INR";
		//	str=str+"										TOTAL REVENUE : "+totalRevenue+"  INR"+"\n";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}

	public String YearlyReports()
	{
		Vector<Integer> vect=new Vector<Integer>();
		String str;
		str="Year;Items Sold;Revenue?";
	//	str=str+"-------------------------------------"+"\n";
		int sales=0,month = 0,year=0;
		double revenueSum=0;
		double totalRevenue=0,revenue=0;
		try {

			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();
			st1=con.createStatement();

			int mnth=0;
			double totalAmount=0;
			rs2=st1.executeQuery("select * from bills");
			while(rs2.next())
			{
				mnth=0;totalAmount=0;
				java.util.Date date1=rs2.getDate("timestamp");
				Calendar cal1 =java.util.Calendar.getInstance();
				cal1.setTime(date1);
				year = cal1.get(Calendar.YEAR);
				//		str=str+"----------YEAR :"+year+"-----------"+"\n";
				if(vect.contains(year)){
					continue;
				}
				vect.add(year);
				
				revenue=0;
				sales=0;
				totalAmount=0;
				rs=st.executeQuery("select * from bills");
				while(rs.next())
				{
					java.util.Date date=rs.getDate("timestamp");
					String saleid=rs.getString("saleid");
					totalAmount=rs.getDouble("totalAmount");
					Calendar cal =java.util.Calendar.getInstance();
					cal.setTime(date);
					month = cal.get(Calendar.MONTH);
					int year1=cal.get(Calendar.YEAR);
					//	System.out.println("Month from bills:"+month+"\tmnth:"+mnth);

					if(year1==year)
					{
						revenue=revenue+totalAmount;
						//		System.out.println("year matched");
						rs1=stmt.executeQuery("select pid,sum(quantity) from orderdetails where saleid='"+saleid+"'group by pid;");
						while(rs1.next())
						{
							String pid=rs1.getString("pid");
							int quantity=rs1.getInt(2);
							sales=sales+quantity;
							//System.out.println("revenue:"+revenue+"\tTotalAmount:"+totalAmount);
						}
					}
				}
				str=str+(year)+";"+sales+";"+revenue+"?";
				totalRevenue=totalRevenue+revenue;
			}
			str=str+"TOTAL REVENUE : "+totalRevenue+"  INR";
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return str;

	}

	public String MonthlyReports()
	{
		Vector<Integer> vect=new Vector<Integer>();
		
		String str;
		str="month;Items Sold;Revenue?";
	
		try {
			int sales=0,month = 0,year=0;
			double revenueSum=0;
			double totalRevenue=0,revenue=0;
			db=new DBConnection();
			con=db.getConnection();
			st = con.createStatement();
			stmt=con.createStatement();
			st1=con.createStatement();

			int mnth=0;
			double totalAmount=0;
			rs2=st1.executeQuery("select * from bills");
			while(rs2.next())
			{
				totalRevenue=0;
				mnth=0;totalAmount=0;
				java.util.Date date1=rs2.getDate("timestamp");
				Calendar cal1 =java.util.Calendar.getInstance();
				cal1.setTime(date1);
				year = cal1.get(Calendar.YEAR);
				
				
				if(vect.contains(year)){
					continue;
				}
				vect.add(year);
				str=str+"----YEAR :;"+year+";----?";
				
				
				while(mnth<12)
				{
					revenue=0;
					sales=0;
					totalAmount=0;
					rs=st.executeQuery("select * from bills");
					while(rs.next())
					{
						java.util.Date date=rs.getDate("timestamp");
						String saleid=rs.getString("saleid");
						totalAmount=rs.getDouble("totalAmount");
						Calendar cal =java.util.Calendar.getInstance();
						cal.setTime(date);
						month = cal.get(Calendar.MONTH);
						int year1=cal.get(Calendar.YEAR);
						//	System.out.println("Month from bills:"+month+"\tmnth:"+mnth);

						if(month==(mnth)&&year1==year)
						{
							revenue=revenue+totalAmount;
							//		System.out.println("month matched");
							rs1=stmt.executeQuery("select pid,sum(quantity) from orderdetails where saleid='"+saleid+"'group by pid;");
							while(rs1.next())
							{
								String pid=rs1.getString("pid");
								int quantity=rs1.getInt(2);
								sales=sales+quantity;
								//		System.out.println("revenue:"+revenue+"\tTotalAmount:"+totalAmount);
							}
						}
					}
					String monthName=theMonth(mnth);
					str=str+(monthName)+";"+sales+";"+revenue+"?";
					mnth=mnth+1;
					totalRevenue=totalRevenue+revenue;
				}
				str=str+"TOTAL REVENUE : "+totalRevenue+"  INR?";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}


	public static String theMonth(int month)
	{
		String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		return monthNames[month];
	}
}
