package exercise1;

public class Main
{
    public static void main(String[] args)
    {
        Thread t = Thread.currentThread();
        System.out.println(t.getId());
        System.out.println(t.getName());
    }
}
