package SharedObjects;

public class ServerControl {
	private Boolean[] servers;

	public ServerControl(Boolean[] control)
	{
		this.servers = control;
	}
	public synchronized void turnoff(int serverNo)
	{
		this.servers[serverNo] = false;
	}
	
	public synchronized void turnon(int serverNo)
	{
		this.servers[serverNo] = true;
	}
	
	public synchronized Boolean get(int serverNo)
	{
		return this.servers[serverNo];
	}
}
