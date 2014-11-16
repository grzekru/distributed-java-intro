package com.charityfleamarket.roles;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.charityfleamarket.items.Item;

public class Recipient implements Runnable
{
	String name;
	private static int id = 0;
	Random rand = new Random();
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	//Recipient stores all winning items.
	private Vector<Item> wonItems = new Vector<Item>();
	
	public Recipient()
	{
		name = generateName();
	}
	
	//Recipient must have unique name.
	private synchronized String generateName()
	{
		id++;
		return "Recipient-" + Integer.toString(id);
	}
	
	public void notifyEnd()
	{
		lock.lock();
		try {
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void notifyWon(Item item)
	{
		System.out.println(this.name + " won " + item.name);
		
		wonItems.addElement(item);
		
		try {
			//If auction was won by recipient, recipient waits additional 5 to 15 seconds before registering for another auction.
			Thread.sleep(rand.nextInt((15000 - 5000) + 1) + 5000);
		} catch (InterruptedException e) {}
	}
	
	public void run() 
	{
		//Recipient participates in auctions as long as market is opened.
		while (MarketManager.getInstance().isMarketOpen == true)
		{
			try {
				//Recipient waits up to 5 seconds before registering for auction.
				Thread.sleep(rand.nextInt((5000) + 1));
			} catch (InterruptedException e) {}
			
			if (Chairman.getInstance().registerRecipient(this) == true)
			{
				//If registration for auction was successful, recipient waits for auction end.
				lock.lock();
				try {
					condition.await();
				} catch (InterruptedException e) {} finally {
					lock.unlock();
				}
			}
		}
		String end = "";
		end += (this.name + " says good bye leaving with items: ");
		for (Item item : wonItems)
		{
			end += (item.name + " ");
		}
		System.out.println(end);
	}
}