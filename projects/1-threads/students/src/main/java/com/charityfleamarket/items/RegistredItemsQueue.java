package com.charityfleamarket.items;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RegistredItemsQueue 
{
	private BlockingQueue<Item> itemQueue;
	
	public RegistredItemsQueue(int limit)
	{
		itemQueue = new ArrayBlockingQueue<Item>(limit);
	}
	
	public boolean addItem(Item item)
	{
		return itemQueue.offer(item);
	}
	
	public Item getItem() throws InterruptedException
	{
		return itemQueue.take();
	}
	
	public String getItemName()
	{
		return itemQueue.peek().name;
	}
	
	public boolean isEmpty()
	{
		return itemQueue.isEmpty();
	}
}