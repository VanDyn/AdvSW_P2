package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.Menu;
import main.MenuItem;
import main.NotOnMenuException;

/**
 * A JUnit class to test the methods of the Menu class
 * 
 * @author Shayne.Shaw
 *
 */
public class MenuTest {

	public Menu testMenu;
	
	@Before
	public void setup() {

		 testMenu =  new Menu("exampleMenu.txt");		 
		 
	}
	
	
	@Test
	public void testNewItemDetails() throws NotOnMenuException {
		
		String test = "Americano";
		String target = "Americano;2.60;Coffee;COF101";
		
		assertEquals(target, testMenu.newItemDetails(test));
		assertTrue("Returned Item Details", testMenu.newItemDetails(test).equals(target));
		
	}
	
	@Test
	public void testGetCategoryDetails() throws NotOnMenuException {
		int size = 3;
		String category = "Coffee";
		String output = "Espresso;2.10;Coffee;COF103";
		
		assertEquals(size, testMenu.getCategoryDetails(category).size());
		//assertEquals(output, testMenu.getCategoryDetails(category).get(1)); // list does not preserve order so sometimes test fails
	}
	
	@Test
	public void testGetCategoryMembers() throws NotOnMenuException {
		int size = 3;
		String category = "Coffee";
		
		assertEquals(size, testMenu.getCategoryMembers(category).size());
	}
	
	@Test
	public void testGetItemCost() throws NotOnMenuException {
		String tea = "Earl Grey";
		String costOfItem = "2.10";
		
		assertEquals(costOfItem, testMenu.getItemCost(tea).toString());		
	}
		
	@Test
	public void testGetMenuSize() {
		int size = 12;
		
		assertEquals(size, testMenu.getMenuSize());
		
	}
	
	@Test
	public void testGetItem() throws NotOnMenuException {
		
		String description = "Brownie";
		
		assertEquals("BAK403", testMenu.getItem(description).getID());
		assertEquals("1.35", testMenu.getItem(description).getCost().toString());
		assertEquals("Brownie", testMenu.getItem(description).getDescription());
		assertEquals("Bakery", testMenu.getItem(description).getCategory());
		
	}
	
	@Test
	public void testInMenu() throws NotOnMenuException {
		String category2 = "Tea";
		MenuItem earlGrey = testMenu.getCategoryMembers(category2).get(0);
		
		assertTrue(testMenu.inMenu(earlGrey));
	}
	
	@Test
	public void testItemDetails() throws NotOnMenuException {
		
		BigDecimal cost2 = new BigDecimal(1.35);
		MenuItem testItem2 = new MenuItem("BAK403",cost2,"Brownie","Bakery");
		String details = "Brownie;1.35;Bakery;BAK403";
		
		assertEquals(details.trim(), testMenu.itemDetails(testItem2).trim());
		
//		MenuItem b = testMenu.getItem("Brownie");
//		System.out.println(b.getDescription() + b.getCost() + b.getCategory() + b.getID());
//	
//		System.out.println(testMenu.itemDetails(testItem2));		
//      assertTrue("True", testMenu.itemDetails(testItem2).equals(details));
		
		// help figure this out		
	}
}


