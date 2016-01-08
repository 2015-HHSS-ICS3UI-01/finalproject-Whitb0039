/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author whitb0039
 */
// make sure you rename this class if you are doing a copy/paste
public class Game extends JComponent implements KeyListener, MouseMotionListener, MouseListener {

    // Height and Width of our game
    static final int WIDTH = 1275;
    static final int HEIGHT = 956;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    //create blocks
    ArrayList<Rectangle> blocks = new ArrayList<>();
    //images array
    //create player
    Rectangle player = new Rectangle(630, 430, 50, 50);
    //keyboard booleans
    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;
    //direction booleans
    boolean E = false;
    boolean NE = false;
    boolean N = false;
    boolean NW = false;
    boolean W = false;
    boolean SW = true;
    boolean S = false;
    boolean SE = false;
    //mouse variables
    boolean leftButtonPressed = false;
    int mouseX = 0;
    int mouseY = 0;
    boolean buttonPressed = false;
    //crosshairs
    int x = 300;
    int y = 300;
    //create player movements
    int moveX = 0;
    int moveY = 0;
    //imported images
    BufferedImage playerImgS = imageHelper.loadImage("CharacterImgS.png");
    BufferedImage playerImgSE = imageHelper.loadImage("CharacterImgSE.png");
    BufferedImage playerImgE = imageHelper.loadImage("CharacterImgE.png");
    BufferedImage playerImgNE = imageHelper.loadImage("CharacterImgNE.png");
    BufferedImage playerImgN = imageHelper.loadImage("CharacterImgN.png");
    BufferedImage playerImgNW = imageHelper.loadImage("CharacterImgNW.png");
    BufferedImage playerImgW = imageHelper.loadImage("CharacterImgW.png");
    BufferedImage playerImgSW = imageHelper.loadImage("CharacterImgSW.png");

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 

        g.setColor(Color.BLACK);
        for (Rectangle block : blocks) {
            g.fillRect(block.x, block.y, block.width, block.height);
        }

        if (S) {
            g.drawImage(playerImgS, player.x, player.y, 50, 50, this);
        } else if (SE) {
            g.drawImage(playerImgSE, player.x, player.y, 50, 50, this);
        } else if (SW) {
            g.drawImage(playerImgSW, player.x, player.y, 50, 50, this);
        } else if (N) {
            g.drawImage(playerImgN, player.x, player.y, 50, 50, this);
        } else if (NE) {
            g.drawImage(playerImgNE, player.x, player.y, 50, 50, this);
        } else if (NW) {
            g.drawImage(playerImgNW, player.x, player.y, 50, 50, this);
        } else if (W) {
            g.drawImage(playerImgW, player.x, player.y, 50, 50, this);
        } else if (E) {
            g.drawImage(playerImgE, player.x, player.y, 50, 50, this);
        }

        //draw crosshair
        g.fillRect(x, y, 50, 50);

        g.fillRect(player.x, player.y, player.width, player.height);










        // GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        //initial things to do before game starts
        //load blocks
        blocks.add(new Rectangle(600, 400, 100, 10));
        blocks.add(new Rectangle(600, 400, 10, 100));
        blocks.add(new Rectangle(700, 400, 10, 100));
        blocks.add(new Rectangle(600, 500, 110, 10));



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

           
            //find direction of character
            x = mouseX - 25;
            y = mouseY - 25;
                      
            if(left){
                moveX = -5;
            }else if(right){
                moveX = +5;
            }else{
                moveX = 0;
            }
            
            player.x = player.x + moveX;
            
            if (mouseX > 637 && mouseY < 478) {
                NE = true;
            } else if (mouseX > 637 && mouseY > 478) {
                SE = true;
            } else if (mouseX < 637 && mouseY > 478) {
                SW = true;
            } else if (mouseX < 637 && mouseY < 478) {
                NW = true;
            }

            //go through all blocks
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
                    }
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

        // starts my game loop
        game.run();
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
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            left = false;
        } else if (key == KeyEvent.VK_D) {
            right = false;
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
}
