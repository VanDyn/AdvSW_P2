package main;

/**
 * Exception class which throws an error if a Gross is selected before
 * any orders have been placed.
 * 
 * @author ShayneShaw
 *
 */

public class NoOrdersPlacedException extends Exception {
	public NoOrdersPlacedException() {
		System.out.println("No orders have been placed today!");
	}
}