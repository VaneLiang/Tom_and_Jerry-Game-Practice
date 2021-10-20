import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;

public class Cheese extends Rectangle{
    Cheese(int x, int y, int width, int height){
        super(x,y,width,height);
    }
    public void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval(x,y,height,width);
    }

}
