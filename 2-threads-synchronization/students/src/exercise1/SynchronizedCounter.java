package exercise1;

import common.Counter;

public class SynchronizedCounter implements Counter 
{
	private long value = 0;
	
    public void increment() 
    {
    	value += 1;
    	System.out.println("increment: " + value);
    }

    public long getValue() 
    {
    	System.out.println("getValue: " + value);
    	return value;
    }
}
