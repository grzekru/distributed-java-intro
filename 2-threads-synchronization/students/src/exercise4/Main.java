package exercise4;

import common.Counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import exercise1.SynchronizedCounter;
import exercise2.LockingCounter;
import exercise3.AtomicCounter;
import static common.CountingRunner.numberOfIterations;
import static common.CountingRunner.numberOfThreads;

public class Main
{
	public static void main(String[] args) throws InterruptedException 
	{
		Counter counter = new LockingCounter();
		execute(counter);
	}
	private static void execute(Counter counter) throws InterruptedException 
	{
		ExecutorService executors = Executors.newCachedThreadPool();
		for (int i = 0; i < numberOfThreads; ++i) 
		{
			executors.execute(new EvenCheckingTask(counter, numberOfIterations));
		}
		executors.shutdown();
		executors.awaitTermination(30, TimeUnit.SECONDS);
		System.out.println("Actual: " + counter.getValue() + ", Expected: " + 
				(2 * numberOfThreads * numberOfIterations));
	}
}