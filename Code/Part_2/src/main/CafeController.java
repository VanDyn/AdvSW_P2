package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import javax.swing.JTextArea;

/**
 * Class to act as the controller for MVC design pattern
 * 
 * "Model View, Model View, Model View Controller
 *  MVC�s the paradigm for factoring your code,
 *  into functional segments so your brain does not explode.
 *  ...."
 *  
 * @author ShayneShaw
 *
 */

public class CafeController {
	
	private static CafeGUI gui;
//	private Server server;
//	private CafeQueue queue;
	
	/**
	 * Takes an instance of CafeGUI, Server and CafeQueue
	 * 
	 * @param gui - CafeGUI
	 * @param server - Server
	 * @param queue - CafeQueue
	 */
//	public CafeController(CafeGUI view, Server slave, CafeQueue line){
//		
//		this.gui = gui;
//		this.server = server;
//		this.queue = queue;
//		
//	}
	CafeController(CafeGUI gui){
		this.gui = gui;
	}
	
	// I think this method should be fully implemented here as opposed to in the GUI
	// However it's not printing anything atm for some reason.  I think there are too
	// many instances of the server and queue in multiple classes and I don't think it 
	// is looking at the correct one.
	
	
	
	
//	public synchronized void update(Observable arg0, Object arg1){
//		//gui.update(arg0, arg1); call the GUI method
//		
//		if (arg1.equals(queue)) {
//			int size = queue.getQueueSize();
//			gui.printToDisplay(String.valueOf(size) + "Controller", gui.getQueueDisplay());
//		} else if (arg1.equals(server)) {
//			gui.printToDisplay(server.getItem() + "Controller", gui.getTillOneDisplay());
//		}
//	}
	
	public synchronized static void updateQueue() {	
		ArrayList<String> arr = CafeQueue.getQueueMembers();
		
		for(String i : arr) {
			gui.setQueueDisplay(i + "\n");
		}
			
	}
	
	public synchronized static void updateServer(Server s, int thread) {	
		if(thread == 1) {
			gui.setTillOneDisplay(s.getItem() + "\n");
		}
			
	}
	
//	public String getItem(){
//		return server.getItem();
//	}
//	
//	public String getCustomerName(){
//		return server.getCustomerName();
//	}
//	
//	public Queue getQueue(){
//		return queue.getQueue();
//	}
//	
//	public void printToDisplay(String s, JTextArea j){
//		gui.printToDisplay(s, j);
//	}	

}

