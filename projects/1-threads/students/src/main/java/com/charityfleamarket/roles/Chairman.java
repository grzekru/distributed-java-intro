package com.charityfleamarket.roles;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.charityfleamarket.items.Item;
import com.charityfleamarket.items.RegistredItemsQueue;

public class Chairman implements Runnable
{
	private static Chairman instance;
	//Chairman maintains queue of registered items.
	private RegistredItemsQueue itemsQueue;
	//Chairman maintains list of registered recipients for upcoming auction.
	private Vector<Recipient> recipientsList = new Vector<Recipient>();
	Random rand = new Random();
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	private Chairman()
	{
		//Queue length is limited to 10 items.
		itemsQueue = new RegistredItemsQueue(10);
	}
	
	static
	{
		instance = new Chairman();
	}
	
	public static Chairman getInstance()
	{
		return instance;
	}
	
	public synchronized boolean registerRecipient(Recipient recipient)
	{
		//List length is limited to 10 items.
		if (recipientsList.size() == 10)
		{
			return false;
		}
		System.out.println("Registering " + recipient.name);
		recipientsList.addElement(recipient);
		return true;
	}
	
	public boolean addItemToQueue(Item item)
	{
		lock.lock();
		condition.signal();
		lock.unlock();
		return itemsQueue.addItem(item);
	}
	
	public Item getItemFromQueue() throws InterruptedException
	{
		return itemsQueue.getItem();
	}
	
	private Recipient drawWinner()
	{
		//If there was at least one recipient for current item, chairman selects a winner randomly.
		int winnerID = rand.nextInt(recipientsList.size());
		Recipient winner = recipientsList.get(winnerID);
		System.out.println("Winner for auction " + itemsQueue.getItemName() + " is " + winner.name);
		return winner;
	}
	
	private void notifyWinner(Recipient winner)
	{
		try {
			//Chairman notifies the winner about won item.
			winner.notifyWon(getItemFromQueue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void notifyrecipients()
	{
		//Chairman makes sure all recipients know that particular auction was finished.
		for (Recipient recipiant : recipientsList)
		{
			recipiant.notifyEnd();
		}
		recipientsList.clear();
	}
	
	private void setNewAuction()
	{
		Recipient winner = drawWinner();
		notifyWinner(winner);
		notifyrecipients();
	}
	
	public void run() 
	{
		while (MarketManager.getInstance().isMarketOpen == true)
		{
			try {
				//Chairman waits exactly 5 seconds before drawing winning recipient.
				Thread.sleep(5000);
			} catch (InterruptedException e) {}
			
			if (itemsQueue.isEmpty())
			{
				boolean notEmpty = false;
				lock.lock();
				try {
					notEmpty = condition.await(5, TimeUnit.SECONDS);
				} catch (InterruptedException e) {} finally {
					lock.unlock();
				}
				if (!notEmpty)
				{
					//If there are still no items, chairman asks market manager to close the market and stops further work.
					System.out.println("No auctions within 5 seconds. Closing the market");
					MarketManager.getInstance().isMarketOpen = false;
					notifyrecipients();
					MarketManager.getInstance().closeMarket();
				}
			}
			else
			{
				//If there was no recipients for current item, nothing happens.
				if (!recipientsList.isEmpty())
				{
					setNewAuction();
				}
				else
				{
					System.out.println("There is no winner for " + itemsQueue.getItemName());
				}
			}
		}
		System.out.println("Chairman says good bye");
	}
}