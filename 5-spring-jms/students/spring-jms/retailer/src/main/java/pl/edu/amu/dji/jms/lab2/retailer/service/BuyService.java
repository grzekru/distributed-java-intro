package pl.edu.amu.dji.jms.lab2.retailer.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.google.common.base.Preconditions;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

public class BuyService implements MessageListener 
{
    private JmsTemplate jmsTemplate;

    private Double maxPrice;

    public void setJmsTemplate(JmsTemplate jmsTemplate) 
    {
    	System.out.println("----- setJmsTemplate");
        this.jmsTemplate = jmsTemplate;
    }

    public void setMaxPrice(Double maxPrice) 
    {
    	System.out.println("----- setMaxPrice");
        this.maxPrice = maxPrice;
    }

    public void onMessage(Message message) 
    {
    	Preconditions.checkArgument(message instanceof MapMessage);

        MapMessage mapMessage = (MapMessage) message;

        try
        {
            Double price = mapMessage.getDouble("price");
            if(maxPrice.compareTo(price)==1)
            {
            	System.out.println("----- MP compere");
                Destination replayTo = mapMessage.getJMSReplyTo();

                jmsTemplate.send(replayTo, new MessageCreator() 
                {
                    public Message createMessage(Session session) throws JMSException 
                    {
                    	System.out.println("----- BUY");
                    	
                        MapMessage buy = session.createMapMessage();
                        buy.setString("retailerID", getClass().getName());
                        buy.setInt("quantity", 10);

                        return buy;
                    }
                });
            }
        } 
        catch (JMSException ex)
        {
            throw new IllegalStateException(ex);
        }
    }
}
