/**
 * @author alex birks (vandyn)
 * 
 * Version: 1.0, 17/03/19
 * 
 * Description: This class creates a text file and populates it with pre-existing orders
 */

package RefactoredCode;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class script_orders {
	
	ArrayList<String[]> order;
	Random generate = new Random();
	String[] name = {"Chloe","Marcus","Susan","Henry","Jenny","Lucy","Alex","Shayne","AJ","Jack","Calum"};
	
	//Change this number to increase or decrease the number of orders in the text file
	int numberOfOrders = 50;
	
	public script_orders() {
		String m = "src/main/exampleMenu.txt";
		order = new ArrayList<String[]>();
		getItemDescriptions(m);
		writeToTextFile();
	}
	
	private void getItemDescriptions(String filename) {
		try(BufferedReader itemDes = new BufferedReader(new FileReader(filename))) {
			String str;
			while((str = itemDes.readLine())!= null) {
				String[] menuLine = str.split(";");
				order.add(menuLine);
			}			
		} catch (FileNotFoundException e) {
			System.out.println("File not found @ script_orders class line 29");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("file found but error reading ");
			e.printStackTrace();
		}
	}
	
	private void writeToTextFile() {
		
		try {
			FileWriter writer = new FileWriter("src/main/existingOrders", false);
			
			for(int i=0;i<numberOfOrders;i++) {
				//Determines number of items ordered in each order
				int numItems = generate.nextInt(3)+1;
				
				float total = 0;
				String[] ordID = new String[numItems];
				String[] ordItems = new String[numItems];

				//Returns a string containing item details and format for writing to file
				for(int c=0; c<numItems;c++) {
					String[] item=order.get(generate.nextInt(order.size()));
					ordID[c] = item[0];
					ordItems[c] = item[2].concat(":".concat(item[3]));
					total = total + Float.parseFloat((item[1]));
				}
				String oID = Arrays.toString(ordID).replaceAll("\\[", "").replaceAll("\\]","").replaceAll(", ",",");
				String oItems = Arrays.toString(ordItems).replaceAll("\\[", "").replaceAll("\\]","").replaceAll(", ",",");
				writer.write(name[generate.nextInt(4)] + "/"+oID+";"+total+";"+oItems);
				writer.write("\r\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	
}
