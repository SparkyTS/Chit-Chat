package chatApp.customStyle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyTextArea extends JTextArea {

    private BufferedImage image;
    private Image img;
    public static JViewport chatViewPort;
    public MyTextArea(int a, int b,String imgPath) {
        super(a,b);
        try{
            image = ImageIO.read(new File(imgPath));
            img = image.getScaledInstance(500,550,Image.SCALE_SMOOTH);
        } catch(IOException e) {
            System.out.println(e.toString());
        }

        chatViewPort = new JViewport()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                if(img != null) g.drawImage(img, 0,0,this.getWidth(),this.getHeight(),this);
            }
        };

        setOpaque(false);

    }
}
