package exercise6.painters;

import exercise6.equipment.Brush;
import exercise6.equipment.Paint;

public class LeftHandedPainter extends Painter 
{
    public LeftHandedPainter(Paint paint, Brush brush) 
    {
        super(paint, brush);
    }

    public void run() 
    {
        try 
        {
            synchronized (brush) 
            {
            	System.out.println("LEFT SYNCH BRUSH");
                String takenPaint = paint.takePaint();
                Thread.sleep(100);

                synchronized (paint) 
                {
                	System.out.println("LEFT SYNCH PAINT");
                    String takenBrush = this.brush.takeBrush();
                    Thread.sleep(100);

                    System.out.printf("Left hand painter painting with %s and %s\n", takenPaint, takenBrush);
                }
            }
        } 
        catch (InterruptedException e) {}
    }
}
