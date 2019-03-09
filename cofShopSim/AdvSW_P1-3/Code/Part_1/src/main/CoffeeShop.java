package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class CoffeeShop {

	public static OrderList orderList = new OrderList();
	public static Menu menu;

	LinkedList<MenuItem> ll;
	
	public CoffeeShop() throws NotOnMenuException, CreateNewCustomerException, EmptyLinkedListException{

		menu = new Menu("examplemenu.txt");
		ll = new LinkedList<MenuItem>(); 

		//MenuItem item;
		String[] data;

		try {

			BufferedReader buff = new BufferedReader(new FileReader("existingOrders"));
			Scanner scan = new Scanner(buff);
			data = new String[2];

			ArrayList<String> orderDetails = new ArrayList<String>();
			ArrayList<String> itemDetails = new ArrayList<String>();


			while(scan.hasNextLine()) {

				String line = scan.nextLine();

				data = line.split("/");

				orderDetails.add(data[0]);
				itemDetails.add(data[1]);

			}

			// split order details
			String[][] idTime = new String[orderDetails.size()][orderDetails.size()];
			data = new String[2];

			for(int i = 0; i < orderDetails.size(); i++) {
				String line = orderDetails.get(i);
				data = line.split(";");

				idTime[i][0] = data[0];
				idTime[i][1] = data[1];
			}
			
			
			// create item objects
			ArrayList<MenuItem> items = new ArrayList<MenuItem>();
			data = new String[2];

			for(int i = 0; i < itemDetails.size(); i++) {
				String line = itemDetails.get(i);
				data = line.split(";");

				items.add(menu.getItem(data[2]));
			}

			String prev = null;
			String curr = null;

			for(int i = 0; i< idTime.length; i++) {

				prev = curr;
				curr = idTime[i][1];
				

				if(curr.equals(prev) || prev == null) {
					ll.add(items.get(i));
				
				}else {
					Order order = new Order(prev,idTime[i-1][0],ll);
					orderList.addOrder(order);
					
					ll.clear();
					ll.add(items.get(i));
		

				}
				if(i == (idTime.length - 1)) {
					Order order = new Order(prev,idTime[i-1][0],ll);
					orderList.addOrder(order);
				}
			}

			scan.close();
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}

		CafeGUI test = new CafeGUI(menu);
		test.setSize(600, 800);
		test.setVisible(true);
		
	}

	public static void createOrder(LinkedList<MenuItem> items, BigDecimal total) {
		Order order = new Order(items,total);
		orderList.addOrder(order);
		System.out.println(orderList.getNumberOfOrders());
	}
	
	
	
	public static void generateReport() throws EmptyLinkedListException {
		
		ReportGenerator report = new ReportGenerator();
		report.generateReport(orderList.getOrders(),menu.getMenu());
		
	}
	
	public OrderList getOrderList() {
		return orderList;
	}
}






