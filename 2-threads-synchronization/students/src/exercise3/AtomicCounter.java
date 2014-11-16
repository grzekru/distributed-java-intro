package exercise3;

import java.util.concurrent.atomic.AtomicLong;

import common.Counter;

public class AtomicCounter implements Counter 
{
	private final AtomicLong value = new AtomicLong();
	
	public void increment() 
	{
		value.incrementAndGet();
	}
	
	public long getValue() 
	{
		return value.longValue();
	}
}