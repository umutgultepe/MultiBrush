
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class sample_server {

    private static int port = 4444, maxConnections = 10, nextClient = 0, clientCount = 0, clId = 0;
    private static boolean[] isConnected;
    private static Socket[] clients;
    private static ArrayList<byte[]> previousData;

    public static void main(String[] args) {
        isConnected = new boolean[maxConnections];
        clients = new Socket[maxConnections];

        previousData = new ArrayList<byte[]>();

        System.out.println("-- Server Started");
        

        try {
            ServerSocket listener = new ServerSocket(port);
            System.out.println(listener.getInetAddress());
            while (clientCount != maxConnections) {

                clients[nextClient] = listener.accept();

                if (DrawingPanel.getClientCount() == 0) {
                    previousData.clear();
                }

                printData();

                doComms conn_c = new doComms(clients[nextClient], nextClient);
                Thread t = new Thread(conn_c);
                t.start();
                clientCount++;

                DrawingPanel.incrementClientCount(1);

                System.out.println("Client Connected at position: " + nextClient);
                nextClient = (nextClient + 1) % maxConnections;
            }
        } catch (SocketException se) {
            System.err.println("Port " + port + " is already in use.");
        } catch (IOException ioe) {
            System.err.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }

    public static void sendDataToAll(byte[] data) {
        try {
            byte[] newByte = new byte[data.length];

            for (int i = 0; i < data.length; i++) {
                newByte[i] = data[i];
            }

            previousData.add(newByte);

            for (int i = 0; i < maxConnections; i++) {
                if (isConnected[i]) {
                    System.out.println("sendData to: " + i);
                    BufferedOutputStream out = new BufferedOutputStream(clients[i].getOutputStream());

                    int dataLength = data.length;

                    out.write(data, 0, dataLength);
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendDataListToAll() {
        try {
            for (int i = 0; i < maxConnections; i++) {
                if (isConnected[i]) {
                    System.out.println("sendData to: " + i);
                    BufferedOutputStream out = new BufferedOutputStream(clients[i].getOutputStream());

                    for (int j = 0; j < previousData.size(); j++) {
                        byte[] data = previousData.get(j);
                        if (j == 0)
                            data[25] = 1;
                        int dataLength = data.length;

                        out.write(data, 0, dataLength);
                        out.flush();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendDataList(int clientIndex) {
        try {
            if (isConnected[clientIndex]) {
                System.out.println("sendPreviousData to: " + clientIndex);
                BufferedOutputStream out = new BufferedOutputStream(clients[clientIndex].getOutputStream());

                for (int i = 0; i < previousData.size(); i++) {
                    byte[] data = previousData.get(i);
                    int dataLength = data.length;

                    out.write(data, 0, dataLength);
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setConnected(int index, boolean b) {
        isConnected[index] = b;
    }

    public static void clientDisconnected(int index) {
        isConnected[index] = false;
        clientCount--;

        DrawingPanel.incrementClientCount(-1);
    }

    public static void printData() {
//        System.err.println("--");
        for (int i = 0; i < previousData.size(); i++) {
            String hexData = "";

            hexData += "TypeIndex: " + (int) previousData.get(i)[0] + " ";
            hexData += "Color: " + (new Color(byteToInt(previousData.get(i), 1)).toString()) + " ";
            hexData += "(" + (int) byteToInt(previousData.get(i), 5) + ", ";
            hexData += (int) byteToInt(previousData.get(i), 9) + ") ";
//                        if (length > 13) {
            hexData += "(" + (int) byteToInt(previousData.get(i), 13) + ", ";
            hexData += (int) byteToInt(previousData.get(i), 17) + ")";
            hexData += " id: " + (int) byteToInt(previousData.get(i), 21);
//                        }

            System.out.println(hexData);
        }
//        System.err.println("--");
    }

    public static int getNextId() {
        return nextClient;
    }

    public static ArrayList<byte[]> getPreviousData() {
        return previousData;
    }

    private static int byteToInt(byte[] byteA, int startIndex) {
        return ((byteA[startIndex] & 0xFF) << 24) | ((byteA[startIndex + 1] & 0xFF) << 16)
                | ((byteA[startIndex + 2] & 0xFF) << 8) | (byteA[startIndex + 3] & 0xFF);
    }
}

class doComms implements Runnable {

    private Socket client;
    private int index;

    doComms(Socket client, int index) {
        this.client = client;
        this.index = index;

        sample_server.setConnected(index, true);

        sample_server.sendDataList(index);


    }

    public void run() {
        try {
            BufferedInputStream in = new BufferedInputStream(client.getInputStream());

            int length;
            byte data[] = new byte[1024];

            while ((length = in.read(data)) != -1) {
                if (length == 0) {
                    break;
                } else {
                    String hexData = "";

                    hexData += length + " - ";

                    hexData += "TypeIndex: " + (int) data[0] + " ";
                    hexData += "Color: " + (new Color(byteToInt(data, 1)).toString()) + " ";
                    hexData += "(" + (int) byteToInt(data, 5) + ", ";
                    hexData += (int) byteToInt(data, 9) + ") ";
//                        if (length > 13) {
                    hexData += "(" + (int) byteToInt(data, 13) + ", ";
                    hexData += (int) byteToInt(data, 17) + ")";
                    hexData += " id: " + (int) byteToInt(data, 21);
//                        }
                    System.out.println(hexData);

                    if ((int) data[0] == 8) { // Equals to Undo
                        for (int i = sample_server.getPreviousData().size() - 1; i >= 0; i--) {
                            int clientId = byteToInt(sample_server.getPreviousData().get(i), 21);
                            System.out.println("UNDO ID: " + clientId);
                            if (clientId == byteToInt(data, 21)) {
                                sample_server.getPreviousData().remove(i);
                                break;
                            }
                        }

                        sample_server.sendDataListToAll();
                    } else {
                        sample_server.sendDataToAll(data);
                    }
                }


            }

            client.close();
        } catch (IOException ioe) {
            System.out.println("Client Disconnected at position: " + index);
            sample_server.clientDisconnected(index);
            //ioe.printStackTrace();
        }
    }

    private int byteToInt(byte[] byteA, int startIndex) {
        return ((byteA[startIndex] & 0xFF) << 24) | ((byteA[startIndex + 1] & 0xFF) << 16)
                | ((byteA[startIndex + 2] & 0xFF) << 8) | (byteA[startIndex + 3] & 0xFF);
    }
}
