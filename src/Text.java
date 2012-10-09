//----------------------------------------------------------------------------------------------------
// Author: Group9   
// Text class is derived from Shape class and used for adding text into particular areas.
//----------------------------------------------------------------------------------------------------
import java.awt.*;
import java.io.*;

public class Text extends Shape
{
   protected Point upperLeft;
   protected String str;

   public Text (Color color, Point corner, String s)
   {
      	chosenColor = color;
        upperLeft = corner;
     	str=s;
   }

   public void setText(String str){
        this.str = str;
   }
   public String getText()
   {
   	return str;	
   }

   public void draw (Graphics page)
   {
       	page.setColor (chosenColor);
        page.drawString ( str , upperLeft.x, upperLeft.y);
   }

    @Override
    public boolean isBlocker() {
        return false;
    }
}
