package main;
/**
 * 
 * @author calumthompson
 *
 * Producer for - any bakery item
 * 				  any sandwhich  
 */
public class KitchenStaff extends Thread{
	
	private Requests requests;
	private KitchenCounter counter;
	

	
	KitchenStaff(Requests r, KitchenCounter k){
		this.requests = r;
		this.counter = k;
	}
	
	public void run() {
		
		while(true) {
			String item = requests.get();
			
			if(item.equals("-1")) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				counter.put(item);
				sendToLog("Kitchen Staff are making: " + item);
			}

		}
	}
	
	private static synchronized void sendToLog(String details) {
		Log l = Log.INSTANCE;
		l.log(details);
	}
}
