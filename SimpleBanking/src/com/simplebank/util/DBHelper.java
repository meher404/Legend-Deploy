package com.simplebank.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class DBHelper {
	
	static Properties prop = null;
	static InputStream input = null;
	static private Connection connect = null;
	static Statement st = null;
	
	/*public static boolean newPost(String un, String pwd){
		
		try {
			
			prop = new Properties();
			input = new FileInputStream("D://MS(IT)//Java//Project(PayPal)//SimpleBanking//databasedetails.properties");
			prop.load(input);
			
			Class.forName(prop.getProperty("classname"));
		    connect = DriverManager.getConnection(prop.getProperty("url")
		              + "user="+prop.getProperty("username")+"&password="+prop.getProperty("password"));
		      
		    
			
			PreparedStatement ps = connect.prepareStatement("insert into post (uname,title,post,timestamp) values (?,?,?,?)");
			ps.setString(1, un);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));
			ps.executeUpdate();
			ps.close();

			return true;
		} catch (Exception e) {
			System.out.println("New Post error..");
			e.printStackTrace();
			return false;
		}
		finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (Exception e) {
					System.out.println("Error during Connection Closed 4");
				}
			}
		}
	}*/
	
	public static boolean login(String email, String pwd){
		
		try {
			
			prop = new Properties();
			input = new FileInputStream("D://MS(IT)//Java//Project(PayPal)//SimpleBanking//databasedetails.properties");
			prop.load(input);
			
			Class.forName(prop.getProperty("classname"));
		    connect = DriverManager.getConnection(prop.getProperty("url")
		              + "user="+prop.getProperty("username")+"&password="+prop.getProperty("password"));
		    st = connect.createStatement();  
		    ResultSet rs = st.executeQuery("select password from bankuser where email='"+email+"' and activestatus=1 ;");
		    if(rs==null)
		    	return false;
		    else{
		    	rs.next();
		    	String p = rs.getString(1);
		    	System.out.println("pwd match?: "+p.equals(pwd));
		    	if(p.equals(pwd))
		    		return true;
		    }
		    return false;
		} catch (Exception e) {
			System.out.println("login check error..");
			//e.printStackTrace();
			return false;
		}
		finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (Exception e) {
					System.out.println("Error during Connection Closed 4");
				}
			}
		}
	}
	
	public static boolean validateTransaction(String email, String amt){
		
		try {
			
			prop = new Properties();
			input = new FileInputStream("D://MS(IT)//Java//Project(PayPal)//SimpleBanking//databasedetails.properties");
			prop.load(input);
			String accno="";
			Class.forName(prop.getProperty("classname"));
		    connect = DriverManager.getConnection(prop.getProperty("url")
		              + "user="+prop.getProperty("username")+"&password="+prop.getProperty("password"));
		    st = connect.createStatement();  
		    ResultSet rs = st.executeQuery("select accno from bankuser where email='"+email+"' and activestatus=1 ;");
		    if(rs==null)
		    	return false;
		    else{
		    	rs.next();
		    	accno = rs.getString(1);
		    }
		    rs = st.executeQuery("select balance from accountdetails where accno="+accno+" ;");
		    rs.next();
		    double bal = rs.getDouble(1);
		    double amount = Double.parseDouble(amt);
		    if(bal>amount)
		    	return true;
		    
		    return false;
		} catch (Exception e) {
			System.out.println("login check error..");
			//e.printStackTrace();
			return false;
		}
		finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (Exception e) {
					System.out.println("Error during Connection Closed 4");
				}
			}
		}
	}
	
	public static boolean activate(String email, String accno, String acode){
		
		try {
			
			prop = new Properties();
			input = new FileInputStream("D://MS(IT)//Java//Project(PayPal)//SimpleBanking//databasedetails.properties");
			prop.load(input);
			
			Class.forName(prop.getProperty("classname"));
		    connect = DriverManager.getConnection(prop.getProperty("url")
		              + "user="+prop.getProperty("username")+"&password="+prop.getProperty("password"));
		    st = connect.createStatement();  
		    ResultSet rs = st.executeQuery("select temppwd from bankuser where email='"+email+"' and accno="+accno+" ;");
		    int k = 9;
		    System.out.println("email: "+email+" accno: "+accno+" acode: "+acode);
		    if(rs==null)
		    	return false;
		    else{
		    	rs.next();
		    	String p = rs.getString(1);
		    	if(p.equals(acode)){
		    		k = st.executeUpdate("update bankuser set activestatus=1 where email='"+email+"' and accno="+accno+" ");
		    		if(k==0)
		    			return false;
		    		else
		    			return true;
		    	}
		    		
		    }
		    return false;
		} catch (Exception e) {
			System.out.println("login check error..");
			e.printStackTrace();
			return false;
		}
		finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (Exception e) {
					System.out.println("Error during Connection Closed 4");
				}
			}
		}
	}
	
	public static boolean setPassword(String email, String accno, String pwd){
		
		try {
			
			prop = new Properties();
			input = new FileInputStream("D://MS(IT)//Java//Project(PayPal)//SimpleBanking//databasedetails.properties");
			prop.load(input);
			
			Class.forName(prop.getProperty("classname"));
		    connect = DriverManager.getConnection(prop.getProperty("url")
		              + "user="+prop.getProperty("username")+"&password="+prop.getProperty("password"));
		      
		    	st = connect.createStatement();
		    	int	k = st.executeUpdate("update bankuser set password='"+pwd+"' where email='"+email+"' and accno="+accno+" ");
		    	if(k==0)
		    		return false;
		    	else
		    		return true;
		} catch (Exception e) {
			System.out.println("login check error..");
			//e.printStackTrace();
			return false;
		}
		finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (Exception e) {
					System.out.println("Error during Connection Closed 4");
				}
			}
		}
	}
	
	public static String genRandomActCode(){
		int n = 6;
		Random randGen = new Random();
		int startNum = (int) Math.pow(10, n-1);
		int range = (int) (Math.pow(10, n) - startNum + 1);
		int randomNum = randGen.nextInt(range) + startNum;
		return randomNum+"";
	}
	
	public static String sendActivationCode(String accno, String email){
		
		try {
			
			prop = new Properties();
			input = new FileInputStream("D://MS(IT)//Java//Project(PayPal)//SimpleBanking//databasedetails.properties");
			prop.load(input);
			
			Class.forName(prop.getProperty("classname"));
		    connect = DriverManager.getConnection(prop.getProperty("url")
		              + "user="+prop.getProperty("username")+"&password="+prop.getProperty("password"));
		      
		    System.out.println("email in db: "+email);
			//ResultSet rs = st.executeQuery("select * from bankuser where accno="+accno+" and email='"+email+"' ;")
			
			PreparedStatement ps = connect.prepareStatement("update bankuser set temppwd=? where accno="+accno+" and email='"+email+"' ;");
			String temp = genRandomActCode();
			ps.setString(1, temp);
			Mailer.send(email, "OTP from Simple Bank", "Please use this One Time Password to proceed with your trasaction/activation. \n OTP: "+temp);
			int k = ps.executeUpdate();
			ps.close();
			System.out.println("k= "+k);
			
			if(k==0)
				return null;
			
			return temp;
		} catch (Exception e) {
			System.out.println("New Post error..");
			e.printStackTrace();
			return null;
		}
		finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (Exception e) {
					System.out.println("Error during Connection Closed 4");
				}
			}
		}
	}
	
	public static String getAccountNumber(String email){
		
		try {
			
			prop = new Properties();
			input = new FileInputStream("D://MS(IT)//Java//Project(PayPal)//SimpleBanking//databasedetails.properties");
			prop.load(input);
			
			Class.forName(prop.getProperty("classname"));
		    connect = DriverManager.getConnection(prop.getProperty("url")
		              + "user="+prop.getProperty("username")+"&password="+prop.getProperty("password"));
		      
		    st = connect.createStatement();
			ResultSet rs = st.executeQuery("select accno from bankuser where email='"+email+"' ;");
			rs.next();
			String temp = rs.getString(1);
			return temp;
		} catch (Exception e) {
			System.out.println("New Post error..");
			e.printStackTrace();
			return null;
		}
		finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (Exception e) {
					System.out.println("Error during Connection Closed 4");
				}
			}
		}
	}

	public static boolean confirmOTP(String email, String accno, String code,
			String amt, String vendor) {
		
		try {
			
			prop = new Properties();
			input = new FileInputStream("D://MS(IT)//Java//Project(PayPal)//SimpleBanking//databasedetails.properties");
			prop.load(input);
			
			Class.forName(prop.getProperty("classname"));
		    connect = DriverManager.getConnection(prop.getProperty("url")
		              + "user="+prop.getProperty("username")+"&password="+prop.getProperty("password"));
		    st = connect.createStatement();  
		    ResultSet rs = st.executeQuery("select temppwd from bankuser where email='"+email+"' and accno="+accno+" ;");
		    int k = 9;
		    System.out.println("email: "+email+" accno: "+accno+" acode: "+code+" amount: "+amt+" vendor: "+vendor);
		    if(rs==null)
		    	return false;
		    else{
		    	rs.next();
		    	String p = rs.getString(1);
		    	if(p.equals(code)){
		    		Double a = Double.parseDouble(amt);
		    		k = st.executeUpdate("update accountdetails set balance=balance-"+a+" where accno="+accno+" ");
		    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		Date d = new Date();
		    		String t = dateFormat.format(d);
		    		k = st.executeUpdate("insert into transaction values ("+accno+",'"+t+"',"+a+",'"+vendor+"') ;");
		    		if(k==0)
		    			return false;
		    		else
		    			return true;
		    	}
		    		
		    }
		    return false;
		} catch (Exception e) {
			System.out.println("login check error..");
			e.printStackTrace();
			return false;
		}
		finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (Exception e) {
					System.out.println("Error during Connection Closed 4");
				}
			}
		}
	}
	
	

}
