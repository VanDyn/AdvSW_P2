package Threads;

import SharedObjects.ServerControl;

public class CloseOpenTills extends Thread{
	
	private ServerControl control;
	private CafeQueue queue;
	
	public CloseOpenTills(ServerControl c, CafeQueue q){
		this.control = c;
		this.queue = q;
	}
	
	public void run() {
		while(true) {
			int s = queue.getQueueSize();
			
			if(s < 2) {
				control.turnon(0);
				control.turnoff(1);
				control.turnoff(2);
				control.turnoff(3);				
			}
			else if(s > 1 && s < 4) {
				control.turnon(0);
				control.turnon(1);
				control.turnoff(2);
				control.turnoff(3);
			}
			else if(s > 3 && s < 6) {
				control.turnon(0);
				control.turnon(1);
				control.turnon(2);
				control.turnoff(3);				
			}else if(s > 5) {
				control.turnon(0);
				control.turnon(1);
				control.turnon(2);
				control.turnon(3);			
			}
		}
	}


}
