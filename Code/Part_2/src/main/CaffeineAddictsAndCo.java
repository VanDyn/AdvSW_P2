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
import Threads.CloseOpenTills;
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
	   
	   Server server = new Server(queue, t, c, 0,counter,requests);
	   Server server2 = new Server(queue, t, c, 1,counter,requests);
	   Server server3 = new Server(queue, t, c, 2,counter,requests);
	   Server server4 = new Server(queue, t, c, 3,counter,requests);
	   
	   KitchenStaff k1 = new KitchenStaff(requests,counter,t,1);
	   KitchenStaff k2 = new KitchenStaff(requests,counter,t,2);
	   
	   CafeGUI gui = new CafeGUI(queue, server, server2, server3, server4, k1, k2, t);
	   gui.setSize(800, 1000);
	   gui.setVisible(true);
		
	   CafeController cafeCon = new CafeController(gui);
	   
	   CloseOpenTills cOTill = new CloseOpenTills(c, queue, gui);
	 
	   queue.start();
	   cOTill.start();
	   
	   server.start();
	   server2.start();
	   server3.start();
	   server4.start();
	   
	   k1.start();
	   k2.start();
	   
		
	}
}
