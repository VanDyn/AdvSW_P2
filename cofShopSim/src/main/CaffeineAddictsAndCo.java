package main;
/**
 * A class to run the main method.  Please run this class!
 * 
 * @author All
 *
 */
public class CaffeineAddictsAndCo {
	
	public static OrderList ol;
	
	public static void main(String[] args) throws NotOnMenuException, CreateNewCustomerException, EmptyLinkedListException {
		
		String filename = "src/main/existingOrders";
		Interface i = new Interface(filename);
		ol = new OrderList();
		
		for(Order o: i.getOrderList()) ol.addOrder(o);
		
		
	}
}
