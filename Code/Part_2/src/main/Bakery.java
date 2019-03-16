package main;

/**
 * Shared Object - Stores the available baked goods and sandwiches
 * @author calumthompson
 *
 */
public class Bakery {


	private int cookieCount;
	private int muffinCount;
	private int brownieCount;

	private boolean empty;
	private boolean done;

	Bakery(){
		this.cookieCount = 0;
		this.muffinCount = 0;
		this.brownieCount = 0;

		empty = true;
		done = false;
	}

	public synchronized int get(String s) {

		switch(s) {
		case "Chocolate Cookie":
			if(cookieCount == 0) {return -1;}
			cookieCount--;
			return 1;
		case "Blueberry Muffin":
			if(muffinCount == 0) {return -1;}
			muffinCount--;
			return 1;
		case "Brownie":
			if(brownieCount == 0) {return -1;}
			brownieCount--;
			return 1;
		default : return -1;
		}
	}

	// produces a bakery item 
	public synchronized boolean put() {

		if(cookieCount != 5) { cookieCount++;}else {empty = false;}
			
		
		if(muffinCount != 8) {muffinCount++;}else {empty = false;}


		if(brownieCount != 8) {brownieCount++;}else {empty = false;}
		return empty;
	}

public void setDone() {done = true;}
public boolean getDone() {return done;}
}
