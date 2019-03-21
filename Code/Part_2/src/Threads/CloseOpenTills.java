package Threads;

import GUI.CafeGUI;
import RefactoredCode.Interface;
import SharedObjects.ServerControl;
import main.Log;

/**
 * A class to automatically control the number of servers required to meet the
 * demands of the CafeQueue
 * 
 * @author calumthompson
 *
 */
public class CloseOpenTills extends Thread {

	private ServerControl SControl;
	private ServerControl KControl;
	private ServerControl QControl;
	private CafeQueue queue;
	private CafeGUI gui;

	/**
	 * Takes an instance of ServerControl, CafeQueue and CafeGUI
	 * 
	 * @param c
	 * @param q2 
	 * @param k 
	 * @param q
	 * @param i
	 * @param g
	 */
	public CloseOpenTills(ServerControl c, ServerControl k, ServerControl q, CafeQueue queue, Interface i, CafeGUI g) {
		this.SControl = c;
		this.queue = queue;
		this.KControl = k;
		this.QControl = q;
		this.gui = g;
	}

	/**
	 * Threads run method which 
	 */
	public void run() {
		while (Interface.getSize() != 0 || queue.getQueueSize() != 0) {
			int s = queue.getQueueSize();

			if (gui.getEnabled() == true) {
				KControl.turnon(0);
				KControl.turnon(1);
				QControl.turnon(0);
				if (s < 2) {
					SControl.turnon(0);
					SControl.turnoff(1);
					SControl.turnoff(2);
					SControl.turnoff(3);
				} else if (s > 1 && s < 4) {
					SControl.turnon(0);
					SControl.turnon(1);
					SControl.turnoff(2);
					SControl.turnoff(3);
				} else if (s > 3 && s < 6) {
					SControl.turnon(0);
					SControl.turnon(1);
					SControl.turnon(2);
					SControl.turnoff(3);
				} else if (s > 5) {
					SControl.turnon(0);
					SControl.turnon(1);
					SControl.turnon(2);
					SControl.turnon(3);
				}
			} else if (gui.getEnabled() == false) {

				SControl.turnoff(0);
				SControl.turnoff(1);
				SControl.turnoff(2);
				SControl.turnoff(3);
				KControl.turnoff(0);
				KControl.turnoff(1);
				QControl.turnoff(0);
			}
		}
		makeLog();
	}
	/**
	 * This will create the log once the cafe has closed
	 * 
	 */
	private void makeLog() {
		Log.INSTANCE.logToFile();
		System.out.println("Log sent to file");
	}
}
