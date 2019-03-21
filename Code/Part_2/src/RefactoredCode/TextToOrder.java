/**
*
* @author Alex Birks
* 
* Version: 1.0 , 04/03/19
* 
* Description:
* This class is an intermediary class between the main method and orderlist. 
* 
**/
package RefactoredCode;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class TextToOrder {

	private static Menu menu;
	//private static Map<String,Order> orderList;
	public static ArrayList<Order> orderList;
	
	public TextToOrder(String filename) {
		menu = new Menu("exampleMenu.txt");
		//orderList = new TreeMap<String,Order>();
		orderList = new ArrayList<Order>();
		
		try {
			getPrevOrders(filename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("File not found @ Interface class line 27");
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param filename
	 * @throws NotOnMenuException
	 * @author Alex Birks
	 * 
	 * Description:
	 * The method reads a text file of preexisting orders and returns each order
	 * as a linked list of menu items
	 */
	private void getPrevOrders(String filename) {
	
		try(InputStream is = getClass().getResourceAsStream(filename);) {
			InputStreamReader isr = new InputStreamReader(is);
		    BufferedReader prevOrders = new BufferedReader(isr);
			String str;
			
			while((str = prevOrders.readLine())!= null) {
				LinkedList<MenuItem> order = new LinkedList<MenuItem>();
				BigDecimal orderTotal = new BigDecimal("0");
				
				String[] orderLine = str.split("/");
				
				//Needed customer info. Is it needed? If so, refactor orderlist
				String name = orderLine[0];
				//String[] name = cusInfo.split(";");
//				String time = cusInfo.substring(0, cusInfo.indexOf(";"));
//				String name = cusInfo.substring(cusInfo.indexOf(":"));
				//Needed order info (Only need item descriptions) 
				String ordInfo = orderLine[1];
				String[] o = ordInfo.split(";");
				ordInfo = o[2];
				o = ordInfo.split(",");
				for(String s: o) {
					s=s.substring(0,s.indexOf(":"));
					MenuItem mi = menu.getItem(s);
					orderTotal = orderTotal.add(menu.getItemCost(s));
					
					order.add(mi);
				}
				
				//Create an order and append to orderList
				makeOrder(name,order,orderTotal);
				
			}		
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found @ Interface class line 46");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("file found but error reading ");
			e.printStackTrace();
		} catch (NotOnMenuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param mi - Linked list of all the items in an order
	 * @param total - total cost of the order
	 * 
	 */
	private void makeOrder(String cusInfo, LinkedList<MenuItem> mi, BigDecimal total) {
		//Order constructor with calc total?
		Order o = new Order(mi,total);
		o.setId(cusInfo);
		orderList.add(o);
	}
	/**
	 * 
	 * @return a list of orders 
	 */
//	public Map<String,Order> getOrderList(){
//		return orderList;
//	}
	
	public static int getSize() {
		return orderList.size();
	}
	
	public static Order getOrder() {
		Order o = orderList.get(0);
		orderList.remove(0);
		return o;
		
	}

}
