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
	   
	   SimTime t = new SimTime();
	   CafeQueue queue = new CafeQueue(i,t);
	   Boolean[] controlList = {true, true, true, true}; 
	   ServerControl c = new ServerControl(controlList);
	   Server server = new Server(queue, t, c, 0);
	   Server server2 = new Server(queue, t, c, 1);
	   Server server3 = new Server(queue, t, c, 2);
	   Server server4 = new Server(queue, t, c, 3);
	   
	   CafeGUI gui = new CafeGUI(queue, server, server2, server3, server4, t);
	   CafeController cafeCon = new CafeController(gui);
	   
	   // while (gui.getStart == true && gui.getStop == false)   // start the threads  NOT SURE WHERE TO USE THIS!
	   queue.start();
	   server.start();
	   server2.start();
	   server3.start();
	   server4.start();
	   
	   //Server s1 = new Server(queue); s1.start();
		
	}
}
