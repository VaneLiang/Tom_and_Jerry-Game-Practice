import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;

public class Mousetraps extends Rectangle{
    int id;
    Mousetraps(int x, int y, int width, int height, int id){

        super(x,y,width,height);
        this.id = id;
    }
    public void draw(Graphics g){
        g.setColor(Color.gray);
        g.fillOval(x,y,height,width);

    }

}
