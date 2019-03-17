package main;

//Import all Java GUI classes
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * Class which will implement the GUI for the coffee shop simulation.
 * 
 * @author ShayneShaw
 * 
 */

public class CafeGUI extends JFrame implements ActionListener, Observer {

	// Buttons and fields for Panel 1
	private JButton start, stop;
	private JLabel welcome;

	// The main display, Panel 2
	private JTextArea queueDisplay;
	private JScrollPane scrollQueue;

	// Labels for the Panel 3
	private JLabel tillOne, tillTwo;

	// Text areas for panel 4
	private JTextArea tillOneDisplay, tillTwoDisplay;
	private JScrollPane scrollTillOne, scrollTillTwo;

	// Formatting
	// private String format = "%1$10s %2$-60s";
	// private String output;
	
	// Instances to be observed
	private CafeQueue q;
	private Server s;

	// Booleans to determine when to start or stop the threads.
	private boolean begin;
	private boolean end;
	
	/**
	 * CafeGUI constructor which takes both the CafeQueue and Server classes to observe.
	 * @param queue - an instance of CafeQueue class
	 * @param server - an instance of Server class
	 */
	public CafeGUI(CafeQueue queue, Server server) {
		
		// Instances to be observed
		this.q = queue;
		queue.registerObserver(this);

		this.s = server;
		server.registerObserver(this);

		// Set up window title and ensure program ends on close		
		setTitle("Caffeine Addicts & Co");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Create a container and layout
		Container content = getContentPane();
		content.setLayout(new GridLayout(4, 1));

		// Panel 1
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 2));
		panel1.setSize(600, 100);

		welcome = new JLabel("Caffeine Addicts & Co");
		welcome.setHorizontalAlignment(JLabel.CENTER);
		welcome.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		panel1.add(welcome);
		start = new JButton("Start");
		stop = new JButton("Stop");
		panel1.add(start);
		panel1.add(stop);
		
		content.add(panel1);

		// Panel 2
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 1));
		panel2.setSize(600, 500);

		queueDisplay = new JTextArea(15, 60);
		queueDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		queueDisplay.setLineWrap(true); // possibly unnecessary
		queueDisplay.setEditable(false);
		scrollQueue = new JScrollPane(queueDisplay);
		panel2.add(scrollQueue);

		content.add(panel2);

		// Panel 3
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 2));

		tillOne = new JLabel("Till 1");
		tillTwo = new JLabel("Till 2");
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

		tillOneDisplay = new JTextArea(7, 30);
		tillOneDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tillOneDisplay.setLineWrap(true);
		tillOneDisplay.setEditable(false);
		scrollTillOne = new JScrollPane(tillOneDisplay);
		panel4.add(scrollTillOne);

		tillTwoDisplay = new JTextArea(7, 30);
		tillTwoDisplay.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		tillTwoDisplay.setLineWrap(true);
		tillTwoDisplay.setEditable(false);
		scrollTillTwo = new JScrollPane(tillTwoDisplay);
		panel4.add(scrollTillTwo);

		content.add(panel4);

		// Add listeners to the buttons
		start.addActionListener(this);
		stop.addActionListener(this);

		// Pack and set visible
		pack();
		setVisible(true);

		// Booleans
		begin = false;
		end = false;

	}

	/**
	 * Determines the appropriate action for the GUI when any button is pressed.
	 * 
	 * @param ActionEvent
	 * 
	 */
	public void actionPerformed(ActionEvent e) {

		System.out.println(e.getActionCommand()); // REMEMBER TO DELETE THIS

		if (e.getSource() == start) {

			begin = true;
			end = false;

		} else if (e.getSource() == stop) {

			begin = false;
			end = true;
			makeLog();

		}

	}
	
	/** 
	 * This will create the log once the cafe has closed
	 * 
	 */
	private void makeLog() {
		Log.INSTANCE.logToFile();

	}

	/**
	 * Getter method which returns the status of "begin"
	 * @return boolean begin
	 */
	public boolean getStart() {
		return begin;
	}
	
	/**
	 * Getter method which returns the status of "end"
	 * @return boolean end
	 */
	public boolean getStop() {
		return end;
	}
	
	public JTextArea getQueueDisplay(){
		return queueDisplay;
	}
	
	public JTextArea getTillOneDisplay(){
		return tillOneDisplay;
	}
	
	public JTextArea getTillTwoDisplay(){
		return tillTwoDisplay;
	}
	
	/**
	 * Takes a string and a JTextArea as arguments and prints the string to the JTextArea
	 * on a new line
	 * @param s - String
	 * @param j - JTextArea
	 */
	public void printToDisplay(String s, JTextArea j){
		j.append("/n" + s);
	}
	
	

	/**
	 * This method will update the GUI displays when any change is observed in
	 * either the Server or CafeQueue classes.
	 * 
	 */
	public synchronized void update(Observable arg0, Object arg1) {

		// System.out.println(arg1.getClass().getSimpleName());
		
//		if (arg1.equals(q)) {
//			int size = q.getQueueSize();
//			queueDisplay.append(String.valueOf(size));
//		} else if (arg1.equals(s)) {
//			tillOneDisplay.append(s.getItem());
//		}

	}
	

}
