package main;

/**
 * Exception class which throws an error if a function such as Total or Cancel is selected before
 * a product has been added to an order.
 * 
 * @author ShayneShaw
 *
 */

public class SelectProductException extends Exception{
	public SelectProductException() {
		System.out.println("Please select a product first!");
	}
}
