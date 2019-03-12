package main;

import java.util.*;
import java.util.Map.Entry;

/**
 * A class to run the main method.  Please run this class!
 * 
 * @author All
 *
 */
public class CaffeineAddictsAndCo {
	
	public static OrderList ol;
	
	public static void main(String[] args) throws NotOnMenuException, CreateNewCustomerException, EmptyLinkedListException {
		
		String filename = "src/main/existingOrders";
		Interface i = new Interface(filename);
		ol = new OrderList();
		
		for(Entry<String, Order> o: i.getOrderList().entrySet()) ol.addOrder(o.getKey(), o.getValue());;
		//Retrieve customer info for every order
		System.out.println(ol.getCusInfo());
		//Retrieve just time of first order
		String o = ol.getCusInfo().get(0);
		System.out.println(o.substring(0, o.indexOf(";")));
		//Retrieve just name of first order
		System.out.println(o.substring(o.indexOf(";")+1));
		
	}
}
