import java.awt.*;

public class Lives extends Rectangle{

    static int SCREEN_WIDTH;
    static int SCREEN_HEIGHT;
    int jerry;
    //int tom;

    Lives(int WIDTH, int HEIGHT){
        Lives.SCREEN_WIDTH = WIDTH;
        Lives.SCREEN_HEIGHT = HEIGHT;
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Consolas",Font.BOLD,25));
        g.drawString("Lives: "+ String.valueOf(jerry), 20, g.getFont().getSize());

    }
}
