package main;


	
	public class TestThread extends Thread{
		
		private String identifier;
		
		public TestThread(String identifier) {
			this.identifier = identifier;
		}
		
		public void run() {
			for (int i = 0; i < 50; i++) {
				Log.INSTANCE.log(identifier + " " + i);
			}
		}
	
	public static void main(String[] args) {
		TestThread aThread = new TestThread("A Thread");
		TestThread bThread = new TestThread("B Thread");
		aThread.start();
		bThread.start();
		Log.INSTANCE.logToFile();
		
	}
	}
	

