package GUI;

//Import all Java GUI classes
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import SharedObjects.SimTime;
import Threads.CafeQueue;
import Threads.KitchenStaff;
import Threads.Server;
import main.Log;

/**
 * Class which will implement the GUI for the coffee shop simulation.
 * 
 * @author ShayneShaw
 * 
 */

public class CafeGUI extends JFrame implements ActionListener, Observer {

	// Buttons and fields for Panel 1
	private JButton start, stop, faster, slower;
	private JLabel welcome;

	// The main display, Panel 2
	private JTextArea queueDisplay;
	private JScrollPane scrollQueue;

	// Labels for the Panel 3
	private JLabel tillOne, tillTwo;

	// Text areas for panel 4 & 5
	private JTextArea tillOneDisplay, tillTwoDisplay, tillThreeDisplay, tillFourDisplay;
	private JScrollPane scrollTillOne, scrollTillTwo, scrollTillThree, scrollTillFour;

	// Text areas for panel 6
	private JTextArea staffOneDisplay, staffTwoDisplay;
	private JScrollPane scrollStaffOne, scrollStaffTwo;

	// Instances to be observed
	private CafeQueue q;
	private CafeQueue pq;
	private Server s;
	private Server s2;
	private Server s3;
	private Server s4;
	private KitchenStaff k1;
	private KitchenStaff k2;

	// Boolean to determine when to start or stop the threads.
	private boolean enabled;

	// To Synch threads
	private SimTime time;

	/**
	 * CafeGUI constructor which takes the CafeQueue and multiple instances of
	 * Server and KitchenStaff classes to observe.
	 * 
	 * @param queue
	 * @param server
	 * @param server2
	 * @param server3
	 * @param server4
	 * @param k1
	 * @param k2
	 * @param t
	 */
	public CafeGUI(CafeQueue[] queues, Server server, Server server2, Server server3, Server server4, KitchenStaff k1,
			KitchenStaff k2, SimTime t) {

		// Instances to be observed
		this.q = queues[0];
		queues[0].registerObserver(this);
		
		if (queues.length > 1) {
			this.pq = queues[1];
			queues[1].registerObserver(this);
		}

		this.s = server;
		server.registerObserver(this);

		this.s2 = server2;
		server2.registerObserver(this);

		this.s3 = server3;
		server3.registerObserver(this);

		this.s4 = server4;
		server4.registerObserver(this);

		this.k1 = k1;
		k1.registerObserver(this);

		this.k2 = k2;
		k2.registerObserver(this);

		// Thread Synch
		this.time = t;

		// Set up window title and ensure program ends on close
		setTitle("Caffeine Addicts & Co");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Create a container and layout
		Container content = getContentPane();
		content.setLayout(new GridLayout(7, 1));

		// Panel 1
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 1));
		panel1.setPreferredSize(new Dimension(600, 100));

		welcome = new JLabel("Caffeine Addicts & Co");
		welcome.setHorizontalAlignment(JLabel.CENTER);
		welcome.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		panel1.add(welcome);

		// Panel 1B
		JPanel panel1B = new JPanel();
		panel1B.setLayout(new GridLayout(1, 4));

		start = new JButton("Resume");
		start.setEnabled(false);
		stop = new JButton("Stop");
		faster = new JButton("Speed up");
		slower = new JButton("Slow down");
		panel1B.add(start);
		panel1B.add(stop);
		panel1B.add(faster);
		panel1B.add(slower);

		content.add(panel1);
		content.add(panel1B);

		// Panel 2
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 1));
		panel2.setSize(600, 100);

		queueDisplay = new JTextArea(600, 200);
		queueDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		queueDisplay.setLineWrap(true); // possibly unnecessary
		queueDisplay.setEditable(false);
		DefaultCaret caret1 = (DefaultCaret) queueDisplay.getCaret();
		caret1.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollQueue = new JScrollPane(queueDisplay);
		panel2.add(scrollQueue);

		content.add(panel2);

		// Panel 3
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 2));

		tillOne = new JLabel("Till 1, Till 3 & Kitchen 1");
		tillTwo = new JLabel("Till 2, Till 4 & Kitchen 2");
		tillOne.setHorizontalAlignment(JLabel.CENTER);
		tillTwo.setHorizontalAlignment(JLabel.CENTER);
		tillOne.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tillTwo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		panel3.add(tillOne);
		panel3.add(tillTwo);

		content.add(panel3);

		// Panel 4
		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(1, 2));

		tillOneDisplay = new JTextArea(300, 200);
		tillOneDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tillOneDisplay.setLineWrap(true);
		tillOneDisplay.setEditable(false);
		DefaultCaret caret2 = (DefaultCaret) tillOneDisplay.getCaret();
		caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollTillOne = new JScrollPane(tillOneDisplay);
		panel4.add(scrollTillOne);

		tillTwoDisplay = new JTextArea(300, 200);
		tillTwoDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tillTwoDisplay.setLineWrap(true);
		tillTwoDisplay.setEditable(false);
		DefaultCaret caret3 = (DefaultCaret) tillTwoDisplay.getCaret();
		caret3.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollTillTwo = new JScrollPane(tillTwoDisplay);
		panel4.add(scrollTillTwo);

		content.add(panel4);

		// Panel 5
		JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayout(1, 2));

		tillThreeDisplay = new JTextArea(300, 200);
		tillThreeDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tillThreeDisplay.setLineWrap(true);
		tillThreeDisplay.setEditable(false);
		DefaultCaret caret4 = (DefaultCaret) tillThreeDisplay.getCaret();
		caret4.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollTillThree = new JScrollPane(tillThreeDisplay);
		panel5.add(scrollTillThree);

		tillFourDisplay = new JTextArea(300, 200);
		tillFourDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tillFourDisplay.setLineWrap(true);
		tillFourDisplay.setEditable(false);
		DefaultCaret caret5 = (DefaultCaret) tillFourDisplay.getCaret();
		caret5.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollTillFour = new JScrollPane(tillFourDisplay);
		panel5.add(scrollTillFour);

		content.add(panel5);

		// Panel 6
		JPanel panel6 = new JPanel();
		panel6.setLayout(new GridLayout(1, 2));

		staffOneDisplay = new JTextArea(300, 200);
		staffOneDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		staffOneDisplay.setLineWrap(true);
		staffOneDisplay.setEditable(false);
		DefaultCaret caret6 = (DefaultCaret) staffOneDisplay.getCaret();
		caret6.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollStaffOne = new JScrollPane(staffOneDisplay);
		panel6.add(scrollStaffOne);

		staffTwoDisplay = new JTextArea(300, 200);
		staffTwoDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		staffTwoDisplay.setLineWrap(true);
		staffTwoDisplay.setEditable(false);
		DefaultCaret caret7 = (DefaultCaret) staffTwoDisplay.getCaret();
		caret7.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollStaffTwo = new JScrollPane(staffTwoDisplay);
		panel6.add(scrollStaffTwo);

		content.add(panel6);

		// Add listeners to the buttons
		start.addActionListener(this);
		stop.addActionListener(this);
		faster.addActionListener(this);
		slower.addActionListener(this);

		// Pack and set visible
		pack();
		setVisible(true);

		// Booleans
		enabled = true;

	}

	/**
	 * Determines the appropriate action for the GUI when any button is pressed.
	 * 
	 * @param ActionEvent
	 * 
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == start) {

			enabled = true;
			start.setEnabled(false);
			stop.setEnabled(true);
		} else if (e.getSource() == stop) {

			enabled = false;
			stop.setEnabled(false);
			start.setEnabled(true);
			//makeLog();

		}else if(e.getSource() == faster) {
			
			if(time.get() >= 500) {time.put(time.get()-500);}  // Limit 500 -> 3000ms delay
			System.out.println("Speed up. Delay time: " + (time.get()));
			
		}else if(e.getSource() == slower){
			if(time.get() < 2500) {time.put(time.get() +500);}
			System.out.println("Slow down. Delay time: " + (time.get()));

		}

	}

	/**
	 * Getter method which returns a boolean
	 * 
	 * @return begin
	 */
	public boolean getEnabled() {
		return enabled;
	}

	/**
	 * Returns the JTextArea
	 * 
	 * @return queueDisplay
	 */
	public JTextArea getQueueDisplay() {
		return queueDisplay;
	}

	/**
	 * Returns the JTextArea
	 * 
	 * @return tillOneDisplay
	 */
	public JTextArea getTillOneDisplay() {
		return tillOneDisplay;
	}

	/**
	 * Returns the JTextArea
	 * 
	 * @return tillTwoDisplay
	 */
	public JTextArea getTillTwoDisplay() {
		return tillTwoDisplay;
	}

	/**
	 * Returns the JTextArea
	 * 
	 * @return tillThreeDisplay
	 */
	public JTextArea getTillThreeDisplay() {
		return tillThreeDisplay;
	}

	/**
	 * Returns the JTextArea
	 * 
	 * @return tillFourDisplay
	 */
	public JTextArea getTillFourDisplay() {
		return tillFourDisplay;
	}

	/**
	 * Returns the JTextArea
	 * 
	 * @return staffOneDisplay
	 */
	public JTextArea getStaffOneDisplay() {
		return staffOneDisplay;
	}

	/**
	 * Returns the JTextArea
	 * 
	 * @return staffTwoDisplay
	 */
	public JTextArea getStaffTwoDisplay() {
		return staffTwoDisplay;
	}

	/**
	 * Takes a string and a JTextArea as arguments and prints the string to the
	 * JTextArea on a new line
	 * 
	 * @param s
	 * @param j
	 * 
	 */
	public void printToDisplay(String s, JTextArea j) {
		j.append("\n" + s);
	}

	/**
	 * Clears all text from the specified JTextArea
	 * 
	 * @param JTextArea
	 */
	public void clearDisplay(JTextArea j) {
		j.setText("");
	}

	/**
	 * This method will update the GUI displays when any change is observed in
	 * either the Server, CafeQueue or KitchenStaff classes.
	 * 
	 */
	public synchronized void update(Observable arg0, Object arg1) {

		if (arg1 instanceof CafeQueue) {
			CafeController.updateQueue((CafeQueue) arg1);
		} else if (arg1 instanceof Server) {
			if (((Server) arg1).getServerNo() == 0) {
				CafeController.updateServer(s, 1);
			} else if (((Server) arg1).getServerNo() == 1) {
				CafeController.updateServer(s2, 2);
			} else if (((Server) arg1).getServerNo() == 2) {
				CafeController.updateServer(s3, 3);
			} else if (((Server) arg1).getServerNo() == 3) {
				CafeController.updateServer(s4, 4);
			}
		} else if (arg1 instanceof KitchenStaff) {
			if (((KitchenStaff) arg1).getID() == 1) {
				CafeController.updateKitchen(k1, 1);
			} else if (((KitchenStaff) arg1).getID() == 2) {
				CafeController.updateKitchen(k2, 2);
			}
		}
	}

}
