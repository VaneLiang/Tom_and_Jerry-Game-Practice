import java.awt.*;
import java.util.Random;

public class Tom extends Rectangle{

    Random random;
    int xVelocity;
    int yVelocity;
    int speed = 7;
    boolean level1 = true;
    boolean level2 = false;

    //Level level;

    Tom(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();



        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0){
            randomXDirection--;
            setXDirection(randomXDirection * speed);
        }
        else{
            randomXDirection++;
            setXDirection(randomXDirection * speed);
        }

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0){
            randomYDirection--;
            setYDirection(randomYDirection * speed);
        }
        else{
            randomYDirection++;
            setYDirection(randomYDirection * speed);
        }

    }

    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
        //move();

    }

    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
        //move();
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x,y,height,width);
    }
}
