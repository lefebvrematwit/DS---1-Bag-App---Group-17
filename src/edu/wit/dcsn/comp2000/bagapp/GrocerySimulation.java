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
	public static void main(String[] args) 
	{
		
	}
	public Object[] ParseGrocery() throws FileNotFoundException
	{
		
		List<String> a = new ArrayList<>();//initialize arrayLists
		List<String> b = new ArrayList<>();
		
		File f = new File("groceries.txt");
		Scanner s = new Scanner(f);//read in from groceries.txt
		
		while (s.hasNextLine())//add groceries.txt line by line
		{
			
			a.add((s.nextLine()));
		}
		
		for(String grocery : a)//split 
		{
			b.addAll(Arrays.asList(grocery.split("/t")));
			
		}
		
		Object[] groceries = b.toArray();
		s.close();
		return groceries;
		
		
		
	}
}
