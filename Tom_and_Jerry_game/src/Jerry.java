import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Jerry extends Rectangle{

    int xVelocity;
    int yVelocity;
    int speed = 5;
    Image img;




    Jerry(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //super(x,y,width,height);







    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
            setYDirection(-speed);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
            setYDirection(speed);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
            setXDirection(-speed);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
            setXDirection(speed);
            move();
        }

    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
            setYDirection(0);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
            setYDirection(0);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
            setXDirection(0);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
            setXDirection(0);
            move();
        }

    }

    public void setXDirection(int xDirection) {
        xVelocity = xDirection;
    }
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move(){
        y += yVelocity;
        x += xVelocity;

    }
    /*public void draw(Graphics g){

        g.setColor(Color.ORANGE);
        g.fillRect(x,y,width,height);

    }*/

    public void drawImage(Graphics g, ImageObserver imageObserver) {
        Image image = Toolkit.getDefaultToolkit().getImage("images/jerry.png");
        g.drawImage(image, x, y, width, height, imageObserver);
    }
}
