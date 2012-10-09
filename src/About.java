//---------------------------------------------------------------------------------------------------
// Author: Group9 
// This class combines information about the owners of the program into a string to return.
//---------------------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.table.*;

public class About 
{
   public About() 
   	   {
			String about="<html><body bgcolor=00ffff><font size=2 color=black><p><font size=+1"+
						"type=arial color=orange<b>MULTI BRUSH</b></font></p><p></p>"+
                                                "<p>MultiBrush allows multiple users, up to 4,</p>"+
                                                "<p>draw on the same image file at the same time</p><p></p>"+
						"<p>This program designed and<br>implemented by </p>"+
						"<p>Mehmet Dogac Gulnerman<br>Fehmi Berkay Aksoy<br>Umut Cemal Yetgin<br>" +
						"Umut Gultepe<br>Sahin Hoke</p><br><br>"+
						"</font></font></body>/html>";	
			JOptionPane.showMessageDialog(null,about,"About Multi Brush",JOptionPane.INFORMATION_MESSAGE);
       }
}