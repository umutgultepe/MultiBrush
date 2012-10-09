//----------------------------------------------------------------------------------------------------
// Author: Group9   
// Bright class is defined for composing new objects which will arrange brightness of an image.
//----------------------------------------------------------------------------------------------------
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Bright extends JPanel
{
	private JPanel controls;      // a panel which contains slider objects.
	private JSlider brightness;   // a slider which can arrange brightness in numerical values.
	private JLabel label;
	
	public Bright()
	{
			brightness=new JSlider(JSlider.HORIZONTAL,0,100,0);
			brightness.setMajorTickSpacing(25);
			brightness.setMinorTickSpacing(10);
			brightness.setPaintTicks(true);
			brightness.setPaintLabels(true);
			brightness.setAlignmentX(Component.LEFT_ALIGNMENT);		
			SliderListener slider=new SliderListener();
			brightness.addChangeListener(slider);		
			label=new JLabel("Brightness: 0");
			label.setAlignmentX(Component.LEFT_ALIGNMENT);		
			controls =new JPanel();
			BoxLayout layout=new BoxLayout(controls,BoxLayout.Y_AXIS);
			controls.setLayout(layout);
			controls.add(label);
			controls.add(brightness);
			controls.add(Box.createRigidArea(new Dimension(0,20)));
		
		    add(controls);		
	}	
	private class SliderListener implements ChangeListener 
	  {
			private int br;		
			public void stateChanged(ChangeEvent event)
		  	  {
					 br=brightness.getValue();
			    	label.setText("Brightness: "+br);
		  	  }	
	  }
}