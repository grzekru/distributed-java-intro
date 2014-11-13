package pl.edu.amu.dji.jms.lab2.jmsserver;

import org.apache.activemq.broker.BrokerService;

public class JMSServer 
{
    public static void main(String[] args) throws Exception 
    {
    	System.out.println("----- JMSServer start");
        BrokerService brokerService = new BrokerService();
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
