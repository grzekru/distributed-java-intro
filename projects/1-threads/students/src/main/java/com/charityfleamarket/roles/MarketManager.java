package com.charityfleamarket.roles;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MarketManager 
{
	private static MarketManager instance;
	Vector<Donor> donorList = new Vector<Donor>();
	Vector<Recipient> recipientList = new Vector<Recipient>();
	ExecutorService executor = Executors.newCachedThreadPool();
	private Chairman chairman = Chairman.getInstance();
	public boolean isMarketOpen = false;
	
	static 
	{
		instance = new MarketManager();
	}
	
	public synchronized static MarketManager getInstance()
	{
		return instance;
	}
	
	//Market manager allows any number of donors to register.
	public void registerDonor(Donor donor)
	{
		donorList.add(donor);
	}
	
	//Market manager allows any number of recipients to register. 
	public void registerRecipient(Recipient recipient)
	{
		recipientList.add(recipient);
	}
	
	//Market manager opens market and lets all donors and recipients into the market.
	public void openMarket()
	{
		isMarketOpen = true;
		for (Donor donor : donorList)
		{
			executor.execute(donor);
		}
		for (Recipient recipient : recipientList)
		{
			executor.execute(recipient);
		}
		executor.execute(chairman);
	}
	
	//Market manager closes market and asks all donors and recipients to leave the market.
	public void closeMarket()
	{
		isMarketOpen = false;
		try {
			executor.shutdown();
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {}
	}
}