package main;

import java.math.BigDecimal;
import java.util.ArrayList;
/**
 * Class to encapsulate the discounted total, amount of money saved, and list of discounts used.
 * @author calumthompson
 *
 */
public class Discount {
	private BigDecimal discountedTotal;
	private BigDecimal savings;
	private ArrayList<String> appliedDiscounts;
	
	Discount(BigDecimal t, BigDecimal save, ArrayList<String> arrL){
		this.discountedTotal = t;
		this.appliedDiscounts = arrL;
		this.savings = save;
	}

	public BigDecimal getNewTotal() {
		return discountedTotal;
	}
	public BigDecimal getSavings() {
		return savings;
	}

	public ArrayList<String> getAppliedDiscounts() {
		return appliedDiscounts;
	}

	
	
}
