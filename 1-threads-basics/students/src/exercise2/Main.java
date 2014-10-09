package exercise2;

public class Main
{
    public static void main(String[] args)
    {
        Thread ta[] = new Thread[4];
        ta[0] = new MyThread("Thread 1");
        ta[1] = new MyThread("Thread 2");
        ta[2] = new MyThread("Thread 3");
        ta[3] = new MyThread("Thread 4");

        for (int i=0;i<ta.length;i++)
        {
            ta[i].start();
        }
    }
}
