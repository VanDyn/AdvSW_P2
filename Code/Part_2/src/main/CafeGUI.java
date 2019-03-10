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
 * Class which will implement the GUI for the coffee shop.
 * 
 * @author ShayneShaw
 *
 */

public class CafeGUI extends JFrame implements ActionListener, Observer {

	// This is where the info will be drawn from
	//private Menu menu;
	//private ArrayList<MenuItem> products;

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


	// For formatting
	//private String format = "%1$10s %2$-60s";
	//private String output;

	// Linked list for order
	//private LinkedList<MenuItem> currentOrder;
	
	// private ObservableDemo weatherUpdate ;   //  IMPLEMENT OBSERVABLE CLASSSES, THIS IS JUST AN EXAMPLE

	

	public CafeGUI() {

		
		

		// Set up window title and ensure program ends on close
		// Create a container and layout
		setTitle("Caffeine Addicts & Co");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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

	}

	/**
	 * Determines the appropriate action for the GUI when any button is pressed.
	 * 
	 * @param ActionEvent
	 * 
	 */
	public void actionPerformed(ActionEvent e) {

		System.out.println(e.getActionCommand());

		if (e.getSource() == start) {

			startDemo();
			
		} else if (e.getSource() == stop) {

			stopDemo();

		}

	}

	private void startDemo() {

		// Start the program

	}

	private void stopDemo() {

		// Stop the program

	}
	
	private void updateQueueDisplay(){
		
		// update the queue display
		queueDisplay.append("Updated");
	}
	
	private void updateTillOne(){
		
		// update till 1 display
		tillOneDisplay.append("Update Till 1");
	}
	
	private void updateTillTwo(){
		
		// update till 2 display
		tillTwoDisplay.append("Update till 2");
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		 // IMPLEMENT OBSERVABLE METHODS, THIS IS JUST AN EXAMPLE
		
		// weatherUpdate = (ObservableDemo) observable;   
        // System.out.println("Weather Report Live. Its "+weatherUpdate.getWeather());
		
	}

}

class TestGUI {
	
	public static void main(String[] args){
		
		CafeGUI test = new CafeGUI();
		
		test.setSize(600, 600);
		test.setVisible(true);
		
		
	}
}
