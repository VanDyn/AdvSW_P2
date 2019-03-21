package SharedObjects;

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
