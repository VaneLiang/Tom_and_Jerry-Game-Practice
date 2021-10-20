import java.awt.*;

public class Level extends Rectangle{

    static int SCREEN_WIDTH;
    static int SCREEN_HEIGHT;
    int tom;

    Level(int WIDTH, int HEIGHT){
        Level.SCREEN_WIDTH = WIDTH;
        Level.SCREEN_HEIGHT = HEIGHT;
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Consolas",Font.BOLD,25));
        g.drawString("Level: "+ String.valueOf(tom), 380, g.getFont().getSize());

    }
}