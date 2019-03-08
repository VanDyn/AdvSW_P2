/**

 * Orderlist class. Instantiates a LinkedList which is appended with orders.
 * Called by coffeeshop and only instantiated once. 
 * 
 * @author Alex Birks
 */


package main;

import java.util.*;

public class OrderList {
     
	private Map<String,Order> orderList;
	
	public OrderList() {
		orderList = new TreeMap<String,Order>();
	}
	
	//Add order to orderlist
	public void addOrder(String cusInfo, Order order) {
		orderList.put(cusInfo, order);
	}
	
	//Print all orders and return map of all orders placed
	public Map<String,Order> getOrders() {
		
		for(Order o: orderList.values()) {
			System.out.println(o.getItemList());
			
		}
		System.out.println(orderList.toString());
		return orderList;
	}
	
	//Return specific order using the order number
	public Order getOrder(String cusInfo) throws OrderDoesNotExistException {
		if(orderList.get(cusInfo)!=null) return orderList.get(cusInfo);
		else throw new OrderDoesNotExistException();
	}
	
	//returns time and name of every order
	public ArrayList<String> getCusInfo() {
		ArrayList<String> cusInfo = new ArrayList<String>();
		for(String o: orderList.keySet()) cusInfo.add(o);
		return cusInfo;
	}
	
	//Return number of orders
	public int getNumberOfOrders() {
		return orderList.size();
	}
}
