package com.legend.lib;

import java.util.ArrayList;

public class Test  {
	public static void main(String[] args) throws Exception {
		Test t=new Test();
		t.ProductTest();
	}
	void ProductTest() throws Exception{
	
		helpFunctions help=new helpFunctions();
		User user=new User();
		user=help.getFilledUser("binni@gmail.com");
		OrderDetails od=new OrderDetails();
		String str=od.insertOrders(user, 509679);
		String[] response=str.split("\\$");
		System.out.println("*******");
		System.out.println(response[0]);
		System.out.println("*******");
		System.out.println(response[1]);
	//	String str=od.OrderSummary(user,"sale002");
	//	System.out.println(str);
		MoreHelpFunctions help1=new MoreHelpFunctions();
		System.out.println(help1.bank_Response("binn9"));
		//help1.UnsuccessfulTransaction("binn9");
		//help1.SuccessfulTransaction("sale001","nikh9");
		
		//help.crudProduct("Nikhitha","legend/images/bag.jpg","500","Bag","54","20.2","Nikhitha","Nikhitha");
	}
}
