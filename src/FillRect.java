//----------------------------------------------------------------------------------------------------
// Author: Group9   
// FillRect class is derived from Shape class and draws filled rectangles with given parameters.
//----------------------------------------------------------------------------------------------------
import java.awt.*;
import java.io.*;

public class FillRect extends Shape
{
    protected Point upperLeft;
    protected int width, height;

    public FillRect (Color color, Point corner, int w, int h)
      {
      		chosenColor = color;
      		upperLeft = corner;
      		width = w;
       		height = h;
      }
    public void setShape (Point firstPoint, Point currentPoint)
     {
      	if (firstPoint.x <= currentPoint.x)
         	if (firstPoint.y <= currentPoint.y)
              	 upperLeft = firstPoint;
        else
            	 upperLeft = new Point (firstPoint.x, currentPoint.y);
        else
           if (firstPoint.y <= currentPoint.y)
                 upperLeft = new Point (currentPoint.x, firstPoint.y);
           else
                 upperLeft = currentPoint;
       width = Math.abs (currentPoint.x - firstPoint.x);
       height = Math.abs (currentPoint.y - firstPoint.y);
     }
   public void draw (Graphics page)
    {
        page.setColor (chosenColor);
        page.fillRect (upperLeft.x, upperLeft.y, width, height);
    }

    @Override
    public boolean isBlocker() {
        return false;
    }
}
