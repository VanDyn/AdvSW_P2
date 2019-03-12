package main;

public class NotOnMenuException extends Exception {
	public NotOnMenuException(String var, String input) {
		System.out.println(input + " is not a valid " + var + " ");
	}
}
