package main;

/**
 * Exception class which throws an error if a product is selected before
 * a new customer has been created.
 * 
 * @author ShayneShaw
 *
 */

public class CreateNewCustomerException extends Exception {
	public CreateNewCustomerException() {
		System.out.println("Please create a new customer first!");
	}
}