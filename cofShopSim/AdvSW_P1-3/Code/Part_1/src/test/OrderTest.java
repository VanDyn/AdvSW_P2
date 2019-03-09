/**
 * Test Order class
 * 
 * @author Alex Birks
 */

package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import org.junit.Test;

import main.MenuItem;
import main.Order;

public class OrderTest {
	
	LinkedList<MenuItem> itemList = new LinkedList<MenuItem>(); 
	BigDecimal c1 = new BigDecimal(2.00);
	BigDecimal c2 = new BigDecimal(1.00);
	BigDecimal total = new BigDecimal(3.00);
	MenuItem cof = new MenuItem("COF001", c1, "Coffee with milk", "Coffee");
	MenuItem tea = new MenuItem("COF002", c2, "Tea without milk", "Tea");

	SimpleDateFormat ft = new SimpleDateFormat ("d.M.yyyy,hh:mm:ss");
	
	
	@Test
	public void getTimeStamp() {
		Date dNow = new Date();
		String time = ft.format(dNow);
		itemList.add(cof);
		itemList.add(tea);
		Order o = new Order(itemList,total);
		assertEquals(time, o.getTimeStamp());
	}
	
	@Test 
	public void getItemList() {
		itemList.add(cof);
		itemList.add(tea);
		Order o = new Order(itemList,total);
		assertSame(o.getItemList(), itemList);
	}
	
}