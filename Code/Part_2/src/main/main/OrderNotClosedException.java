package main;

/**
 * Exception class which throws an error if the New Customer button is pressed a second time before
 * the current order is completed.
 * 
 * @author ShayneShaw
 *
 */

public class OrderNotClosedException extends Exception{
	public OrderNotClosedException() {
		System.out.println("Please close the current order first!");
	}
}
