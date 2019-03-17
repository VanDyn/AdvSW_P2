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
 * "Model View, Model View, Model View Controller MVC�s the paradigm for
 * factoring your code, into functional segments so your brain does not explode.
 * ...."
 * 
 * @author ShayneShaw
 *
 */

public class CafeController {

	private static CafeGUI gui;
	
	/**
	 * Takes an instance of CafeGUI, Server and CafeQueue
	 * 
	 * @param gui - CafeGUI
	 * 
	 */
	
	CafeController(CafeGUI gui) {
		this.gui = gui;
	}

	
<<<<<<< HEAD
	public synchronized static void updateQueue() {

		Queue<Order> q = CafeQueue.getQueue();
		for (int i = 0; i < q.size(); i++) {

			gui.setQueueDisplay(q.remove().getID());
		}

	}
=======
	public synchronized static void updateQueue() {	
		ArrayList<String> arr = CafeQueue.getQueueMembers();
		
		for(String i : arr) {
			gui.setQueueDisplay(i + "\n");
		}
			
	}
	
	public synchronized static void updateServer(Server s, int thread) {	
		if(thread == 1) {
			gui.setTillOneDisplay(s.getItem());
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
>>>>>>> 3f4b0dc64a8b6e85a64018092c9047ce960aabe3

}
