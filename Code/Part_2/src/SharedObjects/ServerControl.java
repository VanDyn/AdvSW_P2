package SharedObjects;
/**
 * A class to control if a server is running. Boolean array is queried by numerical ID
 * Producer Design Pattern
 * @author Jack
 *
 */
public class ServerControl {
	private Boolean[] servers;

	public ServerControl(Boolean[] control) //@param - Boolean array of length for 4
	{
		this.servers = control;
	}
	public synchronized void turnoff(int serverNo) //deactivate server
	{
		this.servers[serverNo] = false;
	}
	
	public synchronized void turnon(int serverNo) //activate server
	{
		this.servers[serverNo] = true;
	}
	
	public synchronized Boolean get(int serverNo) //return server state
	{
		return this.servers[serverNo];
	}
}
 