package chatApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ChatAppGUI extends JFrame {

    private JTextArea  chatBox = new JTextArea("This is where all the chat will appear",30,30);
    private JTextArea  typeBox = new JTextArea("Message",3,35);
    private JButton       send = new JButton("send");
    private JPanel typeAndSend = new JPanel(new FlowLayout(FlowLayout.LEFT));

    public ChatAppGUI(TCPClient client) {

        setPropertiesForChatBox();
        setPropertiesForTypeAndSend();
        setPropertiesForWindow();

        setTitle("Client");
        send.addActionListener((e)->{
            try {
                client.sendMsg(typeBox.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }


    private void setPropertiesForWindow() {
        //adding everything to window
        add(new JScrollPane(chatBox),BorderLayout.PAGE_START);
        add(typeAndSend,BorderLayout.CENTER);
        add(new JLabel("Developed by Tanay Shah"),BorderLayout.PAGE_END);

        //setting the properties of window
        //setTitle("Chatting app test");
        setSize(500,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setPropertiesForTypeAndSend() {
        //Setting properties where user will type the message.
        typeBox.setLineWrap(true);
        typeBox.setWrapStyleWord(true);

        //adding to the GUI
        typeAndSend.add(new JScrollPane(typeBox));
        typeAndSend.add(send);

    }

    private void setPropertiesForChatBox() {
        chatBox.setLineWrap(true);
        chatBox.setWrapStyleWord(true);
        chatBox.setBackground(Color.BLACK);
        chatBox.setForeground(Color.GREEN);
        chatBox.setCaretColor(Color.WHITE);
        chatBox.setEditable(false);
    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient("localhost",6666);
        ChatAppGUI clientWindow = new ChatAppGUI(client);
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
