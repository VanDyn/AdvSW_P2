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
	// private Server server;
	// private CafeQueue queue;
	private static String temp;

	/**
	 * Takes an instance of CafeGUI, Server and CafeQueue
	 * 
	 * @param gui - CafeGUI
	 * 
	 */

	CafeController(CafeGUI gui) {
		this.gui = gui;
		this.temp = "X";
	}

	public synchronized static void updateQueue() {

		gui.clearQueueDisplay();

		ArrayList<String> arr = CafeQueue.getQueueMembers();

		for (String i : arr) {
			gui.setQueueDisplay(i + "\n");
		}

	}

	public synchronized static void updateServer(Server s, int thread) {
		if (thread == 1) {

			System.out.println(s.getCustomerName() + temp);  // REMEMBER TO DELETE THIS!

			if (temp == "X" || temp != s.getCustomerName()) {
				temp = s.getCustomerName();
				gui.clearDisplay(gui.getTillOneDisplay());
				gui.printToDisplay("Currently Serving : " + temp, gui.getTillOneDisplay());
				gui.printToDisplay(s.getItem(), gui.getTillOneDisplay());
			} else {
				gui.printToDisplay(s.getItem(), gui.getTillOneDisplay());
			}

		}
		else if (thread == 2){
			
			System.out.println(s.getCustomerName() + temp + "TILL TWO");  // REMEMBER TO DELETE THIS!

			if (temp == "X" || temp != s.getCustomerName()) {
				temp = s.getCustomerName();
				gui.clearDisplay(gui.getTillTwoDisplay());
				gui.printToDisplay("Currently Serving : " + temp, gui.getTillTwoDisplay());
				gui.printToDisplay(s.getItem(), gui.getTillTwoDisplay());
			} else {
				gui.printToDisplay(s.getItem(), gui.getTillTwoDisplay());
			}
			
		}

	}

}
