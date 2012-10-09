//---------------------------------------------------------------------------
// Author: Group9 
// Multi Brush class is the main driver class of this project which composes
// a MultiFrame object into a frame and displays it.
//---------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.AudioClip;
import java.net.URL;

public class MultiBrush extends JPanel
{
  public static void main (String[] args)
    {       
    		
       		FinalFrame frame = new FinalFrame ();
       		frame.pack ();
       		frame.setVisible (true);
       		frame.setResizable (false);
                /*
       		AudioClip music;
    		URL url=null;   	  
   	  	try
      		{
        		url = new URL ("file", "localhost", "hitchcock.wav");
     		} 
      		catch (Exception exception) {}    
      		music = JApplet.newAudioClip (url);		
      		music.play();*/
    }
}
