/**
 * Orderlist class. Instantiates a LinkedList which is appended with orders.
 * Called by coffeeshop and only instantiated once. 
 * 
 * @author Alex Birks
 */


package part1;

import java.util.*;

public class OrderList {
     
	private static Map<String,Order> orderList;
	private int orderNumber;
	private String apdOrdNum;
	
	public OrderList() {
		orderList = new TreeMap<String,Order>();
		orderNumber = 0;
	}
	
	//Add order to orderlist
	public void addOrder(Order order) {
	
		if(orderNumber < 10) apdOrdNum = "O00";
		else if (orderNumber < 100) apdOrdNum = "O0";
		else apdOrdNum = "O";
		apdOrdNum = apdOrdNum + orderNumber;
		orderList.put(apdOrdNum, order);
		orderNumber++;
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
//	public Order getOrder(String orderNumber) throws OrderDoesNotExistException {
//		if(orderList.get(orderNumber)!=null) {
//			
//			
//			
//			return orderList.get(orderNumber);
//		}
//		else throw new OrderDoesNotExistException();
//	}
	
	//Return specific order using the order number
		public static Order getOrder()  {
			
			 Map.Entry<String,Order> entry = orderList.entrySet().iterator().next();
			 String key = entry.getKey();
			 Order value = entry.getValue();
			 orderList.remove(key);
			
			return value;
		}
	
	//Return number of orders
	public int getNumberOfOrders() {
		return orderList.size();
	}
	
	public static int getSize() {
		return orderList.size();
	}
}
