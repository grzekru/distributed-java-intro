package exercise2;

public class MyThread extends Thread
{
    public MyThread(String name)
    {
        super.setName(name);
        System.out.println("Konstruktor - " + this.getName());
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        System.out.println("Run - " + this.getName());
    }
}
