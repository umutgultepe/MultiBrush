//---------------------------------------------------------------------------------------------------
// Author: Group9  
// FinalFrame class composes a DrawingPanel class object in the constructor and displays it with
// other options in a frame which is made up of JFrame class.
//---------------------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.net.Socket;

public class FinalFrame extends JFrame
{
   private DrawingPanel drawingPanel;
   private Colorbar colors = new Colorbar();
   private Toolbar buttons = new Toolbar ();   
   private JPanel panel = new JPanel();	   	
   private int colorcounter=0,toolcounter=0;  // Counters for determining to add or remove colorbar or toolbar.
   	
   public FinalFrame()
   {
      	super ("Multi Brush");      
     	drawingPanel = new DrawingPanel (buttons,colors);  // A new DrawingPanel object with two parameters.
      	JMenuBar menuBar = createMenuBar ();
      	setJMenuBar (menuBar);       
        // Panel layout arrangement code segments.
     	panel.setLayout (new BorderLayout ());
      	panel.add (drawingPanel,BorderLayout.CENTER);
      	panel.add (buttons,BorderLayout.NORTH);
      	panel.add (colors,BorderLayout.SOUTH);          
      	getContentPane().add(panel);
      	addWindowListener (new WindowCloser());
   }
   //---------------------------MENUBAR & MENUS & MENU ITEMS----------------------------------------------------
   public JMenuBar createMenuBar ()
   {
      MenuListener menuListener = new MenuListener ();
	  //-----------------------------Menu options.--------------------------------------------------------------
      JMenu fileMenu = new JMenu ("File");
      JMenu editMenu = new JMenu ("Edit");
      JMenu viewMenu = new JMenu ("View");
      JMenu colorMenu = new JMenu ("Colors");
      JMenu filterMenu = new JMenu ("Filter");
      JMenu helpMenu = new JMenu ("Help");

      //----------------------------Submenu options.-------------------------------------------------------------
      JMenuItem newMenuItem = new JMenuItem ("New",new ImageIcon("../img/new.gif"));
      newMenuItem.addActionListener (menuListener);
      newMenuItem.setEnabled (true);

      JMenuItem openMenuItem = new JMenuItem ("Open",new ImageIcon("../img/open.gif"));
      openMenuItem.addActionListener (menuListener);
      openMenuItem.setEnabled (true);

      JMenuItem saveMenuItem = new JMenuItem ("Save",new ImageIcon("../img/save.gif"));
      saveMenuItem.addActionListener (menuListener);
      saveMenuItem.setEnabled (true);
      
      JMenuItem exitMenuItem = new JMenuItem ("Exit",new ImageIcon("../img/close.gif"));
      exitMenuItem.addActionListener (menuListener);
      exitMenuItem.setEnabled (true);
      
      fileMenu.add (newMenuItem);
      fileMenu.add (openMenuItem);
      fileMenu.add (saveMenuItem);
      fileMenu.add (exitMenuItem);

      //---------------------------------------------End of File Menu--------------------------------------------
      JMenuItem undoMenuItem = new JMenuItem ("Undo",new ImageIcon("../img/undo.gif"));
      undoMenuItem.addActionListener (menuListener);
      undoMenuItem.setEnabled (true);

      JMenuItem redoMenuItem = new JMenuItem ("Redo",new ImageIcon("../img/redo.gif"));
      redoMenuItem.addActionListener (menuListener);
      redoMenuItem.setEnabled (true);

      JMenuItem clearMenuItem = new JMenuItem ("Clear All",new ImageIcon("../img/clear.gif"));
      clearMenuItem.addActionListener (menuListener);
      clearMenuItem.setEnabled (true);

      editMenu.add (undoMenuItem);
      editMenu.add (redoMenuItem);
      editMenu.add (clearMenuItem);

      //----------------------------------------End of Edit Menu-------------------------------------------------
      JMenuItem toolboxMenuItem = new JMenuItem ("Tool Box",new ImageIcon("../img/tool.gif"));
      toolboxMenuItem.addActionListener(menuListener);
      toolboxMenuItem.setEnabled (true);

      JMenuItem colorboxMenuItem = new JMenuItem ("Color Box",new ImageIcon("../img/cbox.gif"));
      colorboxMenuItem.addActionListener(menuListener);
      colorboxMenuItem.setEnabled (true);

      JMenuItem cursorMenuItem = new JMenuItem ("Cursor Position",new ImageIcon("../img/pos.gif"));
      cursorMenuItem.addActionListener(menuListener);
      cursorMenuItem.setEnabled (true);
      
      viewMenu.add (toolboxMenuItem);
      viewMenu.add (colorboxMenuItem);
      viewMenu.add (cursorMenuItem);

      //--------------------------------------End of View Menu--------------------------------------------------     
      JMenuItem editcolorMenuItem = new JMenuItem ("Edit Colors",new ImageIcon("../img/color.gif"));
      editcolorMenuItem.addActionListener(menuListener);
      editcolorMenuItem.setEnabled (true);
      
      colorMenu.add (editcolorMenuItem);

      //------------------------------------End of Color Menu---------------------------------------------------      
      JMenuItem brightnessMenuItem = new JMenuItem ("Brightness",new ImageIcon("../img/bright.gif"));
      brightnessMenuItem.addActionListener(menuListener);
      brightnessMenuItem.setEnabled (true);
      
      JMenuItem blurMenuItem = new JMenuItem ("Blur",new ImageIcon("../img/blur.gif"));
      blurMenuItem.addActionListener(menuListener);
      blurMenuItem.setEnabled (true);
      
      filterMenu.add (brightnessMenuItem);
      filterMenu.add (blurMenuItem);

      //--------------------------------------End of Filter Menu------------------------------------------------      
      JMenuItem helpMenuItem = new JMenuItem ("Help Topics",new ImageIcon("../img/help.gif"));
      helpMenuItem.addActionListener(menuListener);
      helpMenuItem.setEnabled (true);      
      JMenuItem aboutMenuItem = new JMenuItem ("About Multi Brush",new ImageIcon("../img/license.gif"));
      aboutMenuItem.addActionListener(menuListener);
      aboutMenuItem.setEnabled (true);
      
      helpMenu.add (helpMenuItem);
      helpMenu.add (aboutMenuItem);

      //-----------------------------------------End of Help Menu-----------------------------------------------            	  
      JMenuBar menubar = new JMenuBar ();
      menubar.add (fileMenu);
      menubar.add (editMenu);
      menubar.add (viewMenu);
      menubar.add (colorMenu);
      //menubar.add (filterMenu);
      menubar.add (helpMenu);

      return menubar;
   }

   public class WindowCloser extends WindowAdapter
   {
     	public void windowClosing (WindowEvent event)
      	{
        	System.exit (0);
      	}
   }

  //-------------------------------------------------------------------------------
   public class MenuListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         if (event.getActionCommand().equals ("Exit"))
				System.exit (0);
		 if (event.getActionCommand ().equals ("Open"))
		 		DrawingPanel.displayFile();
		 if(event.getActionCommand().equals ("New"))
                 	    	DrawingPanel.drawnItems.clear();
		 if (event.getActionCommand ().equals ("Edit Colors"))
                 {
                    Color selectedColor=JColorChooser.showDialog((Component) event.getSource(),"Select the color:",Color.black);
                    if (selectedColor != null)  {
               			   int rgbValue=selectedColor.getRed()+
                                                selectedColor.getGreen()+
                                                selectedColor.getBlue();
                    }
                    Colorbar.currentChosenColor=selectedColor;
                  }
      	 if(event.getActionCommand().equals ("Brightness"))	
      	    {
      	    	JFrame frame=new JFrame("Brightness");
      	    	frame.getContentPane().add(new Bright());  // Adding a Bright class object into frame.
      	    	frame.pack();
      	    	frame.setVisible(true);
      	    }      	    
      	 if(event.getActionCommand().equals ("About Magic Brush"))	
      	    {
      	    	About about=new About();
      	    	about.toString();
      	    }   
      	 if(event.getActionCommand().equals ("Help Topics"))	
      	    {
      	    	Help help=new Help();
      	    	help.toString();
      	    } 
      	 if(event.getActionCommand().equals ("Color Box"))
      	    {
      	    	if(colorcounter%2!=0){
      	    	panel.add (colors,BorderLayout.SOUTH);	
      	    	colors.setVisible(true);}      	      	    
      	    	if(colorcounter%2==0){
      	    	colors.setVisible(false);}
      	    	colorcounter++;	  	     
      	    }  
      	 if(event.getActionCommand().equals ("Tool Box"))
      	    {
      	    	if(toolcounter%2!=0){
      	    	panel.add (buttons,BorderLayout.NORTH);	
      	    	buttons.setVisible(true);}      	      	    
      	    	if(toolcounter%2==0){
      	    	buttons.setVisible(false);}
      	    	toolcounter++;	  	     
      	    } 
      	 if(event.getActionCommand().equals ("Clear All"))
      	    	DrawingPanel.drawnItems.clear(); 	       	   	
       }
    }

   
}
