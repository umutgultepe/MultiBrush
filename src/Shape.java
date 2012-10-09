//---------------------------------------------------------------------------------------------------
// Author: Group9  
// Shape class is a parent class for geometric shape classes.
// It includes an abstract method called 'draw' for overriding.
//---------------------------------------------------------------------------------------------------
import java.awt.*;
import java.io.*;

public abstract class Shape
{
   		protected Color chosenColor;

   		public Color getChosenColor ()
   		{
     		 return chosenColor;
   		}
                abstract public boolean isBlocker();
  		 abstract public void draw (Graphics page);
}
