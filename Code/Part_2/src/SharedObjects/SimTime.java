package SharedObjects;
/**
 * A class to control the speed of the program. This is to be used to change the thread delays
 * Producer Design Pattern
 * @author Jack
 *
 */
public class SimTime {
	private int timeLength = 0;
	
	public SimTime(){this.timeLength = 1000;}
	
	public synchronized int get() 
	{
		return timeLength;
	}
	public synchronized void put(int length) 
	{
		this.timeLength = length;
	}
		
}
