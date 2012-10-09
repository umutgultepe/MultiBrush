//---------------------------------------------------------------------------------------------------
// Author: Group9 
// Toolbar class keeps a group of tools which uses JRadioButton components. And this class
// has also an actionPerformed method which  determines what to do when a button selected.
//---------------------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Toolbar extends JToolBar
{
   public static final int LINE=0;
   public static final int OVAL=1;
   public static final int RECT=2;
   public static final int COLOR=3;
   public static final int PENCIL=4;
   public static final int ERASER=5;
   public static final int FILLRECT=6;
   public static final int FILLOVAL=7;
   public static final int UNDO=8;
   public static final int TEXT=9;
   public static final int BLOCK=10;
   public static final int AIR=-1;	   

   private JRadioButton  lineButton,ovalButton,fillOvalButton,rectButton,fillRectButton;
   private JRadioButton  eraserButton,pencilButton,airButton, textButton, blockButton;
   private JButton chosenButton;
   public static JButton  undoButton;
   private int currentButton;    // Shows the current button using an integer to make our work easier.
   
   public Toolbar ()
   {
     	currentButton = LINE;
        ButtonListener listener = new ButtonListener ();
        //-----------------------------------TOOL GROUP-----------------------------------------------------
      	ButtonGroup toolGroup = new ButtonGroup ();	 	

      	lineButton = new JRadioButton (new ImageIcon ("../img/line.gif"));
      	lineButton.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	lineButton.setToolTipText ("Drawing Line Tool");
      	lineButton.addActionListener (listener);

      	ovalButton = new JRadioButton (new ImageIcon ("../img/oval.gif"));
      	ovalButton.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	ovalButton.setToolTipText ("Drawing Oval Tool");
      	ovalButton.addActionListener (listener);
      	
      	fillOvalButton = new JRadioButton (new ImageIcon ("../img/filloval.gif"));
      	fillOvalButton.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	fillOvalButton.setToolTipText ("Filing Oval Tool");
      	fillOvalButton.addActionListener (listener);

      	rectButton = new JRadioButton (new ImageIcon ("../img/rect.gif"));
      	rectButton.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	rectButton.setToolTipText ("Drawing Rectangle Tool");
      	rectButton.addActionListener (listener);
      	
      	fillRectButton = new JRadioButton (new ImageIcon ("../img/fillrect.gif"));
      	fillRectButton.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	fillRectButton.setToolTipText ("Filling Rectangle Tool");
      	fillRectButton.addActionListener (listener);
      	
      	eraserButton = new JRadioButton (new ImageIcon ("../img/eraser.gif"));
      	eraserButton.setSelectedIcon (new ImageIcon ("../img/tickicon.gif"));
      	eraserButton.setToolTipText ("Eraser Tool");
      	eraserButton.addActionListener (listener);
      	
      	pencilButton = new JRadioButton (new ImageIcon ("../img/pencil.gif"));
        pencilButton.setSelectedIcon (   new ImageIcon ("../img/tickicon.gif"));
        pencilButton.setToolTipText ("Pencil Tool");
        pencilButton.addActionListener (listener);      
        	
        airButton = new JRadioButton (new ImageIcon ("../img/airbrush.gif"));
        airButton.setSelectedIcon (   new ImageIcon ("../img/tickicon.gif"));
        airButton.setToolTipText ("Pencil Tool");
        airButton.addActionListener (listener);
        
        //??
        textButton = new JRadioButton (new ImageIcon ("../img/text.gif"));
        textButton.setSelectedIcon (   new ImageIcon ("../img/tickicon.gif"));
        textButton.setToolTipText ("Text Tool");
        textButton.addActionListener (listener);        	

        blockButton = new JRadioButton (new ImageIcon ("../img/block.png"));
        blockButton.setSelectedIcon (   new ImageIcon ("../img/tickicon.gif"));
        blockButton.setToolTipText ("Block Tool");
        blockButton.addActionListener (listener);

      	chosenButton = new JButton ("Choose");
      	chosenButton.setToolTipText ("Choose Color");
      	chosenButton.setBackground (Color.black);
      	chosenButton.setForeground (Color.white);
      	chosenButton.addActionListener (listener);
      	chosenButton.setVisible(false);
      
      	undoButton = new JButton ("Undo");
      	undoButton.setToolTipText ("Undo option");
      	undoButton.setBackground (Color.black);
      	undoButton.setForeground (Color.white);
      	undoButton.addActionListener (listener);
      	undoButton.setVisible(true);
      
      	toolGroup.add(lineButton);
      	toolGroup.add(ovalButton);
      	toolGroup.add(fillOvalButton);
      	toolGroup.add(rectButton);
      	toolGroup.add(fillRectButton);
      	toolGroup.add(pencilButton);
	toolGroup.add(eraserButton);	  
        //toolGroup.add(airButton);
        toolGroup.add(textButton);
        toolGroup.add(blockButton);
        
      	add (lineButton);
      	add (ovalButton);
      	add (fillOvalButton);
      	add (rectButton);
      	add (fillRectButton);
      	//add (airButton);
      	add (pencilButton);
      	add (eraserButton);      	
      	add (chosenButton);
      	add (textButton);
        add (blockButton);
      	add (undoButton);      	
       	setFloatable (false);   // Prevents to move the toolbar from one place to another place.
    }
    public int getButton ()
    {
        return currentButton;
    }
    private class ButtonListener implements ActionListener
     {      
      	public  void actionPerformed (ActionEvent event)
      	  {
                 Object source=event.getSource (); // Determines the source of button clicked.
                 if (source == lineButton)
                         currentButton = LINE;
             else if (source == ovalButton)
                          currentButton = OVAL;
                 else if (source == fillOvalButton)
                         currentButton = FILLOVAL;
                 else if (source == rectButton)
                         currentButton = RECT;
                 else if (source == fillRectButton)
                         currentButton = FILLRECT;
                 else if (source == pencilButton)
                         currentButton = PENCIL;
                 else if (source == eraserButton)
                         currentButton = ERASER;
                 else if (source == airButton)
                         currentButton = AIR;
                 else if (source == textButton)
                         currentButton = TEXT;
                 else if (source == blockButton)
                         currentButton = BLOCK;
                 else if (source == undoButton)
                         currentButton = UNDO;
                 else if (source == chosenButton)
                 {
                        Color returnedColor = JColorChooser.showDialog ((Component) event.getSource(),"Set color:",Color.black);
                        if (returnedColor != null)
                                {
                                    Colorbar.currentChosenColor = returnedColor;
                                    int rgbValue = returnedColor.getRed ()   +
                                                   returnedColor.getGreen () +
                                                   returnedColor.getBlue ();
                                    if (rgbValue < 250)
                                        chosenButton.setForeground (Color.white);
                                    else
                                        chosenButton.setForeground (Color.black);
                                        chosenButton.setBackground (Colorbar.currentChosenColor);
                                }
                  }
          }
    }
}
