package main;
/**
 * 
 * @author calumthompson
 *
 * Producer for - any bakery item
 * 				  any sandwhich  
 */
public class KitchenStaff extends Thread{
	private Bakery b;
	private SandwichCounter s;
	
	KitchenStaff(SandwichCounter sc, Bakery ba){
		this.b = ba;
		this.s = sc;
	}
	
	public void run() {
		int i = 0;
		while(i < 10) {
			try {Thread.sleep(100);}
			catch(InterruptedException e) {}
			
			boolean bSuccess = b.put();
			boolean sSuccess = s.put();
			
			if(bSuccess == false) {
				b.setDone();
			}if(sSuccess == false) {
				s.setDone();
			}
		}
	}
}
