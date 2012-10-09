//---------------------------------------------------------------------------------------------------
// Author: Group9   
//---------------------------------------------------------------------------------------------------

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
 
public class Opener extends JPanel
{
    BufferedImage image;
    Rectangle r;
    double scale, inc, min;
 
    public Opener()
    {
        loadImage();
        scale = 1.0;
        inc = 0.01;
        min = 0.25;
        ImageMover mover = new ImageMover(this);
        addMouseListener(mover);
        addMouseMotionListener(mover);

        ip = "127.0.0.1";
        port = 80;
        isConnected = false;
        System.out.println("Connect: " + connect(ip, port));
    }
 
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        if(r == null)
            init();
        AffineTransform at = AffineTransform.getTranslateInstance(r.x, r.y);
        at.scale(scale, scale);
        g2.drawRenderedImage(image, at);
    }
 
    private void init()
    {
        int w = getWidth();
        int h = getHeight();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        r = new Rectangle(imageWidth, imageHeight);
        r.x = (w - imageWidth)/2;
        r.y = (h - imageHeight)/2;
    }
 
    private void loadImage()
    {
        String fileName = "pi.jpg";
        try
        {
            URL url = getClass().getResource(fileName);
            image = ImageIO.read(url);
        }
        catch(MalformedURLException mue)
        {
            System.err.println("url: " + mue.getMessage());
        }
        catch(IOException ioe)
        {
            System.err.println("read: " + ioe.getMessage());
        }
    }
 
    private JPanel getUIPanel()
    {
        final JButton
            larger  = new JButton("larger"),
            smaller = new JButton("smaller");
        ActionListener l = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JButton button = (JButton)e.getSource();
                if(button == larger)
                    scale += inc;
                if(button == smaller)
                    scale -= scale - inc > min ? inc : 0;
                repaint();
            }
        };
        larger.addActionListener(l);
        smaller.addActionListener(l);
        JPanel panel = new JPanel();
        panel.add(larger);
        panel.add(smaller);
        return panel;
    }
 
    public static void main(String[] args)
    {
        Opener iv = new Opener();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(iv.getUIPanel(), "North");
        f.add(iv);
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }

    private String ip;
    private int port;
    private Socket socket;
    private boolean isConnected;
    private Thread connectionThread, receiveThread;

    public boolean connect(String ip, int port) {
        connectionThread = new Thread(new ConnectClass(ip, port));
        connectionThread.start();
System.out.println("Connect.");
        try {
            connectionThread.join();
        } catch (Exception e) {
            isConnected = false;
        }

        if (isConnected) {
            System.out.println("Connected..");
            /*
            receiveThread = new Thread(new ReceiveClass());
            receiveThread.start();
             *
             */
        }

        return isConnected;
    }

    protected class ConnectClass implements Runnable {

        /**
         * Default constructor for ConnectClass which initializes the IP-Address
         * and the port of the server.
         *
         * @param ip The IP-Address of the server
         * @param port The port of the server.
         */
        public ConnectClass(String ipx, int portx) {
            ip = ipx;
            port = portx;

            socket = null;
        }

        /**
         * Connects to the server with given ip and port.
         */
        public void run() {
            try {
                socket = new Socket(ip, port);
                isConnected = true;
            } catch (Exception e) {
                isConnected = false;
                System.out.println("Connect Failed");
            }

        }
    }
}
 
class ImageMover extends MouseInputAdapter
{
    Opener iv;
    Point offset;
    boolean dragging;
 
    public ImageMover(Opener iv)
    {
         this.iv = iv;
         offset = new Point();
         dragging = false;
    }
 
    public void mousePressed(MouseEvent e)
    {
        Point p = e.getPoint();
        if(iv.r.contains(p))
        {
            offset.x = p.x - iv.r.x;
            offset.y = p.y - iv.r.y;
            dragging = true;
        }
    }
 
    public void mouseReleased(MouseEvent e)
    {
        dragging = false;
    }
 
    public void mouseDragged(MouseEvent e)
    {
        if(dragging)
        {
            iv.r.x = e.getX() - offset.x;
            iv.r.y = e.getY() - offset.y;
            iv.repaint();
        }
    }
    
    
}

