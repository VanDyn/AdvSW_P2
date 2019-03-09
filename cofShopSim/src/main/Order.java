package main;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Class to create and access the customer orders.
 * @author Jack
 *
 */
public class Order implements Comparable<Order>
{
	private String timeStamp;
	private LinkedList<MenuItem> itemList;
	private String ID;
	private BigDecimal total;
	
	
	public Order(LinkedList<MenuItem> Items, BigDecimal total) 
	{	
		this.itemList = Items;
		this.timeStamp = this.makeTimeStamp();
		this.total = total;
	}
	
	/**
	 * Constructor to be used when creating an order through the GUI
	 * @param Items LinkedList of menuItem objects
	 * Timestamp is set and total cost is calculated from the linkedList
	 * @throws EmptyLinkedListException 
	 */
	public Order(LinkedList<MenuItem> Items) throws EmptyLinkedListException //GUI constructor
	{	
		this.itemList = Items;
		this.timeStamp = this.makeTimeStamp();
		this.total = this.calcOrderTotal();
	}
	/**
	 * Parameters pre-existing and loaded from file
	 * @param custID
	 * @param tStamp
	 * @param link - linkedList of item objects
	 * @throws EmptyLinkedListException
	 */
	public Order(String custID, String tStamp, LinkedList<MenuItem> link) throws EmptyLinkedListException{ //File read constructor
		this.ID = custID;
		this.timeStamp = tStamp;
		this.itemList = link;
		this.total = this.calcOrderTotal();
	}
	/**
	 * 
	 * @return generated time stamp
	 */
	private String makeTimeStamp()
	{
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("d.M.yyyy,hh:mm:ss");
		String time = ft.format(dNow);
		return time;
	}
	public String getTimeStamp()
	{
		return this.timeStamp;
	}
	public LinkedList<MenuItem> getItemList()
	{
		return this.itemList;
	}
	public BigDecimal getOrderTotal()
	{
		return this.total;
	}
	public String getID()
	{
		return this.ID;
	}
	/**
	 * Calculate the total cost in the order by querying the LinkedList
	 * @return Total order cost
	 * @throws EmptyLinkedListException
	 */
	private BigDecimal calcOrderTotal() throws EmptyLinkedListException
	{
		BigDecimal orderTotal = new BigDecimal(0);
		int size = itemList.size();
		for(int i=0;i<size;i++)
			{
				
				orderTotal = orderTotal.add(this.itemList.get(i).getCost()); 
			}
		return orderTotal;
	}
	/**
	 * Compare order objects on their IDs
	 */
	public int compareTo(Order arg0) {
		int comp = this.ID.compareTo(arg0.ID);
		if(comp < 0) {
			return -1;
		}
		else if (comp > 0) {
			return 1;
		}
		return 0;
	}
	

}

