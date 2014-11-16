package com.charityfleamarket.roles;

import java.util.Random;
import com.charityfleamarket.items.Item;

public class Donor implements Runnable
{
	String name;
	private static int id = 0;
	Random rand = new Random();
	
	public Donor()
	{
		name = generateName();
	}
	
	//Donor must have unique name.
	private synchronized String generateName()
	{
		id++;
		return "Donor-" + Integer.toString(id);
	}
	
	public void run() 
	{
		//Donor participates in auctions as long as market is opened.
		while (MarketManager.getInstance().isMarketOpen == true)
		{	
			Item item = new Item();
			
			if (MarketManager.getInstance().isMarketOpen == false)
				break;
			
			while (Chairman.getInstance().addItemToQueue(item) == false)
			{
				try {
					//If the queue of registered items is full, donor waits up to 5 seconds and tries again.
					Thread.sleep(rand.nextInt((5000) + 1));
				} catch (InterruptedException e) {}

				if (MarketManager.getInstance().isMarketOpen == false)
					break;
			}
			System.out.println(this.name + " adds item " + item.name);
			
			try {
				//Donor waits between 5 to 30 seconds before registering the item.
				Thread.sleep(rand.nextInt((30000 - 5000) + 1) + 5000);
			} catch (InterruptedException e) {}
		}
		System.out.println(this.name + " says good bye");
	}
}