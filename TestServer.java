package chatApp;

import chatApp.appComponents.ChatAppComp;
import chatApp.customStyle.ChatWindow;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class TestServer extends JFrame {

    private ChatAppComp components = new ChatAppComp();

    public TestServer(TCPServer server) {

        setTitle("Server");
        ChatWindow.setPropertiesForWindow(this,components.chatBox,components.typeAndSend);

        components.send.addActionListener(e->ChatWindow.sendMsg(server,components));
        components.typeBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(e.isShiftDown())
                        components.typeBox.append("\n");
                    else
                        ChatWindow.sendMsg(server,components);
                }
            }
        });
        ChatWindow.receiveMsg(server,components);
    }


    public static void main(String[] args) {
        TCPServer server = new TCPServer(6666);
        TestServer serverWindow = new TestServer(server);
        serverWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    server.closeResources();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

}
