package chatApp;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public TCPServer(int port){
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            //JOptionPane.showMessageDialog(null,"Now Open chat window at client side",
                                       // "Waiting for client to Connect",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Server is not working","Connection Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getMsg() throws IOException {
        return dis.readUTF();
    }

    public void sendMsg(String msg) throws IOException {
        dos.writeUTF(msg);
    }

    void closeResources() throws IOException {
        socket.close();
        dis.close();
        dos.close();
        serverSocket.close();
    }
}
