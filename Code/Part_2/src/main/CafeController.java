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
	private static String temp2;
	private static String temp3;
	private static String temp4;

	/**
	 * Takes an instance of CafeGUI, Server and CafeQueue
	 * 
	 * @param gui
	 *            - CafeGUI
	 * 
	 */

	CafeController(CafeGUI gui) {
		this.gui = gui;
		this.temp = "W";
		this.temp2 = "X";
		this.temp = "Y";
		this.temp2 = "Z";
	}

	public synchronized static void updateQueue() {

		gui.clearDisplay(gui.getQueueDisplay());

		ArrayList<String> arr = CafeQueue.getQueueMembers();

		for (String i : arr) {
			gui.printToDisplay(i, gui.getQueueDisplay());
		}

	}

	public synchronized static void updateServer(Server s, int thread) {
		if (thread == 1) {

			//System.out.println(s.getCustomerName() + temp); // REMEMBER TO
															// DELETE THIS!

			if (temp == "W" || temp != s.getCustomerName() ) {
				temp = s.getCustomerName();
				gui.clearDisplay(gui.getTillOneDisplay());
				gui.printToDisplay("Currently Serving : " + temp, gui.getTillOneDisplay());
				gui.printToDisplay("-------------------", gui.getTillOneDisplay());
			} else {
				gui.printToDisplay(s.getItem(), gui.getTillOneDisplay());
			}

		} else if (thread == 2) {

			//System.out.println(s.getCustomerName() + temp2); // REMEMBER TO
																// DELETE THIS!

			if (temp2 == "X" || temp2 != s.getCustomerName()) {
				temp2 = s.getCustomerName();
				gui.clearDisplay(gui.getTillTwoDisplay());
				gui.printToDisplay("Currently Serving : " + temp2, gui.getTillTwoDisplay());
				gui.printToDisplay("-------------------", gui.getTillTwoDisplay());
			} else {
				gui.printToDisplay(s.getItem(), gui.getTillTwoDisplay());
			}

		} else if (thread == 3) {

			//System.out.println(s.getCustomerName() + temp3); // REMEMBER TO
																// DELETE THIS!

			if (temp3 == "Y" || temp3 != s.getCustomerName()) {
				temp3 = s.getCustomerName();
				gui.clearDisplay(gui.getTillThreeDisplay());
				gui.printToDisplay("Currently Serving : " + temp3, gui.getTillThreeDisplay());
				gui.printToDisplay("-------------------", gui.getTillThreeDisplay());
			} else {
				gui.printToDisplay(s.getItem(), gui.getTillThreeDisplay());
			}

		} else if (thread == 4) {

			//System.out.println(s.getCustomerName() + temp4); // REMEMBER TO
																// DELETE THIS!

			if (temp4 == "Z" || temp4 != s.getCustomerName()) {
				temp4 = s.getCustomerName();
				gui.clearDisplay(gui.getTillFourDisplay());
				gui.printToDisplay("Currently Serving : " + temp4, gui.getTillFourDisplay());
				gui.printToDisplay("-------------------", gui.getTillFourDisplay());
			} else {
				gui.printToDisplay(s.getItem(), gui.getTillFourDisplay());
			}

		}

	}

}
