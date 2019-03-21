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
 * "Model View, Model View, Model View Controller MVCï¿½s the paradigm for
 * factoring your code, into functional segments so your brain does not explode.
 * ...."
 * 
 * @author ShayneShaw
 *
 */

public class CafeController {

	private static CafeGUI gui;
	private static String temp;
	private static String temp2;
	private static String temp3;
	private static String temp4;
	private static String temp5;
	private static String temp6;

	// Formatting
	private static String format = "%1$10s %2$-20s";

	/**
	 * Takes an instance of CafeGUI and acts as the controller between the GUI
	 * and CafeQueue, Server, KitchenStaff
	 * 
	 * @param gui
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

	/**
	 * Method updates the JTextField queueDisplay with information from
	 * customers in the queue.
	 */
	public synchronized static void updateQueue() {

		gui.clearDisplay(gui.getQueueDisplay());

		ArrayList<String> arr = CafeQueue.getQueueMembers();
		ArrayList<String> arr2 = CafeQueue.getQueueOrders();

		for (int i = 0; i < arr.size(); i++) {
			String q = String.format(format, arr.get(i), arr2.get(i));
			gui.printToDisplay(q, gui.getQueueDisplay());
		}

	}

	/**
	 * Method takes an instance of the Server class as well as the int
	 * identifier for the thread associated with it. The information is used to
	 * update the relevant JTextField
	 * 
	 * @param s
	 * @param thread
	 */
	public synchronized static void updateServer(Server s, int thread) {
		if (thread == 1) {
			gui.clearDisplay(gui.getTillOneDisplay());
			temp = s.getCustomerName();
			gui.printToDisplay("Currently Serving : " + temp + " : £" + s.getTotal(), gui.getTillOneDisplay());
			gui.printToDisplay("-------------------", gui.getTillOneDisplay());
			for (String i : s.getArr()) {

				gui.printToDisplay(i, gui.getTillOneDisplay());
			}

		} else if (thread == 2) {

			temp2 = s.getCustomerName();
			gui.clearDisplay(gui.getTillTwoDisplay());
			gui.printToDisplay("Currently Serving : " + temp2 + " : £" + s.getTotal(), gui.getTillTwoDisplay());
			gui.printToDisplay("-------------------", gui.getTillTwoDisplay());

			for (String i : s.getArr()) {

				gui.printToDisplay(i, gui.getTillTwoDisplay());
			}

		} else if (thread == 3) {

			temp3 = s.getCustomerName();
			gui.clearDisplay(gui.getTillThreeDisplay());
			gui.printToDisplay("Currently Serving : " + temp3 + " : £" + s.getTotal(), gui.getTillThreeDisplay());
			gui.printToDisplay("-------------------", gui.getTillThreeDisplay());
			for (String i : s.getArr()) {

				gui.printToDisplay(i, gui.getTillThreeDisplay());
			}

		} else if (thread == 4) {

			temp4 = s.getCustomerName();
			gui.clearDisplay(gui.getTillFourDisplay());
			gui.printToDisplay("Currently Serving : " + temp4 + " : £" + s.getTotal(), gui.getTillFourDisplay());
			gui.printToDisplay("-------------------", gui.getTillFourDisplay());

			for (String i : s.getArr()) {

				gui.printToDisplay(i, gui.getTillFourDisplay());
			}

		}
	}

	/**
	 * Method takes an instance of the KitchenStaff class as well as the int
	 * identifier for the thread associated with it. This information is then
	 * used to update the relevant JTextField associated with it. (
	 * 
	 * @param k
	 * @param thread
	 */
	public synchronized static void updateKitchen(KitchenStaff k, int thread) {
		if (thread == 1) {
			if (temp5 == "A" || temp5 != k.getItem() && (k.getItem() != "-1")) {
				temp5 = k.getItem();
				gui.clearDisplay(gui.getStaffOneDisplay());
				gui.printToDisplay("Currently making : " + temp5, gui.getStaffOneDisplay());
				gui.printToDisplay("-------------------", gui.getStaffOneDisplay());
			} else {
				gui.printToDisplay(k.getItem(), gui.getStaffOneDisplay());
			}

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
