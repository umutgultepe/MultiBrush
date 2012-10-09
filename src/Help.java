//---------------------------------------------------------------------------------------------------
// Author: Group9   
// This class combines information about basic attributes of the program into a string to return.
//---------------------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.table.*;

public class Help
 {
   public Help()
   	  {
			String help="<html><body bgcolor=00ffff><font size=2 color=black><p><font"+
						"size=+1 type=arial color=orange<b>Help Topics</b></font></p>"+			            
						"<p>Multi Brush has a menubar with submenus<br>and options for different functions.</p>"+
						"<p>Menubar is settled at the top of the window.</p>"+
		   				"<p>There is also a buttongroup called toolbar.</p>"+
                                                "<p>Both toolbar and colorbar can be made visible <br> or invisible by users.</p>"+
                                                "<p>You can change color by using colorbar buttongroup<br>which contains the most used colors.</p>"+
                                                "<p>Toolbar contains drawing shape items,undo and erase option.</p>"+
						"</font></font></body>/html>";	
			JOptionPane.showMessageDialog(null,help,"Help for Users",JOptionPane.INFORMATION_MESSAGE);
     }
}