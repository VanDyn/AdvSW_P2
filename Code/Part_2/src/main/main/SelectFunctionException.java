package main;

/**
 * Exception class which throws an error if the confirm button is pressed before
 * a function such as Total or Cancel has been selected.
 * 
 * @author ShayneShaw
 *
 */

public class SelectFunctionException extends Exception {
	public SelectFunctionException() {
		System.out.println("Please select a funtion first! Total, Cancel or Gross");
	}
}
