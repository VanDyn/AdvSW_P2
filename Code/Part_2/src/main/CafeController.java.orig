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

	/**
	 * Takes an instance of CafeGUI, Server and CafeQueue
	 * 
	 * @param gui
	 *            - CafeGUI
	 * @param server
	 *            - Server
	 * @param queue
	 *            - CafeQueue
	 */

	CafeController(CafeGUI gui) {
		this.gui = gui;
	}

	public synchronized static void updateQueue(CafeQueue q) {

		gui.clearQueueDisplay();

		ArrayList<String> arr = q.getQueueMembers();

		for (String i : arr) {
			gui.setQueueDisplay(i + "\n");
		}

	}

	public synchronized static void updateServer(Server s, int thread) {
		if (thread == 1) {
			gui.setTillOneDisplay(s.getItem() + "\n");
			
			// IMPROVE TILL DISPLAYS!!!
		}

	}

}
