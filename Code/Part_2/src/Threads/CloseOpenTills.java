package Threads;

import GUI.CafeGUI;
import SharedObjects.ServerControl;

/**
 * A class to automatically control the number of servers required to meet the
 * demands of the CafeQueue
 * 
 * @author Calum
 *
 */
public class CloseOpenTills extends Thread {

	private ServerControl control;
	private CafeQueue queue;
	private CafeGUI gui;

	/**
	 * Takes an instance of ServerControl, CafeQueue and CafeGUI
	 * 
	 * @param c
	 * @param q
	 * @param g
	 */
	public CloseOpenTills(ServerControl c, CafeQueue q, CafeGUI g) {
		this.control = c;
		this.queue = q;
		this.gui = g;
	}

	/**
	 * Method controls which Servers are active at any one time. The larger the
	 * queue, the more servers are used.
	 */
	public void run() {
		while (true) {
			int s = queue.getQueueSize();

			if (gui.getBegin() == true) {

				if (s < 2) {
					control.turnon(0);
					control.turnoff(1);
					control.turnoff(2);
					control.turnoff(3);
				} else if (s > 1 && s < 4) {
					control.turnon(0);
					control.turnon(1);
					control.turnoff(2);
					control.turnoff(3);
				} else if (s > 3 && s < 6) {
					control.turnon(0);
					control.turnon(1);
					control.turnon(2);
					control.turnoff(3);
				} else if (s > 5) {
					control.turnon(0);
					control.turnon(1);
					control.turnon(2);
					control.turnon(3);
				}
			} else if (gui.getEnd() == false) {

				control.turnoff(0);
				control.turnoff(1);
				control.turnoff(2);
				control.turnoff(3);
			}
		}
	}

}
