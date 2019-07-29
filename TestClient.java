package chatApp;

import chatApp.appComponents.ChatAppComp;
import chatApp.customStyle.ChatWindow;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class TestClient extends JFrame {

    private ChatAppComp components = new ChatAppComp();

    public TestClient(TCPClient client) {

        setTitle("Client");
        ChatWindow.setPropertiesForWindow(this,components.chatBox,components.typeAndSend);

        components.send.addActionListener((e)-> ChatWindow.sendMsg(client,components));
        components.typeBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(e.isShiftDown())
                        components.typeBox.append("\n");
                    else
                        ChatWindow.sendMsg(client,components);
                }
            }
        });

        new Thread(() -> ChatWindow.receiveMsg(client,components),"MsgReceivingThread").start();
    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient("localhost",6666);
        TestClient clientWindow = new TestClient(client);
        clientWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    client.closeResources();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

}
