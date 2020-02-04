package edu.wit.dcsn.comp2000.bagapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Simulates a grocery trip
public class GrocerySimulation 
{
	
	public static List<GroceryItem> parseGroceries() throws FileNotFoundException 
	{
		// Create a list to store the grocery item objects parsed from the groceries.txt file
		final List<GroceryItem> groceries = new ArrayList<>();
		
		// Create Scanner object to read the text file
		final Scanner sc = new Scanner(new File("data/groceries.txt"));
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
		}
		
		// Close the Scanner and return the list of grocery items
		sc.close();
		return groceries;
	}
	
	public static void main(String[] args) 
	{
		// Load the GroceryItems from the groceries.txt file
		final List<GroceryItem> groceries;
		try 
		{
			groceries = GrocerySimulation.parseGroceries();
		} catch (FileNotFoundException e) 
		{
			System.out.println("FATAL ERROR: UNABLE TO READ GROCERIES.TXT FILE");
			e.printStackTrace();
			return;
		}
	
		// Test to ensure that the objects loaded correctly, should be removed when Brian writes his code
		for (GroceryItem item : groceries) 
		{
			System.out.println(item.toString());
			
		}
	}

}
