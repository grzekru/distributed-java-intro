package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.google.common.base.Preconditions;

public class OrderService implements MessageListener 
{
    public void onMessage(Message message) 
    {
    	try
        {
            Preconditions.checkArgument(message instanceof MapMessage);

            MapMessage mapMessage = (MapMessage) message;
            int quantity = mapMessage.getInt("quantity");
            String retailerID = mapMessage.getString("retailerID");

            System.out.println("Ordered quantity: " + quantity + " by " + retailerID);
        } 
        catch (JMSException ex)
        {
            throw new IllegalStateException(ex);
        }
    }
}
