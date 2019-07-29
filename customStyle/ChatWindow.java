package chatApp.customStyle;

import chatApp.TCPClient;
import chatApp.TCPServer;
import chatApp.appComponents.ChatAppComp;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;

public class ChatWindow {

    public static void setPropertiesForWindow(JFrame window, JTextArea chatBox, JPanel typeAndSend) {
        //creating policy for chatBox text Scrolling.
        JScrollPane chatScroll = new JScrollPane(chatBox,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatScroll.setViewport(MyTextArea.chatViewPort);
        chatScroll.setViewportView(chatBox);

        //adding everything to window
        window.add(chatScroll,BorderLayout.PAGE_START);
        window.add(typeAndSend,BorderLayout.CENTER);
        window.add(new JLabel("Developed by Tanay Shah"),BorderLayout.PAGE_END);

        //setting the properties of window
        window.setSize(500,600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
    }

    public static void receiveMsg(TCPClient client, ChatAppComp components) {

        String msg;
        Highlighter highlighter = components.chatBox.getHighlighter();
        Highlighter.HighlightPainter orangeHighLight = new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE);
        try{
            while(true){
                msg=client.getMsg();
                if(msg.length()!=0) {
                    int len = components.chatBox.getText().length();
                    components.chatBox.append("Server : " + msg + (msg.charAt(msg.length() - 1) != '\n' ? "\n" : "") + "\n");
                    highlighter.addHighlight(len, len + msg.length() + 9, orangeHighLight);
                }
            }
        }
        catch (Exception e){
            //when server window is closed
            JOptionPane.showMessageDialog(null,"Error while getting message from server",
                                        "Server Disconnected",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static void sendMsg(TCPClient client,ChatAppComp components) {
        Highlighter highlighter = components.chatBox.getHighlighter();
        Highlighter.HighlightPainter cyanHighLight = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        try {
            String msg = components.typeBox.getText().trim();
            if(msg.length()!=0 && !msg.equals("\n")){
                client.sendMsg(msg);
                int len = components.chatBox.getText().length();
                components.chatBox.append("Client : " + msg + (msg.charAt(msg.length()-1)!='\n' ? "\n" : "") + "\n");
                highlighter.addHighlight(len,len + msg.length() + 9,cyanHighLight);
            }

            components.typeBox.setText("");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void receiveMsg(TCPServer server, ChatAppComp components) {
        Highlighter highlighter = components.chatBox.getHighlighter();
        Highlighter.HighlightPainter orangeHighLight = new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE);
        String msg;
        try{
            while(true){
                msg = server.getMsg();
                if(msg.length()!=0){
                    int len = components.chatBox.getText().length();
                    components.chatBox.append("Client : " + msg + (msg.charAt(msg.length()-1)!='\n' ? "\n" : "") + "\n");
                    highlighter.addHighlight(len, len + msg.length() + 9, orangeHighLight);
                }
            }
        }
        catch (Exception e){
            //when client window is closed
            JOptionPane.showMessageDialog(null,"Error while getting message from client",
                    "Client Disconnected",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static void sendMsg(TCPServer server,ChatAppComp components) {
        Highlighter highlighter = components.chatBox.getHighlighter();
        Highlighter.HighlightPainter cyanHighLight = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        try {
            String msg = components.typeBox.getText().trim();

            if(msg.length()!=0 && !msg.equals("\n")){
                server.sendMsg(msg);
                int len = components.chatBox.getText().length();
                components.chatBox.append("Server : " + msg + (msg.charAt(msg.length()-1)!='\n' ? "\n" : "") + "\n");
                highlighter.addHighlight(len,len + msg.length() + 9,cyanHighLight);
            }
            components.typeBox.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
