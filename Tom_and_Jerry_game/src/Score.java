import java.awt.*;

public class Score extends Rectangle{

    static int SCREEN_WIDTH;
    static int SCREEN_HEIGHT;
    int jerry;
    //int tom;

    Score(int WIDTH, int HEIGHT){
        Score.SCREEN_WIDTH = WIDTH;
        Score.SCREEN_HEIGHT = HEIGHT;
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Consolas",Font.BOLD,25));
        g.drawString("Score: "+ String.valueOf(jerry), 200, g.getFont().getSize());

    }
}
