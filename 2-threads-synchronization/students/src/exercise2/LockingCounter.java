package exercise2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import common.Counter;

public class LockingCounter implements Counter 
{
	private final Lock lock = new ReentrantLock();
	private long value = 0;

	public void increment() 
	{
		lock.lock();
		try 
		{
			value += 1;
		} 
		finally 
		{
			lock.unlock();
		}
	}

	public long getValue() 
	{
		return value;
	}
}