package test;

import static org.junit.Assert.*;

import org.junit.Before;

import main.OrderList;
import main.EmptyLinkedListException;
import main.MenuItem;
import main.NotOnMenuException;
import main.Order;
import main.OrderDoesNotExistException;

import org.junit.Test;

import main.CoffeeShop;
import main.CreateNewCustomerException;

public class CoffeeShopTest {
	
	public CoffeeShop cShop;
	
	@Before
	public void readOrders() throws NotOnMenuException, CreateNewCustomerException, EmptyLinkedListException {
		cShop = new CoffeeShop();
		
	}
	@Test
	public void correctOrders() throws OrderDoesNotExistException {
		
		Order o1 = cShop.getOrderList().getOrder("O001");
		
		//System.out.println(cShop.getOrderList().getOrder("O001"));
		System.out.println("Number of items in order1 " + o1.getItemList().size());
		System.out.println("First item ID " + o1.getItemList().get(0).getID());
		
		assertTrue("Correct Order", o1.getItemList().get(0).getID().equals("COF103"));
		
	}

	@Test
	public void numberOfExistingOrders() {
		System.out.println(cShop.getOrderList().getNumberOfOrders());
		
		assertTrue("Correct number of orders in list", cShop.getOrderList().getNumberOfOrders() == 2);
		
		
	}


}
