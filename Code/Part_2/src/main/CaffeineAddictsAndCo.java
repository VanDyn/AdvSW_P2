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
	   CafeGUI gui = new CafeGUI();
	   
	   Thread queue = new Thread(new CafeQueue(i));
	   queue.start();
	   Thread s1 = new Thread(new Server());
	   s1.start();
		
	
		
	}
}
