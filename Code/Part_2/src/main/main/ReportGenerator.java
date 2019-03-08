package main;
import java.io.*;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;


public class ReportGenerator {

  DateFormat dateFormat;


  ReportGenerator(){
// Set format for report date here. Possibly unnecessary
    dateFormat = new SimpleDateFormat("HH-mm-dd-MM-yy");
  }

  public void generateReport(Map<String, Order> ol, Collection<MenuItem> menu) throws EmptyLinkedListException{


    // Filewriter for writing to txt file
    PrintStream ps;
    
    try {
      
      BigDecimal total = BigDecimal.ZERO;
      
      // Specification states report should be 'all items in a menu, quantity sold and total earnings
      // So menu access required. As class only implements single method this may be better in coffee shop.'


      // Will need to create collection containing all menu items

      ArrayList<ItemSaleTracker> items = new ArrayList<ItemSaleTracker>();

      Iterator<MenuItem> menuIterator = menu.iterator();
      
      while(menuIterator.hasNext()){
        items.add(new ItemSaleTracker(menuIterator.next()));
      }

     
      for(Order o: ol.values()){
        // check items against menu and keep running total of earnings
        for(MenuItem m: o.getItemList()){
        	boolean found = false;
        	
        	// Need to look through Pairs, find item corresponding to one in order
        	// then increment quantity
        	for (int i = 0; ( i < items.size() ) && !found ; i++) {
        		ItemSaleTracker itemST = items.get(i);
        		if (m.getID().equals( itemST.getItem().getID() )) {
        			items.get(i).incByOne();
        			found = true;
        		}
        	}
        	total = total.add(o.getOrderTotal());

        }
      }

      // Final section that writes to file
      // build filename in format log-HH-mm-dd-MM-yy.txt
      String dateString = dateFormat.format( new Date() );
      String fileName = String.format( "log-%s.txt", dateString );
      File logFile = new File(fileName);
      
      ps = new PrintStream( new FileOutputStream( logFile ) );
      
      // Title for file
      ps.println( String.format( "Log File for %s", dateString ) );
      
      ps.println("Item ID -- Item Description -- Quantity Ordered");
      
      Iterator<ItemSaleTracker> allItemPairs = items.listIterator();
      while (allItemPairs.hasNext()) {
    	  // Write item ID, description and quantity ordered to file
    	  ItemSaleTracker thisItemPair = allItemPairs.next();
    	  ps.println(String.format("%s %s %d", thisItemPair.getItem().getID(), 
    			  thisItemPair.getItem().getDescription(), thisItemPair.getQuantity()));
      }
      // List total income
      
      ps.println(String.format("Total Daily Earnings: %s", total.toString()));
      
      // close dataStream
      ps.close();
      
    } catch (IOException e){
    	System.out.println("Exception");
    }
  }

  // Basic Pair class as Java has no native Pair class
  public class ItemSaleTracker{
    MenuItem item;
    int quantity;

    public ItemSaleTracker(MenuItem item) {
      this.item = item;
      quantity = 0;
    }

    public void setItem(MenuItem item){
      this.item = item;
    }

    public void setQuantity(int quantity){
      this.quantity = quantity;
    }
    
    public void incByOne() {
    	quantity = quantity + 1;
    }

    public MenuItem getItem(){
      return item;
    }

    public int getQuantity(){
      return quantity;
    }

  }


}
