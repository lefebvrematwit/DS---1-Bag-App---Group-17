package edu.wit.dcsn.comp2000.bagapp;

import edu.wit.dcsn.comp2000.bagadt.BagInterface;
import edu.wit.dcsn.comp2000.bagadt.ResizableArrayBag;

/**
 * Class that models a grocery bag. Backed by a Bag and has a weight capacity. The weight of the bag can not exceed the 
 * defined capacity and add calls that do will be rejected.
 * @author Matt Lefebvre
 */
public class GroceryBag 
{
	/*
	 * The max weight standard grocery bags can hold in lb. A plastic bag can hold up to 17lb, a paper bag between 30-49lb.  
	 * Due to the paper bag having a weight range, we set the max to a number in the middle of the ranges 
	 */
	public static final int PLASTIC_BAG_MAX= 17;
	public static final int PAPER_BAG_MAX = 39;
	private final int capacity; // the max weight the bag can hold 
	private int currentWeight; // the current weight of a bag
	private final BagInterface<GroceryItem> groceries; // the bag holding grocery items
	
	/**
	 * The constructor for the GroceryBag class
	 * @param capacity - the weight capacity of this bag
	 */
	public GroceryBag(int capacity) 
	{
		this.capacity = capacity;
		this.currentWeight = 0;
		this.groceries = new ResizableArrayBag<>();
	}
	
	/**
	 * Method that attempts to add a GroceryItem to the GroceryBag. The method will return a boolean based on whether or not
	 * the bag can fit the item (depends on the weight of the item and remaining weight in the bag)
	 * @param item
	 * @return boolean 
	 */
	public boolean add(GroceryItem item) 
	{
		// capacity check
		if (item.getSize().sizeValue > this.getRemainingCapacity()) 
		{
			return false;
		}
	
		this.groceries.add(item);
		this.currentWeight += item.getSize().sizeValue;
		return true;
	}
	
	/**
	 * Removes a GroceryItem from the Bag if items are present. Returns null otherwise
	 * @return GroceryItem item (null if empty)
	 */
	public GroceryItem remove() 
	{
		return this.groceries.remove();
	}
	
	/**
	 * Method to get the grocery bags capacity (i.e. - the total weight it can hold)
	 * @return int capacity
	 */
	public int getCapacity() 
	{
		return this.capacity;
	}
	
	/**
	 * Method to get the remaining capacity in the GroceryBag
	 * @return int remainingCapacity
	 */
	public int getRemainingCapacity() 
	{
		return this.capacity - this.currentWeight;
	}
	
	/**
	 * Method to get the item count of the grocery bag
	 * @return int items in the bag
	 */
	public int getItemCount() 
	{
		return this.groceries.getCurrentSize();
	}
	
	/**
	 * Method to check if the grocery bag is empty
	 * @return boolean empty
	 */
	public boolean isEmpty()
	{
		return this.groceries.isEmpty();
	}

	@Override
	public String toString() 
	{
		final StringBuilder builder = new StringBuilder("capacity: " + this.capacity + ", currentWeight: " + this.currentWeight+ ", groceries:[");
		
		for (Object item : this.groceries.toArray())
		{
			builder.append(System.lineSeparator()).append("\t").append(item.toString()).append(",");
		}
		
		builder.append(System.lineSeparator()).append("]");
		return builder.toString();
	}
	
	/**
	 * Method that returns a GroceryBag that has its capacity set at the max capacity a plastic bag can hold
	 * @return GroceryBag object with a capacity of 17 (GroceryBag.PLASTIC_BAG_MAX)
	 */
	public static GroceryBag createPlasticBag() 
	{
		return new GroceryBag(PLASTIC_BAG_MAX);
	}
	
	/**
	 * Method that returns a GroceryBag that has its capacity set at the max capacity a paper bag can hold
	 * @return GroceryBag object with a capacity of 39 (GroceryBag.PAPER_BAG_MAX)
	 */
	public static GroceryBag createPaperBag() 
	{
		return new GroceryBag(PAPER_BAG_MAX);
	}
	
	
}
