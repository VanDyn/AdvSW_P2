package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Class to create and access the coffee shops menu.
 * 
 * @author calumthompson
 *
 */
public class Menu {

	/**
	 * Would TreeMap be a better choice? Allow sorting by item ID code. Will
	 * need custom comparator class.
	 * 
	 * EX. Unique ID: COF101
	 * 
	 * COF - Coffee TEA - Tea JUI - Juice SND - Sandwich BAK - Bakery
	 * 
	 */
	private HashSet<MenuItem> menu;

	/**
	 * Constructor reads in the menu .txt file and stores it in the [Selected
	 * data structure]
	 * 
	 * @param txtFile
	 *            name of the text file used to read in the menu
	 */
	public Menu(String txtFile) {
		this.menu = new HashSet<MenuItem>();
		try {

			BufferedReader buff = new BufferedReader(new FileReader(txtFile));
			Scanner scan = new Scanner(buff);
			String[] data = new String[4];

			while (scan.hasNextLine()) {

				String line = scan.nextLine();
				data = line.split(";");

				String ID = data[0];
				
				if(ID.length() == 6) {
					double temp = Double.parseDouble(data[1]);
					BigDecimal cost = new BigDecimal(temp);
					cost = cost.setScale(2,BigDecimal.ROUND_DOWN);
					String description = data[2];
					String category = data[3];
					
					MenuItem item = new MenuItem(ID, cost, description, category);
					this.menu.add(item);
				}
				
				
				

			}

			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	/**
	 * Returns all details needed to instantiate a new item object. Search menu
	 * for item.
	 * 
	 * @param name
	 *            accepts the item identifier. Preferably the item ID code
	 * @return returns [data structure or string with delimiter] with all item
	 *         details
	 */
	public String newItemDetails(String description) throws NotOnMenuException {

		String items = new String();
		Iterator<MenuItem> itr = this.menu.iterator();
		Boolean valid = false;
		
		// loop through each member of menu set.
		while (itr.hasNext()) {
			MenuItem item = itr.next();

			if (item.getDescription().equals(description)) {
				items = itemDetails(item);
				valid = true;
			}

		}
		if(valid) {return items;}
		else {throw new NotOnMenuException("Description",description);}		
	}

	/**
	 * Returns a list containing the details of every item in that category.
	 * 
	 * -->[Description;Price;Category;ID]<--
	 * 
	 * Search menu for members of a specified category.
	 * 
	 * @param category
	 *            category to return details of
	 * @return
	 */
	public ArrayList<String> getCategoryDetails(String category) throws NotOnMenuException {

		ArrayList<String> itemDeets = new ArrayList<String>();
		ArrayList<MenuItem> categoryItems = getCategoryMembers(category);
		
		// loop through each member of menu set.

		for (MenuItem item : categoryItems) {
			itemDeets.add(itemDetails(item));
		}
		if(itemDeets.size() == 0) throw new NotOnMenuException("Category", category);
		
		return itemDeets;
	}

	/**
	 * Returns a list containing the details of every item in that category.
	 * 
	 * Search menu for items of a specified category.
	 * 
	 * @param category
	 *            Specify which category to retrieve members from
	 * @return ArrayList of item objects
	 */
	public ArrayList<MenuItem> getCategoryMembers(String category) throws NotOnMenuException{

		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		Iterator<MenuItem> itr = this.menu.iterator();
		Boolean valid = false;
		
		// loop through each member of menu set.
		while (itr.hasNext()) {
			MenuItem item = itr.next();

			if (item.getCategory().equals(category)) {
				items.add(item);
				valid = true;
			}
		}
		if(valid) {return items;}
		else {throw new NotOnMenuException("Category", category);}

		
	}

	/**
	 * Check whether and item order is valid. Used for checking the initial
	 * order text file. This can be changed to only accept specific information
	 * 
	 * @param order
	 *            item details
	 * @return true/false
	 */
	public boolean inMenu(MenuItem item) throws NotOnMenuException{

		// MenuItem tempItem = new MenuItem(description,price,category,ID);
		boolean valid = false;

		if (menu.contains(item)) {
			valid = true;
		}else {throw new NotOnMenuException("MenuItem", item.getDescription());}
		
		return valid;
	}

	/**
	 * Return the cost of an item. Searches the menu by the items description
	 * 
	 * @param description
	 *            items description
	 * @return cost specified items cost
	 */
	public BigDecimal getItemCost(String description) throws NotOnMenuException{
		Iterator<MenuItem> itr = this.menu.iterator();
		BigDecimal cost = new BigDecimal(0);
		Boolean valid = false;
		
		while (itr.hasNext()) {
			MenuItem item = itr.next();
			if (item.getDescription().equals(description)) {
				cost = item.getCost();
				valid = true;
			}
		}
		
		if(valid) {return cost.setScale(2,BigDecimal.ROUND_DOWN);}
		else { throw new NotOnMenuException("Description", description);}
	}

	/**
	 * Returns the menu item with a certain description
	 * 
	 * @param id
	 *            MenuItems Category
	 * @return MenuItem
	 */
	public MenuItem getItem(String description) throws NotOnMenuException {

		MenuItem item = null;
		Iterator<MenuItem> itr = this.menu.iterator();
		Boolean valid = false;
		
		while (itr.hasNext()) {
			MenuItem temp = itr.next();
			if (temp.getDescription().equals(description)) {
				item = temp;
				valid = true;
			}
		}
		if(valid) {return item;}
		else { throw new NotOnMenuException("Description", description);}
		
	}

	/**
	 * Return a string of an items details.
	 */
	public String itemDetails(MenuItem item) throws NotOnMenuException {
		
		inMenu(item);
		
		String description = item.getDescription();
		BigDecimal price = item.getCost();
		String category = item.getCategory();
		String id = item.getID();
		
		String itemDetails = description + ";" + price + ";" + category + ";" + id;
		return itemDetails;
	}

	public int getMenuSize() {
		return this.menu.size();
	}
	
	public HashSet<MenuItem> getMenu(){
		return menu;
	}

}
