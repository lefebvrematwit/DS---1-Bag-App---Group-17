package edu.wit.dcsn.comp2000.bagapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that parses and bags GroceryItem objects obtained from reading the groceries.txt file
 * @author Matt Lefebvre
 * @author Brian Howard
 * @author Rodney Chan
 */
public class GroceryBagger 
{
	
	/**
	 * Parses GroceryItem objects from the groceries.txt file located in the data directory of this project
	 * @author Rodney Chan
	 * @return List of GroceryItem objects
	 * @throws FileNotFoundException - if the file is not present
	 */
	private static List<GroceryItem> parseGroceries(String path) throws FileNotFoundException 
	{
		System.out.printf("--- STARTING GROCERY PARSING OF %s ---%n", path);
		
		// Create a list to store the grocery item objects parsed from the groceries.txt file
		final List<GroceryItem> groceries = new ArrayList<>();
		
		// Create Scanner object to read the text file
		final Scanner sc = new Scanner(new File(path));
		while (sc.hasNextLine()) 
		{
			final String[] args = sc.nextLine().split("\t");
			groceries.add(new GroceryItem(
				args[0],
				GroceryItemSize.interpretDescription(args[1]),
				args[2],
				args[3],
				args[4],
				args[5].equalsIgnoreCase("breakable"),
				args[6].equalsIgnoreCase("perishable")
			));
			System.out.printf("Created GroceryItem and added to shopping cart - %s%n", args[0]);
		}
		
		// Close the Scanner and return the list of grocery items
		sc.close();
		System.out.printf("--- GROCERY PARSING of %s COMPLETE ---%n%n", path);
		return groceries;
	}
	
	/**
	 * Sorts the List of grocery items obtained from the parseGroceries method
	 * @author Brian Howard
	 * @param groceries - List of GroceryItems obtained via the parseGroceries() method
	 * @return Bag of GroceryBag objects
	 */
	private static List<GroceryBag> sortGroceries(List<GroceryItem> groceries) 
	{
		System.out.println("--- STARTING GROCERY SORTING ---");
		
		// Create a Bag to hold GroceryBags
		final List<GroceryBag> shoppingCart = new ArrayList<>();
		
		/*
		 * Create three bags to sort the items: one for breakable items (eggs, chips, etc.), one for hard items (soda and juice), 
		 * and one for other misc items
		 * 
		 * Fill the bags with items until the next addition would cause the bag to exceed its capacity. In that case, add the bag to the
		 * shopping cart, update the bags reference with a new bag and continue filling the bags with GroceryItems
		 */
		GroceryBag breakable = GroceryBag.createPlasticBag();
		GroceryBag hard = GroceryBag.createPlasticBag();
		GroceryBag misc = GroceryBag.createPlasticBag();
		for (GroceryItem item : groceries) 
		{
			// Check if the item is breakable, if so it should be isolated from other items that could potentially break it
			if (item.isBreakable()) 
			{
				System.out.printf("Adding %s to the breakable bag%n", item.getName());
				if (!breakable.add(item)) //  || item.getTexture().equalsIgnoreCase("soft")
				{
					System.out.printf("Unable to add %s to the breakable bag without exceeding capacity, adding bag to the shopping cart and creating a new bag%n", item.getName());
					shoppingCart.add(breakable);
					breakable = GroceryBag.createPlasticBag();
					breakable.add(item);
				}
			// Check if the item is a heavy item, if so group it together (e.g. - soda, juice, cans, etc.)
			} else if (item.getWeight().equalsIgnoreCase("heavy"))
			{
				System.out.printf("Adding %s to the hard bag%n", item.getName());
				if (!hard.add(item)) 
				{
					System.out.printf("Unable to add %s to the hard bag without exceeding capacity, adding bag to the shopping cart and creating a new bag%n", item.getName());
					shoppingCart.add(hard);
					hard = GroceryBag.createPlasticBag();
					hard.add(item);
				}
			// If the item is not breakable or heavy it can be put with any other items	
			} else 
			{
				System.out.printf("Adding %s to the misc bag%n", item.getName());
				if (!misc.add(item)) 
				{
					System.out.printf("Unable to add %s to the misc bag without exceeding capacity, adding bag to the shopping cart and creating a new bag%n", item.getName());
					shoppingCart.add(misc);
					misc = GroceryBag.createPlasticBag();
					misc.add(item);
				}
			}

		}
		
		// If the bags still have items but didnt get added to the cart because they did not exceed their capacity, add them before returning
		if (!breakable.isEmpty())
		{
			System.out.println("Adding breakable bag to the shopping cart");
			shoppingCart.add(breakable);
		}
		if (!hard.isEmpty())
		{
			System.out.println("Adding hard items bag to the shopping cart");
			shoppingCart.add(hard);
		}
		if (!misc.isEmpty())
		{
			System.out.println("Adding misc items bag to the shopping cart");
			shoppingCart.add(misc);
		}

		System.out.printf("--- GROCERY SORTING COMPLETE ---%n%n");
		return shoppingCart;
	}
		
	/**
	 * Runs the grocery simulation
	 * @param args - ignored
	 */
	public static void main(String[] args) 
	{
		// Load the GroceryItems from the groceries.txt file
		final List<GroceryItem> groceries;
		try 
		{
			groceries = GroceryBagger.parseGroceries("./data/groceries.txt");
		} catch (FileNotFoundException e) 
		{
			System.out.println("FATAL ERROR: UNABLE TO READ GROCERIES.TXT FILE");
			e.printStackTrace();
			return;
		}
	
		// Sort the GroceryItems into GroceryBags, then loop through and print out their contents
		final List<GroceryBag> shoppingCart = GroceryBagger.sortGroceries(groceries);
		System.out.println("--- STARTING DISPLAY OF BAG CONTENTS ---");
		for (GroceryBag bag : shoppingCart)
		{
			System.out.println(bag.toString());
		}
		System.out.printf("--- DISPLAY OF BAG CONTENTS COMPLETE ---%n%n");
	}

}
