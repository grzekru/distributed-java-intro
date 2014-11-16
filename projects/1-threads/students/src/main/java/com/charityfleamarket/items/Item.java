package com.charityfleamarket.items;

public class Item 
{
	public String name;
	private static int id = 0;
	
	public Item()
	{
		name = generateName();
	}
	
	//Item must have unique name.
	private synchronized String generateName()
	{
		id++;
		return "Item-" + Integer.toString(id); 
	}
}