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
	   
	   
	   CafeQueue queue = new CafeQueue(i);
	   Server server = new Server(queue);
	   
	   CafeGUI gui = new CafeGUI(queue, server);
	   CafeController cafeCon = new CafeController(gui, server, queue);
	   
	   // while (gui.getStart == true && gui.getStop == false)   // start the threads  NOT SURE WHERE TO USE THIS!
	   queue.start();
	   server.start();
	   
	   //Server s1 = new Server(queue); s1.start();
		
	}
}
