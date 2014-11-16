package exercise1;

import common.Counter;

public class SynchronizedCounter implements Counter 
{
	private long value = 0;

    public synchronized void increment() 
    {
    	value++;
    }

    public long getValue() 
    {
        return value;
    }
}
