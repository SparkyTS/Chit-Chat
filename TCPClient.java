package chatApp;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    TCPClient(String host,int port) {
        try {
            socket = new Socket(host,port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Could not connect to the server!","Connection Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    void closeResources() throws IOException {
        socket.close();
        dos.close();
        dis.close();
    }

    public void sendMsg(String msg) throws IOException {
        dos.writeUTF(msg);
    }

    public String getMsg() throws IOException {
        return dis.readUTF();
    }
}
