package pl.edu.amu.dji.jms.lab2.retailer;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

public class RetailerApp 
{
    public static void main(String[] args) 
    {
    	System.out.println("----- RetailerApp start");
        new ClassPathXmlApplicationContext("/context.xml");
    }
}
