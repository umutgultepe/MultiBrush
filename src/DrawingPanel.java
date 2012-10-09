//---------------------------------------------------------------------------------------------------
// Author: Group9  
// DrawingPanel class takes a group of color buttons and a group of tool buttons as parameters
// and uses ArrayList class to keep drawn item objects easily and make processes with these
// drawn objects easier such as undoing and clearing works.
//---------------------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {

    public static final int DRAWAREA = 500;  // Specifies the size of drawing area.
    private Toolbar buttonbar;
    private Colorbar colorbar;
    public static ArrayList drawnItems, blockers;  // Keeps drawn items in an ArrayList class.
    private Shape currentShape;          // Represents current shape.
    private Point basePoint, currentPoint;
    private int xPosition, yPosition, lastShape;
    private Color chosenColor;
    private ImageIcon image;
    private boolean blockUsed, removeBlock;
    public static String name;
    public static ArrayList<Point> linePoints;
    //AskIp ipReturner;

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private JButton button2;
    private JFrame frame2;
    private JLabel label2;
    private JPanel panel2;

    //Constructor of program takes two Object parameters.
    public DrawingPanel(Toolbar toolbuttons, Colorbar colorbuttons) {
        name = (".gif");
        image = new ImageIcon(name);
        drawnItems = new ArrayList();
        linePoints = new ArrayList<Point>();
        /**
         *
         */
        externalDrawnItems = new ArrayList();
        blockers = new ArrayList();
        buttonbar = toolbuttons;
        colorbar = colorbuttons;
        lastShape = Toolbar.LINE;
        setBackground(Color.white);
        setMinimumSize(new Dimension(DRAWAREA, DRAWAREA));
        setPreferredSize(new Dimension(DRAWAREA, DRAWAREA));
        addMouseListener(this);
        addMouseMotionListener(this);
        blockUsed = false;
        removeBlock = false;

        ///////////////////////////////////////
        
        frame = new JFrame ("Ask IP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        label = new JLabel("Please write the server's ip: ");
        textField = new JTextField(15);
        button = new JButton ("Connect!");
        button2 = new JButton ("No need!");
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.add(button2);
        frame.add(panel);
        frame.setSize(new Dimension(200,150));
        frame.setVisible(true);

        frame2 = new JFrame("Connection");
        panel2 = new JPanel();
        label2 = new JLabel("");

        panel2.add(label2);
        frame2.add(panel2);
        frame2.setSize(new Dimension(100,100));
        button.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ip = textField.getText();
                port = 4444;
                isConnected = false;
                //System.out.println("Connect: " + (isConnected = (connect(ip, port))));

                Random r = new Random();
                id = r.nextInt();
                System.out.println(id);

                isConnected = connect(ip, port);
                if(isConnected)
                    label2.setText("Connection Established");
                else
                    label2.setText("Connection Failed");
                frame.setVisible(false);
                frame2.setVisible(true);
            }
        });
        button2.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("quit");
                frame.setVisible(false);
            }
        });
        
        //ipReturner = new AskIp();
        //ipReturner.getName();
                
        /*
        ip = "139.179.1.4";
        port = 4444;
        isConnected = false;
        //System.out.println("Connect: " + (isConnected = (connect(ip, port))));
        
        Random r = new Random();
        id = r.nextInt();
        System.out.println(id);

        isConnected = connect(ip, port);
        */
    }

    public void paintComponent(Graphics page) // Processes with Graphics object.
    {
        super.paintComponent(page);
        image = new ImageIcon(name);
        image.paintIcon(this, page, 0, 0);
        if (drawnItems.size() > 0) {
            for (int index = 0; index <= drawnItems.size() - 1; index++) {
                ((Shape) drawnItems.get(index)).draw(page);
            }
        }

        for (int index = 0; index <= externalDrawnItems.size() - 1; index++) {
            ((Shape) externalDrawnItems.get(index)).draw(page);
        }

        // Shows the cursor position coordinates at a specific location.  	 	
        page.drawString(("Cursor Position: (" + posX() + "," + posY() + ")"), 350, 500);
        repaint();
    }

    public void mousePressed(MouseEvent event) {
        int currentAction = buttonbar.getButton();  // By using an integer determines which button is active.
        chosenColor = Colorbar.currentChosenColor;
        basePoint = event.getPoint();

        switch (currentAction) {
            case Toolbar.LINE:
                Cursor lineCursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("../img/cursor.png")).getImage(), new Point(0, 0), "");
                setCursor(lineCursor);
                currentShape = new Line(chosenColor, basePoint, basePoint);
                lastShape = Toolbar.LINE;
                drawnItems.add(currentShape);
                linePoints.clear();
                break;

            case Toolbar.OVAL:
                Cursor ovalCursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("../img/cursor.png")).getImage(), new Point(0, 0), "");
                setCursor(ovalCursor);
                currentShape = new Oval(chosenColor, basePoint, 0, 0);
                lastShape = Toolbar.OVAL;
                drawnItems.add(currentShape);
                break;

            case Toolbar.FILLOVAL:
                Cursor fovalCursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("../img/cursor.png")).getImage(), new Point(0, 0), "");
                setCursor(fovalCursor);
                currentShape = new FillOval(chosenColor, basePoint, 0, 0);
                lastShape = Toolbar.FILLOVAL;
                drawnItems.add(currentShape);
                break;

            case Toolbar.RECT:
                Cursor rectCursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("../img/cursor.png")).getImage(), new Point(0, 0), "");
                setCursor(rectCursor);
                currentShape = new Rect(chosenColor, basePoint, 0, 0);
                lastColor = chosenColor;
                lastShape = Toolbar.RECT;
                drawnItems.add(currentShape);
                break;

            case Toolbar.FILLRECT:
                Cursor frectCursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("../img/cursor.png")).getImage(), new Point(0, 0), "");
                setCursor(frectCursor);
                currentShape = new FillRect(chosenColor, basePoint, 0, 0);
                lastShape = Toolbar.FILLRECT;
                drawnItems.add(currentShape);
                break;

            case Toolbar.PENCIL:  // Here is a code segment which is useful for changing cursor image for particular drawings.
                Cursor pencilCursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("../img/cursor.png")).getImage(), new Point(0, 0), "");
                setCursor(pencilCursor);

                currentShape = new FillRect(chosenColor, basePoint, 4, 4);
                lastShape = Toolbar.PENCIL;
                drawnItems.add(currentShape);

                if (!checkDrawable(Toolbar.PENCIL, basePoint, new Point(basePoint.x + 4, basePoint.y + 4))) {
                    drawnItems.remove(drawnItems.size() - 1);
                    repaint();
                } else {
                    sendData(Toolbar.FILLRECT, chosenColor,basePoint, new Point(basePoint.x + 4, basePoint.y + 4));
                }
                break;

            case Toolbar.ERASER:
                Cursor eraserCursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("../img/ec.jpg")).getImage(), new Point(0, 0), "");
                setCursor(eraserCursor);
                currentShape = new FillOval(Color.WHITE, basePoint, 0, 0);
                lastShape = Toolbar.ERASER;
                drawnItems.add(currentShape);
                break;

            case Toolbar.AIR:
                Cursor airCursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("../img/pos.gif")).getImage(), new Point(0, 0), "");
                setCursor(airCursor);
                currentShape = new AirBrush(chosenColor, basePoint);
                lastShape = Toolbar.AIR;
                drawnItems.add(currentShape);
                break;

            //?
            case Toolbar.BLOCK:
                if (!blockUsed) {
                    Cursor blockCursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("../img/cursor.png")).getImage(), new Point(0, 0), "");
                    setCursor(blockCursor);
                    currentShape = new Blocker(Color.GRAY, basePoint, 0, 0);
                    lastShape = Toolbar.BLOCK;
                    drawnItems.add(currentShape);
                } else {
                    removeBlock = true;
                    
                }
                break;

            case Toolbar.UNDO:
                Cursor undoCursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("../img/pos.gif")).getImage(), new Point(0, 0), "");
                setCursor(undoCursor);
                lastShape = Toolbar.UNDO;
                if (!drawnItems.isEmpty())
                    drawnItems.remove(drawnItems.size() - 1);

                break;
        }

        repaint();
    }
    //------Selecting and Displaying any file from computer memory locations.------

    public static void displayFile() {
        JFileChooser chooser = new JFileChooser();
        int st = chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        String name = file.getName();
    }
    //------------------------------------------------------------------------------

    public void mouseDragged(MouseEvent event) {
        int currentAction = buttonbar.getButton();
        currentPoint = event.getPoint();
        chosenColor = colorbar.getChosenColor();

        switch (currentAction) {
            case Toolbar.LINE:
                //if(!blockUsed)  //FOR DEMO
                ((Line) currentShape).setEndPoint(currentPoint);
                break;

            case Toolbar.OVAL:
                ((Oval) currentShape).setShape(basePoint, currentPoint);
                break;

            case Toolbar.FILLOVAL:
                ((FillOval) currentShape).setShape(basePoint, currentPoint);
                break;

            case Toolbar.RECT:
                ((Rect) currentShape).setShape(basePoint, currentPoint);
//                    sendData(3, lastColor, basePoint, currentPoint);
                break;

            case Toolbar.FILLRECT:
                ((FillRect) currentShape).setShape(basePoint, currentPoint);
                break;

            case Toolbar.PENCIL:
                currentShape = new FillRect(chosenColor, currentPoint, 4, 4);
                lastShape = Toolbar.PENCIL;
                drawnItems.add(currentShape);

                if (!checkDrawable(Toolbar.PENCIL, currentPoint, new Point(currentPoint.x + 4, currentPoint.y + 4))) {
                    drawnItems.remove(drawnItems.size() - 1);
                    repaint();
                } else {
                    sendData(Toolbar.FILLRECT, chosenColor,currentPoint, new Point(currentPoint.x + 4, currentPoint.y + 4));
                }
                
                break;

            case Toolbar.ERASER:
                currentShape = new FillOval(Color.WHITE, currentPoint, 20, 20);
                lastShape = Toolbar.ERASER;
                drawnItems.add(currentShape);

                sendData(Toolbar.ERASER, Color.WHITE, currentPoint,
                        new Point(currentPoint.x + 20, currentPoint.y + 20));
                break;

            case Toolbar.UNDO:
  /*              lastShape = Toolbar.UNDO;
                drawnItems.remove(drawnItems.size() - 1);*/
                break;

            case Toolbar.BLOCK:
                if (!blockUsed && !removeBlock)
                    ((Blocker) currentShape).setShape(basePoint, currentPoint);
                break;
        }

        repaint();
    }

    // Shows the current coordinates of the cursor when mouse is moving.
    public void mouseMoved(MouseEvent event) {
        xPosition = event.getX();
        yPosition = event.getY();
    }

    public int posX() {
        return xPosition;
    }

    public int posY() {
        return yPosition;
    }

    public void mouseClicked(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
        int currentAction = buttonbar.getButton();
        Point curPoint = event.getPoint();

        Point baseP = getBasePoint(basePoint, curPoint);
        Point endP = getEndPoint(basePoint, curPoint);

        switch (currentAction) {
            case Toolbar.LINE:
                baseP = getLineBasePoint(basePoint, currentPoint);
                endP = getLineEndPoint(basePoint, currentPoint);
                setLinePoints(baseP, endP);
                if (!checkDrawable(Toolbar.LINE, baseP, endP)) {
                    drawnItems.remove(drawnItems.size() - 1);
                    repaint();
                } else {
                    sendData(Toolbar.LINE, chosenColor, basePoint, currentPoint);
                }
                break;

            case Toolbar.OVAL:
                if (!checkDrawable(Toolbar.OVAL, baseP, endP)) {
                    drawnItems.remove(drawnItems.size() - 1);
                    repaint();
                } else {
                    sendData(Toolbar.OVAL, chosenColor, basePoint, curPoint);
                }
                break;

            case Toolbar.FILLOVAL:
                if (!checkDrawable(Toolbar.FILLOVAL, baseP, endP)) {
                    drawnItems.remove(drawnItems.size() - 1);
                    repaint();
                } else {
                    sendData(Toolbar.FILLOVAL, chosenColor, basePoint, curPoint);
                }
                break;

            case Toolbar.RECT:
                if (!checkDrawable(Toolbar.RECT, baseP, endP)) {
                    drawnItems.remove(drawnItems.size() - 1);
                    repaint();
                } else {
                    sendData(Toolbar.RECT, chosenColor, baseP, endP);
                }
                
                break;

            case Toolbar.FILLRECT:
                if (!checkDrawable(Toolbar.FILLRECT, baseP, endP)) {
                    drawnItems.remove(drawnItems.size() - 1);
                    repaint();
                } else {
                    sendData(Toolbar.FILLRECT, chosenColor, baseP, endP);
                }
                break;

            case Toolbar.PENCIL:
                //sendData(Toolbar.PENCIL, chosenColor, basePoint, curPoint);
                break;

            case Toolbar.ERASER:
                /*
                sendData(Toolbar.ERASER, Color.WHITE, basePoint,
                        new Point(basePoint.x + 20, basePoint.y + 20));
                 *
                 */
                break;

            case Toolbar.UNDO:
                sendData(Toolbar.UNDO, Color.WHITE, baseP, endP);
                 /**
                 * /
                 */
                break;

            case Toolbar.BLOCK:
                if (!removeBlock) {
                    if (!blockUsed) {
                        if (!checkDrawable(Toolbar.BLOCK, baseP, endP)) {
                            drawnItems.remove(drawnItems.size() - 1);
                            repaint();
                        } else {
                            ((Blocker) currentShape).setShape (baseP, endP);

                            sendData(Toolbar.BLOCK, Color.GRAY, baseP, endP);

                            blockUsed = true;
                        }
                        

//                        currentShape = (Shape) drawnItems.get(drawnItems.indexOf(currentShape));
                    } else {

                    }
                } else {
                    removeBlock = false;
                    blockUsed = false;
                    
                    for (int i = 0; i < drawnItems.size(); i++) {
                        if (((Shape) drawnItems.get(i)).isBlocker()) {
                            sendData(-1, Color.GRAY, ((Blocker) drawnItems.get(i)).upperLeft,
                                    new Point(((Blocker) drawnItems.get(i)).upperLeft.x + ((Blocker) drawnItems.get(i)).height,
                                              ((Blocker) drawnItems.get(i)).upperLeft.y + ((Blocker) drawnItems.get(i)).width
                                    ));
                            drawnItems.remove(i);
                            
                            break;
                        }
                    }

                    repaint();
                }
                /*
                ((Blocker) currentShape).setShape (basePoint, currentPoint);
                 *
                 */
                break;
        }
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent event) {
    }

    private Point getBasePoint(Point b, Point e) {
        int minX = b.x;
        if (e.x < b.x)
            minX = e.x;
        int minY = b.y;
        if (e.y < b.y)
            minY = e.y;

        return new Point(minX, minY);
    }

    private Point getEndPoint(Point b, Point e) {
        int minX = b.x;
        if (e.x > b.x)
            minX = e.x;
        int minY = b.y;
        if (e.y > b.y)
            minY = e.y;

        return new Point(minX, minY);
    }

    private Point getLineBasePoint(Point b, Point e) {
        if (b.x <= e.x)
            return b;
        else
            return e;
    }
    
    private Point getLineEndPoint(Point b, Point e) {
        if (b.x > e.x)
            return b;
        else
            return e;
    }

    private void setLinePoints(Point b, Point e) {
        linePoints.clear();
        if (e.x == b.x){
            for (int i = b.y; i <= e.y; i++) {
                linePoints.add(new Point(b.x, i));
            }
            return;
        }

        double angle = Math.atan(((double)(e.y - b.y)) / (e.x - b.x));

        //if (angle >= 45)// {
            for (int i = b.x; i <= e.x; i++) {
                linePoints.add(new Point(i, (int) ((Math.tan(angle) * (i-b.x)) + b.y)));
            }
  /*      } else {
            for (int i = b.y; i <= e.y; i++) {
                linePoints.add(new Point((int) ((1 / Math.tan(i)) * i) + b.x, i));
            }
        }*/
    }

    private boolean checkDrawable(int item, Point baseP, Point endP) {
        boolean isBlockUsed = (blockers.size() > 0) ? true : false;

        if (!isBlockUsed)
            return true;

        for (int i = 0; i < blockers.size(); i++) {
            Blocker theBlock = (Blocker) blockers.get(i);

            Point blockerBase = theBlock.upperLeft;
            Point blockerEnd = new Point(theBlock.upperLeft.x + theBlock.width, theBlock.upperLeft.y + theBlock.height);

            if (item == Toolbar.LINE) {
                for (Point p: linePoints) {
                    if (!checkPointDrawable(blockerBase, blockerEnd, p))
                        return false;
                }
            } else {
                if (baseP.x <= blockerBase.x && baseP.y <= blockerBase.y &&
                    endP.x >= blockerEnd.x && endP.y >= blockerEnd.y)
                    return false;

                if (!checkDrawableStraightLine(blockerBase, blockerEnd, baseP, new Point(baseP.x, endP.y)))
                    return false;
                if (!checkDrawableStraightLine(blockerBase, blockerEnd, new Point(endP.x, baseP.y), endP))
                    return false;
                if (!checkDrawableStraightLine(blockerBase, blockerEnd, baseP, new Point(endP.x, baseP.y)))
                    return false;
                if (!checkDrawableStraightLine(blockerBase, blockerEnd, new Point(baseP.x, endP.y), endP))
                    return false;
            }
        }
        return true;
    }

    private boolean checkDrawableStraightLine(Point bB, Point bE, Point lB, Point lE) {
        if (lB.x == lE.x) {
            for (int i = lB.y; i <= lE.y; i++) {
                if (!checkPointDrawable(bB, bE, new Point(lB.x, i)))
                    return false;
            }
        } else {
            for (int i = lB.x; i <= lE.x; i++) {
                if (!checkPointDrawable(bB, bE, new Point(i, lB.y)))
                     return false;
            }
        }
        return true;
    }

    private boolean checkPointDrawable(Point bB, Point bE, Point p) {
        if (p.x >= bB.x && p.x <= bE.x && p.y >= bB.y && p.y <= bE.y)
            return false;
        return true;
    }

    private void sendData(int shapeType, Color color, Point basePoint, Point endPoint) {
        if (isConnected) {
            byte shapeTypeByte = (byte) (shapeType);

            byte[] colorByte = new byte[4];
            colorByte[0] = (byte) (color.getRGB() >> 24);
            colorByte[1] = (byte) ((color.getRGB() << 8) >> 24);
            colorByte[2] = (byte) ((color.getRGB() << 16) >> 24);
            colorByte[3] = (byte) ((color.getRGB() << 24) >> 24);

            byte[] baseX = new byte[4];
            baseX[0] = (byte) (basePoint.x >> 24);
            baseX[1] = (byte) ((basePoint.x << 8) >> 24);
            baseX[2] = (byte) ((basePoint.x << 16) >> 24);
            baseX[3] = (byte) ((basePoint.x << 24) >> 24);

            byte[] baseY = new byte[4];
            baseY[0] = (byte) (basePoint.y >> 24);
            baseY[1] = (byte) ((basePoint.y << 8) >> 24);
            baseY[2] = (byte) ((basePoint.y << 16) >> 24);
            baseY[3] = (byte) ((basePoint.y << 24) >> 24);

            byte[] endX = new byte[4];
            byte[] endY = new byte[4];
            //if (shapeType != Toolbar.LINE) {

            endX[0] = (byte) (endPoint.x >> 24);
            endX[1] = (byte) ((endPoint.x << 8) >> 24);
            endX[2] = (byte) ((endPoint.x << 16) >> 24);
            endX[3] = (byte) ((endPoint.x << 24) >> 24);


            endY[0] = (byte) (endPoint.y >> 24);
            endY[1] = (byte) ((endPoint.y << 8) >> 24);
            endY[2] = (byte) ((endPoint.y << 16) >> 24);
            endY[3] = (byte) ((endPoint.y << 24) >> 24);
            //}

            byte[] idByte = new byte[4];

            idByte[0] = (byte) (id >> 24);
            idByte[1] = (byte) ((id << 8) >> 24);
            idByte[2] = (byte) ((id << 16) >> 24);
            idByte[3] = (byte) ((id << 24) >> 24);

            byte[] totalByte = new byte[26];

            totalByte[0] = shapeTypeByte;

            totalByte[1] = colorByte[0];
            totalByte[2] = colorByte[1];
            totalByte[3] = colorByte[2];
            totalByte[4] = colorByte[3];

            totalByte[5] = baseX[0];
            totalByte[6] = baseX[1];
            totalByte[7] = baseX[2];
            totalByte[8] = baseX[3];

            totalByte[9] = baseY[0];
            totalByte[10] = baseY[1];
            totalByte[11] = baseY[2];
            totalByte[12] = baseY[3];

//            if (shapeType != Toolbar.LINE) {
            totalByte[13] = endX[0];
            totalByte[14] = endX[1];
            totalByte[15] = endX[2];
            totalByte[16] = endX[3];

            totalByte[17] = endY[0];
            totalByte[18] = endY[1];
            totalByte[19] = endY[2];
            totalByte[20] = endY[3];

            totalByte[21] = idByte[0];
            totalByte[22] = idByte[1];
            totalByte[23] = idByte[2];
            totalByte[24] = idByte[3];

            totalByte[25] = 0;


//            }

            //           send(totalByte);

            try {
                out = new BufferedOutputStream(socket.getOutputStream());

                int dataLength = totalByte.length;

                out.write(totalByte, 0, dataLength);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private Color lastColor;
    private String ip;
    private int port;
    private Socket socket;
    private boolean isConnected;
    private Thread connectionThread, receiveThread;
    private BufferedOutputStream out;
    private BufferedInputStream in;
    private static ArrayList externalDrawnItems;
    private static int clientCount = 0;
    
    private int id;

    public static void incrementClientCount(int num) {
        clientCount += num;

        if (clientCount < 0) {
            clientCount = 0;
        }

        System.out.println("cl:" + clientCount);
    }

    public static int getClientCount() {
        return clientCount;
    }

//    public static void incrementId() {
//        statId += 1;
//    }
//
//    public static int getId() {
//        return statId;
//    }

    public boolean connect(String ip, int port) {
        connectionThread = new Thread(new ConnectClass(ip, port));
        connectionThread.start();

        try {
            connectionThread.join();
        } catch (Exception e) {
            isConnected = false;
        }

        if (isConnected) {
            receiveThread = new Thread(new ReceiveClass());
            receiveThread.start();
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

    /**
     * Receive Class is an inner class which implements the method to receive
     * data from the server.
     */
    protected class ReceiveClass implements Runnable {

        /**
         * Default constructor for Receive Class
         */
        public ReceiveClass() {
            in = null;
        }

        /**
         * Receives the continuously sent data from the server.
         */
        public void run() {
            try {
                in = new BufferedInputStream(socket.getInputStream());

                int length = -1;
                byte[] data = new byte[1024];
                while ((length = in.read(data)) != -1) {
                    if (length == 0) {
                        break;
                    }

                    /**
                    String hexData = "";

                    hexData += length + " - ";

                    hexData += "TypeIndex: " + (int) data[0] + " ";
                    hexData += "Color: " + (new Color(byteToInt(data, 1)).toString()) + " ";
                    hexData += "(" + (int) byteToInt(data, 5) + ", ";
                    hexData += (int) byteToInt(data, 9) + ") ";
                    //                        if (length > 13) {
                    hexData += "(" + (int) byteToInt(data, 13) + ", ";
                    hexData += (int) byteToInt(data, 17) + ")";
                    //                        }

                     */
                    Color color = new Color(byteToInt(data, 1));
                    Point base = new Point(byteToInt(data, 5), byteToInt(data, 9));
                    Point end = new Point(byteToInt(data, 13), byteToInt(data, 17));
                    int baseId = byteToInt(data, 21);
                    int clear = (int) data[25];

                    if (clear == 1)
                        externalDrawnItems.clear();

//                    if (baseId != id) {
                        int type = (int) data[0];
                        switch (type) {
                            case Toolbar.LINE:
                                Line line = new Line(color, base, end);
                                externalDrawnItems.add(line);
                                break;

                            case Toolbar.OVAL:
                                Oval oval = new Oval(color, end, 0, 0);
                                oval.setShape(base, end);
                                externalDrawnItems.add(oval);
                                break;

                            case Toolbar.FILLOVAL:
                                FillOval foval = new FillOval(color, end, 0, 0);
                                foval.setShape(base, end);
                                externalDrawnItems.add(foval);
                                break;

                            case Toolbar.RECT:
                                Rect rect = new Rect(color, base, 0, 0);
                                rect.setShape(base, end);

                                externalDrawnItems.add(rect);
                                break;

                            case Toolbar.FILLRECT:
                                FillRect fillrect = new FillRect(color, base, 0, 0);
                                fillrect.setShape(base, end);

                                externalDrawnItems.add(fillrect);
                                break;

                            case Toolbar.PENCIL:
                                FillRect pencil = new FillRect(color, base, 0, 0);
                                pencil.setShape(base, end);

                                externalDrawnItems.add(pencil);
                                break;

                            case Toolbar.ERASER:
                                FillRect eraser = new FillRect(color, base, 0, 0);
                                eraser.setShape(base, end);

                                externalDrawnItems.add(eraser);
                                break;

                            case Toolbar.UNDO:
                                /*    lastShape = Toolbar.UNDO;
                                drawnItems.remove(drawnItems.size() - 1);
                                 *
                                 */
                                break;

                            case Toolbar.BLOCK:
                                Blocker blocker = new Blocker(color, base, 0, 0);
                                blocker.setShape(base, end);
                                if (baseId != id){
                                    externalDrawnItems.add(blocker);
                                    blockers.add(blocker);
                                }
                                break;

                            case -1:
                                System.out.println("Got: -1 BLockers: " + blockers.size() + "All: " + externalDrawnItems.size() );

                                for (Object b: blockers)
                                    if (((Blocker) b).upperLeft.x == base.x &&
                                    ((Blocker) b).upperLeft.y == base.y) {
                                        blockers.remove(b);
                                        break;
                                    }
                                for (Object b: externalDrawnItems)
                                    if (((Shape) b).isBlocker())
                                        if (((Blocker) b).upperLeft.x == base.x &&
                                            ((Blocker) b).upperLeft.y == base.y) {
                                            externalDrawnItems.remove(b);
                                            break;
                                        }
                                System.out.println("Got: -1 BLockers: " + blockers.size() + "All: " + externalDrawnItems.size() );

                                break;
                        }
                        /**
                         *
                        if ((int) data[0] == Toolbar.LINE) {
                        Line line = new Line(color, base, end);
                        externalDrawnItems.add(line);
                        } else {
                        FillRect shape = new FillRect(color, base, 0, 0);
                        shape.setShape(base, end);

                        externalDrawnItems.add(shape);
                        }
                         */
                        repaint();
//                    }

//                    System.out.println(hexData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private int byteToInt(byte[] byteA, int startIndex) {
            return ((byteA[startIndex] & 0xFF) << 24) | ((byteA[startIndex + 1] & 0xFF) << 16)
                    | ((byteA[startIndex + 2] & 0xFF) << 8) | (byteA[startIndex + 3] & 0xFF);
        }
    }
}


