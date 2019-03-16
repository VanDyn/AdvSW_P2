package main;

/**
 * Shared object for sandwichs
 * @author calumthompson
 *
 */
public class SandwichCounter {

	private int hamCheeseCount;
	private int eggCount;
	private int fiveCheeseCount;

	private boolean empty;
	private boolean done;

	SandwichCounter(){
		this.hamCheeseCount = 0;
		this.eggCount = 0;
		this.fiveCheeseCount = 0;

		empty = true;
		done = false;
	}

	public synchronized int get(String s) {

		switch(s) {
		case "Ham and Cheese":
			if(hamCheeseCount == 0) {return -1;}
			hamCheeseCount--;
			return 1;
		case "Egg":
			if(eggCount == 0) {return -1;}
			eggCount--;
			return 1;
		case "Five Cheese Toastie":
			if(fiveCheeseCount == 0) {return -1;}
			fiveCheeseCount--;
			return 1;
		default : return -1;
		}
	}

	// produces a bakery item 
	public synchronized boolean put() {
		if(hamCheeseCount != 5) { hamCheeseCount++;}else {empty = false;}


		if(eggCount != 4) {eggCount++;}else {empty = false;}


		if(fiveCheeseCount != 4) {fiveCheeseCount++;}else {empty = false;}
		return empty;

	}
	public void setDone() {done = true;}
	public boolean getDone() {return done;}
}
