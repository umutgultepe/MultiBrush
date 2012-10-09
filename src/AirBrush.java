//----------------------------------------------------------------------------------------------------
// Author: Group9  
//----------------------------------------------------------------------------------------------------
import java.awt.*;
import java.io.*;
import java.util.*;

public class AirBrush extends Shape
{
    protected Point basePoint;
    protected int width=3;
    protected int height=3;

    public AirBrush(Color color, Point corner)
      {
      		chosenColor = color;
      		basePoint = corner;      	
      }    
   public void draw (Graphics page)
    {       
       for(int i=1;i<=20;i++)
       {
       		Random gen = new Random();
       		int a = gen.nextInt(20)+1;
       		int b = gen.nextInt(20)+1;
       
       		page.setColor (chosenColor);
       		page.fillOval (basePoint.x+a, basePoint.y+b, width, height);
       }
    }

    @Override
    public boolean isBlocker() {
        return false;
    }
}