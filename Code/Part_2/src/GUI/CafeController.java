package GUI;

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

import Threads.CafeQueue;
import Threads.KitchenStaff;
import Threads.Server;

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
	private static String temp2;
	private static String temp3;
	private static String temp4;
	private static String temp5;
	private static String temp6;

	// Formatting
	private static String format = "%1$10s %2$-20s";

	/**
	 * Takes an instance of CafeGUI, Server and CafeQueue
	 * 
	 * @param gui
	 *            - CafeGUI
	 * 
	 */

	public CafeController(CafeGUI gui) {
		this.gui = gui;
		this.temp = "W";
		this.temp2 = "X";
		this.temp3 = "Y";
		this.temp4 = "Z";
		this.temp5 = "A";
		this.temp6 = "B";

	}

	public synchronized static void updateQueue() {

		gui.clearDisplay(gui.getQueueDisplay());

		ArrayList<String> arr = CafeQueue.getQueueMembers();
		ArrayList<String> arr2 = CafeQueue.getQueueOrders();

		for (int i = 0; i < arr.size(); i++) {
			String q = String.format(format, arr.get(i), arr2.get(i));
			gui.printToDisplay(q, gui.getQueueDisplay());
		}

	}

	public synchronized static void updateServer(Server s, int thread) {
		if (thread == 1) {
			gui.clearDisplay(gui.getTillOneDisplay());
			temp = s.getCustomerName();
			gui.printToDisplay("Currently Serving : " + temp + " : �" + s.getTotal(), gui.getTillOneDisplay());
			gui.printToDisplay("-------------------", gui.getTillOneDisplay());
			for(String i : s.getArr()) {
				
				gui.printToDisplay(i, gui.getTillOneDisplay());
			}

//			if (temp == "W" || temp != s.getCustomerName()) {
//				temp = s.getCustomerName();
//				gui.clearDisplay(gui.getTillOneDisplay());
//				gui.printToDisplay("Currently Serving : " + temp + " : �" + s.getTotal(), gui.getTillOneDisplay());
//				gui.printToDisplay("-------------------", gui.getTillOneDisplay());
//			} else {
//				gui.printToDisplay(s.getItem(), gui.getTillOneDisplay());
//			}

		} else if (thread == 2) {

//			if (temp2 == "X" || temp2 != s.getCustomerName()) {
				temp2 = s.getCustomerName();
				gui.clearDisplay(gui.getTillTwoDisplay());
				gui.printToDisplay("Currently Serving : " + temp2 + " : �" + s.getTotal(), gui.getTillTwoDisplay());
				gui.printToDisplay("-------------------", gui.getTillTwoDisplay());
				
				for(String i : s.getArr()) {
					
					gui.printToDisplay(i, gui.getTillTwoDisplay());
				}
//			} else {
//				gui.printToDisplay(s.getItem(), gui.getTillTwoDisplay());
//			}

		} else if (thread == 3) {

//			// System.out.println(s.getCustomerName() + temp3); // REMEMBER TO
//			// DELETE THIS!
//
//			if (temp3 == "Y" || temp3 != s.getCustomerName()) {
				temp3 = s.getCustomerName();
				gui.clearDisplay(gui.getTillThreeDisplay());
				gui.printToDisplay("Currently Serving : " + temp3 + " : �" + s.getTotal(), gui.getTillThreeDisplay());
				gui.printToDisplay("-------------------", gui.getTillThreeDisplay());
				for(String i : s.getArr()) {
					
					gui.printToDisplay(i, gui.getTillThreeDisplay());
				}
//			} else {
//				gui.printToDisplay(s.getItem(), gui.getTillThreeDisplay());
//			}

		} else if (thread == 4) {

			// System.out.println(s.getCustomerName() + temp4); // REMEMBER TO
			// DELETE THIS!

//			if (temp4 == "Z" || temp4 != s.getCustomerName()) {
				temp4 = s.getCustomerName();
				gui.clearDisplay(gui.getTillFourDisplay());
				gui.printToDisplay("Currently Serving : " + temp4 + " : �" + s.getTotal(), gui.getTillFourDisplay());
				gui.printToDisplay("-------------------", gui.getTillFourDisplay());
				
				for(String i : s.getArr()) {
					
					gui.printToDisplay(i, gui.getTillFourDisplay());
				}
//			} else {
//				gui.printToDisplay(s.getItem(), gui.getTillFourDisplay());
//			}

		}
	}
	public synchronized static void updateKitchen(KitchenStaff k, int thread){
		if (thread == 1) {
			if (temp6 == "A" || temp != k.getItem() && (k.getItem() != "-1")) {
				temp6 = k.getItem();
				gui.clearDisplay(gui.getStaffOneDisplay());
				gui.printToDisplay("Currently making : " + temp6, gui.getStaffOneDisplay());
				gui.printToDisplay("-------------------", gui.getStaffOneDisplay());
			} else {
				gui.printToDisplay(k.getItem(), gui.getStaffOneDisplay());
			}

//			if (temp5 == "A" || temp != k.getItem()) {
//				gui.clearDisplay(gui.getStaffOneDisplay());
//			//	gui.printToDisplay(s, j);
//			} else {
//				gui.printToDisplay(k.getItem(), gui.getStaffOneDisplay());
//			}

		} else if (thread == 2) {


			if (temp6 == "B" || temp2 != k.getItem() && (k.getItem() != "-1")) {
				temp6 = k.getItem();
				gui.clearDisplay(gui.getStaffTwoDisplay());
				gui.printToDisplay("Currently making : " + temp6, gui.getStaffTwoDisplay());
				gui.printToDisplay("-------------------", gui.getStaffTwoDisplay());
			} else {
				gui.printToDisplay(k.getItem(), gui.getStaffTwoDisplay());
			}

		}
		
	}
}
