package edu.wit.dcsn.comp2000.bagapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.wit.dcsn.comp2000.bagadt.BagInterface;
import edu.wit.dcsn.comp2000.bagadt.ResizableArrayBag;

/**
 * Class that models a grocery bag. Backed by a Bag and has a weight capacity. The weight of the bag can not exceed the defined capacity
 * and add calls that do will be rejected.
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
	private final int capacity;
	private int currentWeight;
	private final BagInterface<GroceryItem> groceries;
	
	/**
	 * The constructor for the GroceryBag class
	 * @param capacity - the weight capaicity of this bag
	 */
	public GroceryBag(int capacity) 
	{
		this.capacity = capacity;
		this.currentWeight = 0;
		this.groceries = new ResizableArrayBag<>();
	}
	
	/**
	 * Method that attempts to add a GroceryItem to the GroceryBag. The method will return a boolean based on whether or not
	 * the bag can fit the item (dependent on the weight of the item and remaining weight in the bag)
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
		
		this.add(item);
		return true;
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
	 * Method that returns a GroceryBag that has its capacity set at the max capacity a plastic bag can hold
	 * @return GroceryBag object with a capacity of 17 (GroceryBag.PLASTI_BAG_MAX)
	 */
	public static GroceryBag createPlasticBag() 
	{
		return new GroceryBag(PLASTIC_BAG_MAX);
	}
	
	/**
	 * Method that returns a GroceryBag that has its capacity set at the max capacity a paper bag cam hold
	 * @return GroceryBag object with a capacity of 39 (GroceryBag.PAPER_BAG_MAX)
	 */
	public static GroceryBag createPaperBag() 
	{
		return new GroceryBag(PAPER_BAG_MAX);
	}
	/**
	 * @author chanr1
	 * @return
	 * @throws FileNotFoundException
	 * Method to move groceries.txt content to list. Splits every String in array and returns list
	 */
	public List<String> ParseGrocery() throws FileNotFoundException
	{
		//test
		List<String> a = new ArrayList<>();//test
		File f = new File("groceries.txt");
		Scanner s = new Scanner(f);
		while (s.hasNextLine())
		{
			a.add((s.nextLine()));
		}
		for(String grocery: a)
		{
			 grocery.split("\t");
		}
		return a;
		
		
		
	}
	
}
