package exercise4;

import exercise3.*;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Main
{
    public static void main(String[] args)
    {
        Thread ta[] = new Thread[4];
        ta[0] = new Thread(new MyRunnable());
        ta[1] = new Thread(new MyRunnable());
        ta[2] = new Thread(new MyRunnable());
        ta[3] = new Thread(new MyRunnable());

        ExecutorService a = Executors.newFixedThreadPool(4);
        for (int i=0;i<4;i++)
        {
            a.execute(ta[0]);
        }
        a.shutdown();

        try {
            a.awaitTermination(10000,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("To jest już koniec!");
    }
}
