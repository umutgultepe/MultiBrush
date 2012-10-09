/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Umut Cemal Yetgin
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

public class AskIp {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private JButton button2;
    private JFrame frame2;
    private JLabel label2;
    private JPanel panel2;
    String name;

    public AskIp(){
        frame = new JFrame ("Ask IP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        label = new JLabel("Please write the server's ip: ");
        textField = new JTextField(15);
        button = new JButton ("Connect!");
        button2 = new JButton ("No need!");
        button.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setName();  // code to execute when button is pressed
                quit();
            }
        });
        button2.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quit();  // code to execute when button is pressed
            }
        });
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.add(button2);
        frame.add(panel);
        frame.setSize(new Dimension(200,150));
        frame.setVisible(true);

        frame2 = new JFrame("Connection");
        panel2 = new JPanel();
        label2 = new JLabel("Ip is set!");

        panel2.add(label2);
        frame2.add(panel2);
    }

    public void setName(){
        System.out.println("set name" + textField.getText());
        name = textField.getText();
        frame.setVisible(false);
        frame2.setSize(new Dimension(100,50));
        frame2.setVisible(true);
    }

    public String getName(){
        System.out.println("Get name");
        return name;
    }

    public void quit(){
        System.out.println("quit");
        frame.setVisible(false);
    }

}
