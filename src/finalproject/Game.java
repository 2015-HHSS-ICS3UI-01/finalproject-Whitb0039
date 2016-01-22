/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author whitb0039
 */
// make sure you rename this class if you are doing a copy/paste
public class Game extends JComponent implements KeyListener, MouseMotionListener, MouseListener {

    // Height and Width of our game
    static final int WIDTH = 1280;
    static final int HEIGHT = 924;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    //set what screen is being displayed
    //menu 
    //press enter flash
    int pressEnterCounter = 0;
    Font menu = new Font("Helvetica", Font.BOLD, 80);
    //angle variable
    double triX = 0;
    double triY = 0;
    double triAngle = 0;
    //shooting int
    double explosion = 0;
    int x = 100;
    int y = 500;
    //screen variable
    int level = 0;
    //mouse varibales
    int mouseX = 640;
    int mouseY = 0;
    boolean buttonPressed = false;
    boolean runGame = true;
    boolean runMenu = true;
    //block
    ArrayList<Rectangle> background = new ArrayList<>();
    ArrayList<Rectangle> blocks = new ArrayList<>();
    ArrayList<Rectangle> verticalBlocks = new ArrayList<>();
    ArrayList<Rectangle> pillars = new ArrayList<>();
    //enemies
    ArrayList<Rectangle> enemies = new ArrayList<>();
    int currentLevelHP = 1;
    int numberOfEnemies = 0;
    ArrayList enemyHP = new ArrayList();
    //ArrayList<BufferedImage> enemies = new ArrayList<>();
    //another player
    Rectangle player = new Rectangle(630, 430, 50, 50);
    int lives = 6;
    int moveX = 0;
    int moveY = 0;
    boolean inAir = false;
    int frameCount = 0;
    int gravity = 1;
    //direction of character
    boolean E = false;
    boolean NE = false;
    boolean N = true;
    boolean NW = false;
    boolean W = false;
    boolean SW = false;
    boolean S = false;
    boolean SE = false;
    //keyboard variables
    boolean enter = false;
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    boolean jump = false;
    //import images
    BufferedImage playerImgS = imageHelper.loadImage("CharacterImgS.png");
    BufferedImage playerImgSE = imageHelper.loadImage("CharacterImgSE.png");
    BufferedImage playerImgE = imageHelper.loadImage("CharacterImgE.png");
    BufferedImage playerImgNE = imageHelper.loadImage("CharacterImgNE.png");
    BufferedImage playerImgN = imageHelper.loadImage("CharacterImgN.png");
    BufferedImage playerImgNW = imageHelper.loadImage("CharacterImgNW.png");
    BufferedImage playerImgW = imageHelper.loadImage("CharacterImgW.png");
    BufferedImage playerImgSW = imageHelper.loadImage("CharacterImgSW.png");
    BufferedImage smallEnemyS = imageHelper.loadImage("smallEnemyS.png");
    BufferedImage smallEnemySE = imageHelper.loadImage("smallEnemySE.png");
    BufferedImage smallEnemyE = imageHelper.loadImage("smallEnemyE.png");
    BufferedImage smallEnemyNE = imageHelper.loadImage("smallEnemyNE.png");
    BufferedImage smallEnemyN = imageHelper.loadImage("smallEnemyN.png");
    BufferedImage smallEnemyNW = imageHelper.loadImage("smallEnemyNW.png");
    BufferedImage smallEnemyW = imageHelper.loadImage("smallEnemyW.png");
    BufferedImage smallEnemySW = imageHelper.loadImage("smallEnemySW.png");
    BufferedImage crosshair = imageHelper.loadImage("Crosshair.png");
    BufferedImage cobble = imageHelper.loadImage("CobbleBackground.png");
    BufferedImage waterCobble = imageHelper.loadImage("WaterCobble.png");
    BufferedImage woodCobbleVertical = imageHelper.loadImage("WoodCobble_Vertical.png");
    BufferedImage woodCobbleHorizontal = imageHelper.loadImage("WoodCobble_Horizontal.png");
    BufferedImage bullet = imageHelper.loadImage("Bullet.png");
    BufferedImage explosion1 = imageHelper.loadImage("explosion1.png");
    BufferedImage explosion2 = imageHelper.loadImage("explosion2.png");
    BufferedImage explosion3 = imageHelper.loadImage("explosion3.png");
    BufferedImage explosion4 = imageHelper.loadImage("explosion4.png");
    BufferedImage explosion5 = imageHelper.loadImage("explosion5.png");
    BufferedImage smallEnemy = imageHelper.loadImage("smallEnemy.png");
    BufferedImage pillar = imageHelper.loadImage("pillar.png");
    BufferedImage blank = imageHelper.loadImage("blank.png");
    BufferedImage pressEnter = imageHelper.loadImage("pressEnter.png");

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 


        for (int i = 0; i < 1400; i = i + 50) {
            for (int u = 0; u < 1400; u = u + 50) {
                g.drawImage(cobble, i, u, this);
            }
        }
        if (level == 0) {
            for (Rectangle wall : blocks) {
                g.drawImage(waterCobble, wall.x, wall.y, this);
            }

            if (pressEnterCounter < 10) {
                g.drawImage(pressEnter, 400, 300, this);
            } else if (pressEnterCounter > 20) {
                pressEnterCounter = 0;
            }
            pressEnterCounter++;
        }else if(level == -1){
            g.            
        }



        if (buttonPressed) {
            if (explosion == 1) {
                g.drawImage(explosion1, x, y, this);
            } else if (explosion == 2) {
                g.drawImage(explosion2, x, y, this);
            } else if (explosion == 3) {
                g.drawImage(explosion3, x, y, this);
            } else if (explosion == 4) {
                g.drawImage(explosion4, x, y, this);
            } else if (explosion == 5) {
                g.drawImage(explosion5, x, y, this);
            }

        }

        for (Rectangle block : enemies) {
            String smallEnemyDirection = direction(block.x, block.y, player.x, player.y);
            if (smallEnemyDirection.equals("NE")) {
                g.drawImage(smallEnemyNE, block.x, block.y, 50, 50, this);
                block.x = block.x + 2;
                block.y = block.y - 2;
            } else if (smallEnemyDirection.equals("N")) {
                g.drawImage(smallEnemyN, block.x, block.y, 50, 50, this);
                block.y = block.y - 3;
            } else if (smallEnemyDirection.equals("NW")) {
                g.drawImage(smallEnemyNW, block.x, block.y, 50, 50, this);
                block.x = block.x - 2;
                block.y = block.y - 2;
            } else if (smallEnemyDirection.equals("S")) {
                g.drawImage(smallEnemyS, block.x, block.y, 50, 50, this);
                block.y = block.y + 3;
            } else if (smallEnemyDirection.equals("SW")) {
                g.drawImage(smallEnemySW, block.x, block.y, 50, 50, this);
                block.x = block.x - 2;
                block.y = block.y + 2;
            } else if (smallEnemyDirection.equals("SE")) {
                g.drawImage(smallEnemySE, block.x, block.y, 50, 50, this);
                block.x = block.x + 2;
                block.y = block.y + 2;
            } else if (smallEnemyDirection.equals("E")) {
                g.drawImage(smallEnemyE, block.x, block.y, 50, 50, this);
                block.x = block.x + 3;
            } else if (smallEnemyDirection.equals("W")) {
                g.drawImage(smallEnemyW, block.x, block.y, 50, 50, this);
                block.x = block.x - 3;
            }
        }



        String playerDirection = direction(player.x, player.y, mouseX, mouseY);
        //draw player according to direction
        if (playerDirection == "NE") {
            g.drawImage(playerImgNE, player.x, player.y, 50, 50, this);
        } else if (playerDirection == "N") {
            g.drawImage(playerImgN, player.x, player.y, 50, 50, this);
        } else if (playerDirection == "NW") {
            g.drawImage(playerImgNW, player.x, player.y, 50, 50, this);
        } else if (playerDirection == "S") {
            g.drawImage(playerImgS, player.x, player.y, 50, 50, this);
        } else if (playerDirection == "SW") {
            g.drawImage(playerImgSW, player.x, player.y, 50, 50, this);
        } else if (playerDirection == "SE") {
            g.drawImage(playerImgSE, player.x, player.y, 50, 50, this);
        } else if (playerDirection == "E") {
            g.drawImage(playerImgE, player.x, player.y, 50, 50, this);
        } else if (playerDirection == "W") {
            g.drawImage(playerImgW, player.x, player.y, 50, 50, this);
        }

        if (level >= 1) {
            for (Rectangle block : pillars) {
                g.drawImage(pillar, block.x, block.y, this);
            }
        } else if (level == 0) {
            g.drawImage(pillar, 50, 5, this);
            g.drawImage(pillar, 1050, 5, this);
            g.drawImage(pillar, 50, 700, this);
            g.drawImage(pillar, 1050, 700, this);
        }


        //draw the press enter text

        g.drawString("" + lives, 400, 500);

        //draw crosshair
        g.drawImage(crosshair, x, y, 50, 50, this);

        // GAME DRAWING ENDS HERE
    }

    public void run() {
        //end of initial things to do

        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;









        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 

            if (lives <= 0) {
                level = -1;
            }

            numberOfEnemies = level * 10;
            if (level == 0) {
                menuScreen();
                if (enter) {
                    level = 1;
                }
            } else if (level >= 1) {
                mainGame();
            } else if (level == -1) {
                gameOver();
            }
            if (enemies.isEmpty() && level >= 1) {
                enemies.clear();
                level = level + 1;
                runGame = true;

            }
            //find the coordinates for the cross hair
            x = mouseX - 25;
            y = mouseY - 25;

            //if user is pressing left
            if (left) {
                moveX = -5; //move 5 pixels left
            } else if (right) { //if user is pressing right
                moveX = +5; //move 5 pixels to the right
            } else { //if nothing is being pressed
                moveX = 0; //dont move on the X axis
            }
            if (up) { //if user is pressing up
                moveY = -5; //move up 5 pixels
            } else if (down) { //if the user is pressing down
                moveY = +5; //move down 5 pixels
            } else { //if nothing is being pressed
                moveY = 0; //dont move
            }

            //player cannot run off screen
            if (player.x < 0) {
                player.x = 0;
            } else if (player.x > 1200) {
                player.x = 1200;
            } else if (player.y < 0) {
                player.y = 0;
            } else if (player.y > 800) {
                player.y = 800;
            }

            //move player to new location
            player.x = player.x + moveX;
            player.y = player.y + moveY;




            Rectangle shoot = new Rectangle(mouseX - 8, mouseY - 8, 16, 16);

            //shooting enemies
            if (buttonPressed) {
                Iterator<Rectangle> it = enemies.iterator();
                while (it.hasNext()) {
                    Rectangle block = it.next();
                    if (shoot.intersects(block)) {

                        it.remove();


                    }
                }


            }

            //player vs block collision
            for (Rectangle block : blocks) {
                //is the player hitting a block
                if (player.intersects(block)) {
                    //get the collision rectangle
                    Rectangle intersection = player.intersection(block);
                    //fix the x movement
                    if (intersection.width < intersection.height) {
                        //player on the left
                        if (player.x < block.x) {
                            player.x = player.x - intersection.width;
                            //if the player is on the right
                        } else {
                            player.x = player.x + intersection.width;
                        }
                    } else if (intersection.height < intersection.width) {
                        if (player.y < block.y) {
                            player.y = player.y - intersection.height;
                            //if the player is on the right
                        } else {
                            player.y = player.y + intersection.height;
                        }
                    }
                }
            }


            for (Rectangle enemy : enemies) {
                for (Rectangle block : blocks) {
                    //is the player hitting a block
                    if (enemy.intersects(block)) {
                        //get the collision rectangle
                        Rectangle intersection = enemy.intersection(block);
                        //fix the x movement
                        if (intersection.width < intersection.height) {
                            //player on the left
                            if (enemy.x < block.x) {
                                enemy.x = enemy.x - intersection.width;
                                //if the test is on the right
                            } else {
                                enemy.x = enemy.x + intersection.width;
                            }
                        } else if (intersection.height < intersection.width) {
                            if (enemy.y < block.y) {
                                enemy.y = enemy.y - intersection.height;
                                //if the test is on the right
                            } else {
                                enemy.y = enemy.y + intersection.height;
                            }
                        }
                    }
                }
            }
            Iterator<Rectangle> it = enemies.iterator();
            while (it.hasNext()) {
                Rectangle removeEnemy = it.next();
                if (player.intersects(removeEnemy)) {
                    it.remove();
                    lives = lives - 1;
                }
            }



            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        Game game = new Game();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        //add keylistener
        frame.addKeyListener(game); //keyboard
        game.addMouseListener(game); //mouse click
        game.addMouseMotionListener(game); //mouse motion

        // starts my game loop
        game.run();
    }

    public String direction(int playerX, int playerY, int x, int y) {

        //find the x and y lengths of the right triangle the mouse and character created

        String direction = "";

        //direction of character
        if (x > playerX) {
            triX = x - playerX;
        } else {
            triX = playerX - x;
        }
        if (y > playerY) {
            triY = y - playerY;
        } else {
            triY = playerY - y;
        }
        //find the angle and turn into a degree 
        triAngle = Math.atan2(triY, triX);
        triAngle = Math.toDegrees(triAngle);

        if (x > playerX && y < playerY && triAngle < 67.6 && triAngle > 22.4) {//NE
            direction = "NE";
        } else if (x > playerX && y < playerY && triAngle > 67.6
                || x < playerX && y < playerY && triAngle > 67.6) {//N
            direction = "N";
        } else if (x < playerX && y < playerY && triAngle < 67.6 && triAngle > 22.4) {//NW
            direction = "NW";
        } else if (x < playerX && y > playerY && triAngle < 67.6 && triAngle > 22.4) {//SW
            direction = "SW";
        } else if (x < playerX && y > playerY && triAngle < 22.4
                || x < playerX && y < playerY && triAngle < 22.4) {//W
            direction = "W";
        } else if (x > playerX && y > playerY && triAngle > 67.6
                || x < playerX && y > playerY && triAngle > 67.6) {//S
            direction = "S";
        } else if (x > playerX && y > playerY && triAngle < 67.6 && triAngle > 22.4) {//SE
            direction = "SE";
        } else if (x > playerX && y < playerY && triAngle < 22.4
                || x > playerX && y > playerY && triAngle < 22.4) {//E
            direction = "E";
        }
        return direction;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            left = true;
        } else if (key == KeyEvent.VK_D) {
            right = true;
        } else if (key == KeyEvent.VK_W) {
            up = true;
        } else if (key == KeyEvent.VK_S) {
            down = true;
        }
        if (key == KeyEvent.VK_ENTER) {
            enter = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            left = false;
        } else if (key == KeyEvent.VK_D) {
            right = false;
        } else if (key == KeyEvent.VK_W) {
            up = false;
        } else if (key == KeyEvent.VK_S) {
            down = false;
        }
        if (key == KeyEvent.VK_ENTER) {
            enter = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            buttonPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            buttonPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void mainGame() {
        //remove all enemies and pillars
        if (runGame) {

            blocks.clear();
            enemies.clear();
            pillars.clear();

            //add enemies
            //add enemies on left hand side of the screen
            for (int i = 0; i < numberOfEnemies / 2; i++) {
                int eX = (int) (Math.random() * (300 - 0 + 1)) + 0;
                int eY = (int) (Math.random() * (924 - 0 + 1)) + 0;
                enemies.add(new Rectangle(eX, eY, 50, 50));
            }
            //add enemies on the right hand side of the screen
            for (int i = 0; i < numberOfEnemies / 2; i++) {
                int eX = (int) (Math.random() * (1280 - 900 + 1)) + 900;
                int eY = (int) (Math.random() * (924 - 0 + 1)) + 0;
                enemies.add(new Rectangle(eX, eY, 50, 50));
            }

            //add pillars
            for (int i = 0; i < 9; i++) {
                int pX = (int) (Math.random() * (1280 - 0 + 1)) + 0;
                int pY = (int) (Math.random() * (924 - 0 + 1)) + 0;
                blocks.add(new Rectangle(pX, pY + 100, 90, 90));
                pillars.add(new Rectangle(pX, pY, 50, 50));
            }
        }

        runGame = false;
    }

    public void menuScreen() {
        if (runMenu) {

            blocks.clear();
            enemies.clear();
            pillars.clear();


            for (int menuEY = 100; menuEY < 800; menuEY = menuEY + 50) {
                enemies.add(new Rectangle(0, menuEY, 50, 50));
                enemies.add(new Rectangle(1200, menuEY, 50, 50));
                blocks.add(new Rectangle(50, menuEY + 50, 100, 100));
                blocks.add(new Rectangle(1050, menuEY + 50, 100, 100));
            }
            for (int menuEX = 50; menuEX < 1100; menuEX = menuEX + 50) {
                enemies.add(new Rectangle(menuEX, 0, 50, 50));
                enemies.add(new Rectangle(menuEX, 900, 50, 50));
                blocks.add(new Rectangle(menuEX, 100, 100, 100));
                blocks.add(new Rectangle(menuEX, 800, 100, 100));
            }

        }
        runMenu = false;
    }

    public void gameOver() {
        enemies.clear();
        blocks.clear();
        pillars.clear();
    }
}
