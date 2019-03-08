/*****
Class for holding data concerning menu items
Contains id String, float cost and description String.
*****/
package main;

import java.math.BigDecimal;

public class MenuItem {

  private String id;
  private BigDecimal cost;
  private String description;
  private String category;

  public MenuItem(String id, BigDecimal cost, String description, String category){
    this.id = id;
    this.cost = cost;
    this.description = description;
    this.category = category;

  }

  public String getID(){
    return id;
  }

  public BigDecimal getCost(){
    return cost;
  }

  public String getDescription(){
    return description;
  }
  public String getCategory(){
	    return category;
	  }

}
