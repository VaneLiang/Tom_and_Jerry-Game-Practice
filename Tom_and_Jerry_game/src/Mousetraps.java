import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

public class Mousetraps extends Rectangle{
    int id;
    Mousetraps(int x, int y, int width, int height, int id){

        super(x,y,width,height);
        this.id = id;
    }
   /* public void draw(Graphics g){
        g.setColor(Color.gray);
        g.fillOval(x,y,height,width);

    }*/

    public void drawImage(Graphics g, ImageObserver imageObserver) {
        Image image = Toolkit.getDefaultToolkit().getImage("images/mousetrap.png");
        g.drawImage(image, x, y, width, height, imageObserver);
    }
}
