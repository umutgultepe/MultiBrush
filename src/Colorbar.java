//---------------------------------------------------------------------------------------------------
// Author: Group9   
// Colorbar class keeps a group of color buttons which uses JRadioButton components. And this class
// has also an actionPerformed method which  determines what to do when a color button selected.
//---------------------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Colorbar extends JToolBar
{
   public static final int RED=19;
   public static final int YELLOW=20;
   public static final int BLUE=21;
   public static final int GREEN=22;
   public static final int CYAN=23;
   public static final int PINK=24;
   public static final int ORANGE=25;
   public static final int BLACK=26;
   public static final int WHITE=27;
   public static final int MAGENTA=28;
   public static final int BROWN=29;
   			
   private JRadioButton  red,yellow,blue,green,cyan,pink,orange,black,white,magenta,brown;
   private JButton       chosenButton,undoButton;
   private int   currentButton;    // Shows the current button using an integer to make our work easier.
   public static Color currentChosenColor;
   
   public Colorbar ()
    {
     	currentButton = BLACK;
      	currentChosenColor = Color.black;
        ButtonListener listener = new ButtonListener ();        
      	//------------------------------------------COLOR GROUP-----------------------------------------------
      	ButtonGroup colorGroup = new ButtonGroup ();
      
      	red = new JRadioButton (new ImageIcon ("../img/red.gif"));
      	red.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	red.setToolTipText ("Red");
      	red.addActionListener (listener);
      
      	yellow = new JRadioButton (new ImageIcon ("../img/yellow.gif"));
      	yellow.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	yellow.setToolTipText ("Yellow");
      	yellow.addActionListener (listener);
      
      	blue = new JRadioButton (new ImageIcon ("../img/blue.gif"));
      	blue.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	blue.setToolTipText ("Blue");
      	blue.addActionListener (listener);
      
      	green = new JRadioButton (new ImageIcon ("../img/green.gif"));
      	green.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	green.setToolTipText ("Green");
      	green.addActionListener (listener);
      	
      	cyan = new JRadioButton (new ImageIcon ("../img/cyan.gif"));
      	cyan.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	cyan.setToolTipText ("Cyan");
      	cyan.addActionListener (listener);
      	
      	pink = new JRadioButton (new ImageIcon ("../img/pink.gif"));
        pink.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	pink.setToolTipText ("Pink");
      	pink.addActionListener (listener);
      	
      	orange = new JRadioButton (new ImageIcon ("../img/orange.gif"));
      	orange.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	orange.setToolTipText ("Orange");
      	orange.addActionListener (listener);
      	
      	black = new JRadioButton (new ImageIcon ("../img/black.gif"));
      	black.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	black.setToolTipText ("Black");
      	black.addActionListener (listener);
      	
        white = new JRadioButton (new ImageIcon ("../img/white.gif"));
      	white.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	white.setToolTipText ("White");
      	white.addActionListener (listener);
      	
      	magenta = new JRadioButton (new ImageIcon ("../img/purple.gif"));
      	magenta.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	magenta.setToolTipText ("Magenta");
      	magenta.addActionListener (listener);      
      
      	brown = new JRadioButton (new ImageIcon ("../img/brown.gif"));
      	brown.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	brown.setToolTipText ("Brown");
      	brown.addActionListener (listener);     	
      
        colorGroup.add (red);
      	colorGroup.add (yellow);   
      	colorGroup.add (blue);
      	colorGroup.add (green);
      	colorGroup.add (cyan);
        colorGroup.add (pink);
	colorGroup.add (orange);
      	colorGroup.add (black);
	colorGroup.add (white);
	colorGroup.add (magenta);      	
      	colorGroup.add (brown);
	  
      	add (red);
      	add (yellow);
     	add (blue);
      	add (green);
      	add (cyan);
	    add (pink);
	  	add (orange);
      	add (black);
	  	add (white);
	  	add (magenta);      	
      	add (brown);
       	setFloatable (false);      // Prevents to move the toolbar from one place to another place.
   	}
    public int getButton ()
    {
       return currentButton;
    }
    public Color getChosenColor ()
    {
        return currentChosenColor;
    }
    private class ButtonListener implements ActionListener
     {      
      	public  void actionPerformed (ActionEvent event)
      	  {
        		 Object source=event.getSource (); // shows the source of button clicked.       		 
           		 if (source == pink)
           		    currentChosenColor = Color.pink;	
           		 else if (source == red)
           		    currentChosenColor = Color.red;	
           		 else if (source == blue)
           		    currentChosenColor = Color.blue;	
           		 else if (source == magenta)
           		    currentChosenColor = Color.magenta;	
           		 else if (source == cyan)
           		    currentChosenColor = Color.cyan;	
           		 else if (source == green)
           		    currentChosenColor = Color.green;	
           		 else if (source == yellow)
           		    currentChosenColor = Color.yellow;	
           		 else if (source == brown)
           		    currentChosenColor = new Color(139,69,19);	
           		 else if (source == orange)
           		    currentChosenColor = Color.orange;	
           		 else if (source == white)
           		    currentChosenColor = Color.white;	
           		 else if (source == black)
           		    currentChosenColor = Color.black;           		     	
           }
    }
}
