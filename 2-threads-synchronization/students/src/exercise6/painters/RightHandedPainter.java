package exercise6.painters;

import exercise6.equipment.Brush;
import exercise6.equipment.Paint;

public class RightHandedPainter extends Painter 
{
    public RightHandedPainter(Paint paint, Brush brush)
    {
        super(paint, brush);
    }

    public void run() 
    {
        try 
        {
            synchronized (brush) 
            {
            	System.out.println("RIGHT SYNCH BRUSH");
                String takenBrush = brush.takeBrush();
                Thread.sleep(100);

                synchronized (paint) 
                {
                	System.out.println("RIGHT SYNCH PAINT");
                    String takenPaint = paint.takePaint();
                    Thread.sleep(100);

                    System.out.printf("Right hand painter painting with %s and %s\n", takenPaint, takenBrush);
                }
            }
        } catch (InterruptedException e) {}
    }
}
