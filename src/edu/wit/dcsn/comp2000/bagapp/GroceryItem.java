package edu.wit.dcsn.comp2000.bagapp;

/**
 * Class that represents an item that you would find in a store based off the groceries.txt file and each items attributes
 * @author Matt Lefebvre
 */
public class GroceryItem 
{
	private final String name;
	private final GroceryItemSize size;
	private final String weight;
	private final String texture;
	private final String elasticity;
	private final boolean breakable;
	private final boolean perishable; 
	
	/**
	 * Constructor for the GroceryItem class, no default constructor available
	 * @param name - the grocery items name (e.g. - hot dog buns)
	 * @param size - the size of the item as a GroceryItemSize (e.g. GroceryItemSize.SMALL)
	 * @param weight - the weight of the item (e.g. - light, heavy)
	 * @param texture - the texture of the item (e.g. - soft)
	 * @param elasticity - the elasticity of the item (e.g. - flexible)
	 * @param breakable - whether item is easily breakable or not
	 * @param perishable - whether the item is perishable or not
	 */
	public GroceryItem(String name, GroceryItemSize size, String weight, String texture, String elasticity, boolean breakable, boolean perishable)
	{
		this.name = name;
		this.size = size;
		this.weight = weight;
		this.texture = texture;
		this.elasticity = elasticity;
		this.breakable = breakable;
		this.perishable = perishable;
	}

	/**
	 * Getter method for the GroceryItem's name
	 * @return String name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Getter method for the GroceryItem's size 
	 * @return GroceryItemSize size
	 */
	public GroceryItemSize getSize()
	{
		return this.size;
	}
	
	/**
	 * Getter method for the GroceryItem's weight
	 * @return String weight
	 */
	public String getWeight()
	{
		return this.weight;
	}
	
	/**
	 * Getter method for the GroceryItem's texture
	 * @return String texture
	 */
	public String getTexture()
	{
		return this.texture;
	}
	
	/**
	 * Getter method for the GroceryItem's elasticity
	 * @return String elasticity
	 */
	public String getElasticity()
	{
		return this.elasticity;
	}
	
	/**
	 * Getter method for whether the GroceryItem is breakable
	 * @return boolean breakable
	 */
	public boolean isBreakable() 
	{
		return this.breakable;
	}
	
	/**
	 * Getter method for whether the GroceryItem is perishable
	 * @return boolean perishable
	 */
	public boolean isPerishable() 
	{
		return this.perishable;
	}
	
	/**
	 * Returns a String representing this GroceryItem object
	 * @return String string
	 */
	public String toString()
	{
		return String.format("name=%s, size=%s, weight=%s, texture=%s, elasticity=%s, breakable=%b, perishable=%b", 
				this.name, this.size.toString(), this.weight, this.texture, this.elasticity, this.breakable, this.perishable);
	}
	
}
