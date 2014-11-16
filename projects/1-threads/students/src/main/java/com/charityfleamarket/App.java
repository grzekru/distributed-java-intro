package com.charityfleamarket;

import com.charityfleamarket.roles.Donor;
import com.charityfleamarket.roles.MarketManager;
import com.charityfleamarket.roles.Recipient;

public class App 
{
	public void charityFleaMarket()
	{
		MarketManager marketManager = MarketManager.getInstance();
		
		for (int i=0;i<2;i++)
		{
			marketManager.registerDonor(new Donor());
		}
		for (int i=0;i<4;i++)
		{
			marketManager.registerRecipient(new Recipient());
		}
		
		marketManager.openMarket();
	}
	
    public static void main(String args[])
    {
        new App().charityFleaMarket();
    }
}