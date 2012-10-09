//----------------------------------------------------------------------------------------------------
// Author: Group9   
// Line class is derived from Shape class and draws lines between given two points with chosen color.
//----------------------------------------------------------------------------------------------------
import java.awt.*;
import java.io.*;

public class Line extends Shape
{
   protected Point firstPoint,secondPoint;
 
   public Line (Color color, Point p1, Point p2)
     {
      		chosenColor = color;
      		firstPoint = p1;
      		secondPoint = p2;
     }
   public void setEndPoint (Point endPoint)
     {
    		secondPoint = endPoint;
     }
   public void draw (Graphics page)
    {
      	page.setColor (chosenColor);
        page.drawLine (firstPoint.x, firstPoint.y, secondPoint.x,secondPoint.y);
    }

    @Override
    public boolean isBlocker() {
        return false;
    }
}
