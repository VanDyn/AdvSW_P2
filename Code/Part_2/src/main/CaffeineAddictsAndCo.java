package main;

import java.util.*;
import java.util.Map.Entry;

import GUI.CafeController;
import GUI.CafeGUI;
import RefactoredCode.CreateNewCustomerException;
import RefactoredCode.EmptyLinkedListException;
import RefactoredCode.Interface;
import RefactoredCode.NotOnMenuException;
import RefactoredCode.OrderList;
import SharedObjects.KitchenCounter;
import SharedObjects.Requests;
import SharedObjects.ServerControl;
import SharedObjects.SimTime;
import Threads.CafeQueue;
import Threads.KitchenStaff;
import Threads.Server;

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
	   
	   Requests requests = new Requests();
	   KitchenCounter counter = new KitchenCounter();
	   
	   KitchenStaff kitchen = new KitchenStaff(requests,counter,t);
	  // KitchenStaff kitchen2 = new KitchenStaff(requests,counter,t);
	  // KitchenStaff kitchen3 = new KitchenStaff(requests,counter,t);
	   
	   Server server = new Server(queue, t, c, 0,counter,requests);
	   Server server2 = new Server(queue, t, c, 1,counter,requests);
	   Server server3 = new Server(queue, t, c, 2,counter,requests);
	   Server server4 = new Server(queue, t, c, 3,counter,requests);
	   
	   CafeGUI gui = new CafeGUI(queue, server, server2, server3, server4, t);
	   gui.setSize(600, 800);
	   gui.setVisible(true);
		
	   CafeController cafeCon = new CafeController(gui);
	   
	   kitchen.start();
	   //kitchen2.start();
	  // kitchen3.start();
	   
	   queue.start();
	   
	   server.start();
	   server2.start();
	   server3.start();
	   server4.start();
	   
		
	}
}
