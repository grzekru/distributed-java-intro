package exercise3;

public class Main
{
    public static void main(String[] args)
    {
        Thread ta[] = new Thread[4];
        ta[0] = new Thread(new MyRunnable());
        ta[1] = new Thread(new MyRunnable());
        ta[2] = new Thread(new MyRunnable());
        ta[3] = new Thread(new MyRunnable());

        for (int i=0;i<ta.length;i++)
        {
            ta[i].start();
            /*try {
                ta[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

        int flag = 0;

        while(true)
        {
            for (int i=0;i<ta.length;i++)
            {
                System.out.println(ta[i].isAlive());
                if (ta[i].isAlive() == false)
                {
                    flag++;

                }
                else
                {
                    flag = 0;
                }


                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag >= 4)
            {
                break;
            }
        }
    }
}
