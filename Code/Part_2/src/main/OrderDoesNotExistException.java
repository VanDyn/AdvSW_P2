package main;

/**
 * Exception class that throws an error and informs user
 * order does not exist. 
 * 
 * @author Alex Birks
 */

public class OrderDoesNotExistException extends Exception{

	public  OrderDoesNotExistException() {
		System.out.println("Order does not exist");
	}
}
