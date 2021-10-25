import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;


public class Cheese extends Rectangle{
    Cheese(int x, int y, int width, int height){
        super(x,y,width,height);
        //Image img;
        //img = ImageIO.read(new File("src/jerry.png"));
    }
    /*public void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval(x,y,height,width);
    }*/

    public void drawImage(Graphics g, ImageObserver imageObserver) {
        Image image = Toolkit.getDefaultToolkit().getImage("images/cheese.jpg");
        g.drawImage(image, x, y, width, height, imageObserver);
    }
}
