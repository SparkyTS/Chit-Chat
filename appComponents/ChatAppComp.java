package chatApp.appComponents;

import chatApp.customStyle.MyTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ChatAppComp {
    public MyTextArea chatBox;
    //protected static JTextArea  chatBox = new JTextArea("This is where all the chat will appear",30,30);
    public JTextArea typeBox;
    public JButton       send;
    public JPanel typeAndSend;

    public ChatAppComp(){
        //JTextArea  chatBox = new JTextArea("This is where all the chat will appear",30,30);
            chatBox =  new MyTextArea(30,100, "D:\\My CODEs\\Java Projects\\GUI_BASED\\src\\chatApp\\customStyle\\panda.jpg");
            typeBox = new JTextArea("Message",3,35);
               send = new JButton("send");
        typeAndSend = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setPropertiesForChatBox();
        setPropertiesForTypeAndSend();
    }

    private void setPropertiesForTypeAndSend() {
        //Setting properties where user will type the message.
        typeBox.setLineWrap(true);
        typeBox.setWrapStyleWord(true);
        typeBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                typeBox.setBorder(BorderFactory.createLineBorder(Color.CYAN));
            }

            @Override
            public void focusLost(FocusEvent e) {
                typeBox.setBorder(null);
            }
        });

        //adding to the GUI
        typeAndSend.add(new JScrollPane(typeBox,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        typeAndSend.add(send);

    }

    private void setPropertiesForChatBox() {
        chatBox.setLineWrap(true);
        chatBox.setWrapStyleWord(true);
        chatBox.setBackground(new Color(1,1,1, (float) 0.01));//transparent,so background picture can be seen.
        //chatBox.setBackground(Color.BLACK);
        chatBox.setForeground(Color.BLUE);
        //chatBox.setCaretColor(Color.WHITE);
        chatBox.setEditable(false);
    }
}
