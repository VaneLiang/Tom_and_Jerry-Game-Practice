import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.Timer;

public class GamePanel extends JPanel implements Runnable{
    Image image;
    Graphics graphics;
    Jerry jerry;
    Tom tom;
    Tom_big tom_big;
    Cheese cheese;
    Lives lives;
    Score score;
    Level level;
    boolean gameOver;
    boolean win;
    int cheeseX;
    int cheeseY;

    Random random;
    Thread gameThread;

    static final int SCREEN_WIDTH = 1200;
    static final int SCREEN_HEIGHT = 900;
    static final Dimension SCREEN_SIZE = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);


    int respawn_jerryX = 100;
    int respawn_jerryY = 300;
    int respawn_tomX = 900;
    int respawn_tomY = 300;
    static final int UNIT_SIZE = 50;
    static final int BIG_UNIT_SIZE = UNIT_SIZE * 3;

    Mousetraps mousetrap1;
    Mousetraps mousetrap2;
    Mousetraps mousetrap3;
    Mousetraps mousetrap4;
    int num_of_mousetrap = 4;
    int mousetrap_x1 = respawn_tomX;
    int mousetrap_y1 = respawn_tomY;
    int mousetrap_x2;
    int mousetrap_y2;
    int mousetrap_x3;
    int mousetrap_y3;
    int mousetrap_x4;
    int mousetrap_y4;
    boolean set_trap1 = false;
    boolean set_trap2 = false;
    boolean set_trap3 = false;
    boolean set_trap4 = false;

    int score_left_to_win;






    GamePanel() {
        level = new Level(SCREEN_WIDTH,SCREEN_HEIGHT);
        level.tom = 1;
        newJerry();
        newTom();
        score = new Score(SCREEN_WIDTH, SCREEN_HEIGHT);
        lives = new Lives(SCREEN_WIDTH,SCREEN_HEIGHT);
        lives.jerry = 5;
        //level = new Level(SCREEN_WIDTH,SCREEN_HEIGHT);
        //level.tom = 1;
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
        newCheese();
        newTomBig();
        System.out.println("Game Start! Level1!");


    }




    public void newCheese(){
        cheeseX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        cheeseY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        cheese = new Cheese(cheeseX,cheeseY,UNIT_SIZE,UNIT_SIZE);
    }

    public void newJerry(){
        jerry = new Jerry(respawn_jerryX,respawn_jerryY,UNIT_SIZE,UNIT_SIZE);

    }

    public void newTom() {
        random = new Random();
        tom = new Tom(respawn_tomX,respawn_tomY,UNIT_SIZE*2,UNIT_SIZE*2);
    }

    public void newTomBig(){
        random = new Random();
        tom_big = new Tom_big(respawn_tomX, respawn_tomY, UNIT_SIZE*3, UNIT_SIZE*3);
    }

    public void checkMousetraps(){
        if(level.tom <= 2){
            if(num_of_mousetrap == 4 && (mousetrap_x1 - tom.x >= 500 || tom.x - mousetrap_x1 >= 500 || mousetrap_y1 - tom.y >= 500 || tom.y - mousetrap_y1 >= 500) ){
                mousetrap_x1 = tom.x;
                mousetrap_y1 = tom.y;
                set_trap1 = true;
                newMousetraps();
            }
            if(num_of_mousetrap == 3 && (tom.x - mousetrap_x1 >= 500 || mousetrap_x1 - tom.x >= 500 || mousetrap_y1 - tom.y >= 500 || tom.y - mousetrap_y1 >= 500)){
                set_trap2 = true;
                mousetrap_x2 = tom.x;
                mousetrap_y2 = tom.y;
                newMousetraps();
            }
            if(num_of_mousetrap == 2 && (tom.x - mousetrap_x2 >= 500 || mousetrap_x2 - tom.x >= 500 || mousetrap_y2 - tom.y >= 500 || tom.y - mousetrap_y2 >= 500)){
                set_trap3 = true;
                mousetrap_x3 = tom.x;
                mousetrap_y3 = tom.y;
                newMousetraps();
            }
            if(num_of_mousetrap == 1 && (tom.x - mousetrap_x3 >= 500 || mousetrap_x3 - tom.x >= 500 || mousetrap_y3 - tom.y >= 500 || tom.y - mousetrap_y3 >= 500)){
                set_trap4 = true;
                mousetrap_x4 = tom.x;
                mousetrap_y4 = tom.y;
                newMousetraps();
            }
        }

        else if(level.tom ==3 ){
            if(num_of_mousetrap == 4 && (mousetrap_x1 - tom_big.x >= 500 || tom_big.x - mousetrap_x1 >= 500 || mousetrap_y1 - tom_big.y >= 500 || tom_big.y - mousetrap_y1 >= 500) ){
                mousetrap_x1 = tom_big.x;
                mousetrap_y1 = tom_big.y;
                set_trap1 = true;
                newMousetraps();
            }
            if(num_of_mousetrap == 3 && (tom_big.x - mousetrap_x1 >= 500 || mousetrap_x1 - tom_big.x >= 500 || mousetrap_y1 - tom_big.y >= 500 || tom_big.y - mousetrap_y1 >= 500)){
                set_trap2 = true;
                mousetrap_x2 = tom_big.x;
                mousetrap_y2 = tom_big.y;
                newMousetraps();
            }
            if(num_of_mousetrap == 2 && (tom_big.x - mousetrap_x2 >= 500 || mousetrap_x2 - tom_big.x >= 500 || mousetrap_y2 - tom_big.y >= 500 || tom_big.y - mousetrap_y2 >= 500)){
                set_trap3 = true;
                mousetrap_x3 = tom_big.x;
                mousetrap_y3 = tom_big.y;
                newMousetraps();
            }
            if(num_of_mousetrap == 1 && (tom_big.x - mousetrap_x3 >= 500 || mousetrap_x3 - tom_big.x >= 500 || mousetrap_y3 - tom_big.y >= 500 || tom_big.y - mousetrap_y3 >= 500)){
                set_trap4 = true;
                mousetrap_x4 = tom_big.x;
                mousetrap_y4 = tom_big.y;
                newMousetraps();
            }
        }

    }

    public void newMousetraps(){

        if(set_trap1 == true && set_trap2 == false && set_trap3 ==false && set_trap4 == false){
            mousetrap1 = new Mousetraps(mousetrap_x1, mousetrap_y1,UNIT_SIZE,UNIT_SIZE,1);
            num_of_mousetrap--;

        }
        if(set_trap2 == true && set_trap1 == true && set_trap3 == false && set_trap4 == false){

            mousetrap2 = new Mousetraps(mousetrap_x2, mousetrap_y2,UNIT_SIZE,UNIT_SIZE,2);
            num_of_mousetrap--;


        }
        if(set_trap3 == true){

            mousetrap3 = new Mousetraps(mousetrap_x3, mousetrap_y3,UNIT_SIZE,UNIT_SIZE,3);
            num_of_mousetrap--;

        }
        if(set_trap4 == true){

            mousetrap4 = new Mousetraps(mousetrap_x4, mousetrap_y4,UNIT_SIZE,UNIT_SIZE,4);
            num_of_mousetrap--;

        }


    }



    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

        if(gameOver){
            g.clearRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
            gameThread.stop();
            System.out.println("Game Over! Your final Score is: "+ score.jerry + ", You need "+ score_left_to_win + " left to Win!");
            g.setColor(Color.RED);
            g.setFont(new Font("MV Boli", Font.PLAIN, 60));
            g.drawString("GAME OVER!", 400, 200);
        }
        if(win){
            g.clearRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
            gameThread.stop();
            System.out.println("Congratulations! You Win!");
            g.setColor(Color.RED);
            g.setFont(new Font("MV Boli", Font.PLAIN, 60));
            g.drawString("WINNER WINNER!", 300, 200);
        }

    }

    public void draw(Graphics g){
        jerry.drawImage(g,this);
        if(level.tom <=2){
            tom.drawImage(g,this);
        }

        if(level.tom == 3){
            //System.out.println("draw Big Tom");
            tom_big.drawImage(g,this);
        }
        cheese.drawImage(g,this);
        lives.draw(g);
        score.draw(g);
        level.draw(g);
        if(set_trap1){
            mousetrap1.drawImage(g,this);
        }
        if(set_trap2){
            mousetrap2.drawImage(g,this);
        }
        if(set_trap3){
            mousetrap3.drawImage(g,this);
        }
        if(set_trap4){
            mousetrap4.drawImage(g,this);

        }


    }

    public void move(){
        jerry.move();
        tom.move();
        if(level.tom == 3){
            tom_big.move();
        }
    }


    public void checkCollision(){
        /////Check Jerry's collision with Tom ///////////
        if(jerry.intersects(tom) && level.tom <=2){
            jerry.x = respawn_jerryX;
            jerry.y = respawn_jerryY;
            tom.x = respawn_tomX;
            tom.y = respawn_tomY;
            lives.jerry--;
            set_trap1 = false;
            set_trap2 = false;
            set_trap3 = false;
            set_trap4 = false;
            num_of_mousetrap = 4;
            System.out.println("Life Lost!");
        }
        /////Check Jerry's collision with Big Tom ///////////
        if(jerry.intersects(tom_big) && level.tom ==3){
            jerry.x = respawn_jerryX;
            jerry.y = respawn_jerryY;
            tom_big.x = respawn_tomX;
            tom_big.y = respawn_tomY;
            lives.jerry--;
            set_trap1 = false;
            set_trap2 = false;
            set_trap3 = false;
            set_trap4 = false;
            num_of_mousetrap = 4;
            System.out.println("Life Lost!");
        }

        /////Check jerry's collision with cheese //////////
        if(jerry.intersects(cheese)){
            newCheese();
            score.jerry++;
            System.out.println("Scored!");
            if(score.jerry == 5){
                level.tom = 2;
                System.out.println("Level2!");
                jerry.x = respawn_jerryX;
                jerry.y = respawn_jerryY;
                tom.x = respawn_tomX;
                tom.y = respawn_tomY;
            }
            if(score.jerry == 15){
                level.tom = 3;
                System.out.println("Level3!");
                jerry.x = respawn_jerryX;
                jerry.y = respawn_jerryY;
                tom_big.x = respawn_tomX;
                tom_big.y = respawn_tomY;
            }

        }

        /////Check jerry's collision with screen border //////
        if(jerry.x  <= 0){
            jerry.x = 0;
        }
        if(jerry.x + UNIT_SIZE >= SCREEN_WIDTH){
            jerry.x = SCREEN_WIDTH - UNIT_SIZE;
        }
        if(jerry.y  <= 0){
            jerry.y = 0;
        }
        if(jerry.y + UNIT_SIZE >= SCREEN_HEIGHT){
            jerry.y = SCREEN_HEIGHT - UNIT_SIZE;
        }

        /////Check Tom's collision with screen border ////////
        if(tom.x  <= 0){
            tom.setXDirection(-tom.xVelocity);
        }
        if(tom.x + UNIT_SIZE >= SCREEN_WIDTH){
            tom.setXDirection(-tom.xVelocity);
        }
        if(tom.y  <= 0){
            tom.setYDirection(-tom.yVelocity);
        }
        if(tom.y + UNIT_SIZE >= SCREEN_HEIGHT){
            tom.setYDirection(-tom.yVelocity);
        }
        /////Check BigBig Tom's collision with screen border ////////
        if(tom_big.x  <= 0){
            tom_big.setXDirection(-tom_big.xVelocity);
        }
        if(tom_big.x + BIG_UNIT_SIZE >= SCREEN_WIDTH){
            tom_big.setXDirection(-tom_big.xVelocity);
        }
        if(tom_big.y  <= 0){
            tom_big.setYDirection(-tom_big.yVelocity);
        }
        if(tom_big.y + BIG_UNIT_SIZE >= SCREEN_HEIGHT){
            tom_big.setYDirection(-tom_big.yVelocity);
        }

        /////Check Jerry's collision with Mousetraps  && Check Cheese's collision with Mousetraps, make sure it won't respawn on a trap ////////
        if(set_trap1){
            if(jerry.intersects(mousetrap1)){
                jerry.x = respawn_jerryX;
                jerry.y = respawn_jerryY;
                tom.x = respawn_tomX;
                tom.y = respawn_tomY;
                tom_big.x = respawn_tomX;
                tom_big.y = respawn_tomY;
                lives.jerry--;
                System.out.println("Life Lost!");
                set_trap1 = false;
                set_trap2 = false;
                set_trap3 = false;
                set_trap4 = false;
                num_of_mousetrap = 4;
            }
            if(cheese.intersects(mousetrap1)){
                newCheese();
            }
        }
        if(set_trap2){
            if(jerry.intersects(mousetrap2)){
                jerry.x = respawn_jerryX;
                jerry.y = respawn_jerryY;
                tom.x = respawn_tomX;
                tom.y = respawn_tomY;
                tom_big.x = respawn_tomX;
                tom_big.y = respawn_tomY;
                lives.jerry--;
                System.out.println("Life Lost!");
                set_trap1 = false;
                set_trap2 = false;
                set_trap3 = false;
                set_trap4 = false;
                num_of_mousetrap = 4;
            }
            if(cheese.intersects(mousetrap2)){
                newCheese();
            }
        }
        if(set_trap3){
            if(jerry.intersects(mousetrap3)){
                jerry.x = respawn_jerryX;
                jerry.y = respawn_jerryY;
                tom.x = respawn_tomX;
                tom.y = respawn_tomY;
                tom_big.x = respawn_tomX;
                tom_big.y = respawn_tomY;
                lives.jerry--;
                System.out.println("Life Lost!");
                set_trap1 = false;
                set_trap2 = false;
                set_trap3 = false;
                set_trap4 = false;
                num_of_mousetrap = 4;
            }
            if(cheese.intersects(mousetrap3)){
                newCheese();
            }
        }
        if(set_trap4){
            if(jerry.intersects(mousetrap4)){
                jerry.x = respawn_jerryX;
                jerry.y = respawn_jerryY;
                tom.x = respawn_tomX;
                tom.y = respawn_tomY;
                tom_big.x = respawn_tomX;
                tom_big.y = respawn_tomY;
                lives.jerry--;
                System.out.println("Life Lost!");
                set_trap1 = false;
                set_trap2 = false;
                set_trap3 = false;
                set_trap4 = false;
                num_of_mousetrap = 4;
            }
            if(cheese.intersects(mousetrap4)){
                newCheese();
            }
        }


    }


    public void checkLevel(){

        if(level.tom == 2){
            checkMousetraps();

            //System.out.println("Level2!");

        }
        if(level.tom == 3){
            checkMousetraps();
        }
    }


    public void checkGameOver(){
        if(lives.jerry == 0){
            gameOver = true;
            score_left_to_win = 20- score.jerry;
        }
    }
    public void checkWin(){
        if(score.jerry == 20){
            win = true;
        }
    }



    public void run(){
        ////// game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >=1){
                move();
                checkCollision();
                checkGameOver();
                checkWin();
                //checkMousetraps();
                checkLevel();

                repaint();
                delta--;

            }

        }
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            jerry.keyPressed(e);

        }
        public void keyReleased(KeyEvent e){
            jerry.keyReleased(e);
        }

    }





    }



