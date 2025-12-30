import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreen implements KeyListener{
    
    //instance variables
    private static int sJumpCounter = 0;    
    private static int sDuckCounter = 0;
    private int jumpCounter = 0;
    private int duckCounter = 0;
    private static int rocketSpeed = 5;
    private static JLabel rocket;
    private JLabel dodger;
    private JLabel jumper;
    private JLabel ducker;
    private static int soldierHeight = 253;
    private static JLabel sDodger;
    private static JLabel sJumper;
    private static JLabel sDucker;
    private static Panel panel;
    
    //GameScreen constructor which creates the various components of the game screen
    public GameScreen(JFrame frame){
        panel = new Panel(400, 400, frame, Color.pink);
        MyProgram.getMainFrame().makeIcon(panel.getPanel(), 0, 240, 125, 125, "Dodger.png");
        dodger = MyProgram.getMainFrame().getLabelIcon();
        MyProgram.getMainFrame().makeIcon(panel.getPanel(), 0, 240, 125, 125, "Jumper.png");
        jumper = MyProgram.getMainFrame().getLabelIcon();
        MyProgram.getMainFrame().makeIcon(panel.getPanel(), 0, 240, 125, 125, "Ducker.png");
        ducker = MyProgram.getMainFrame().getLabelIcon();
        MyProgram.getMainFrame().makeIcon(panel.getPanel(), 340, 250, 75, 75, "Soldier.png");
        MyProgram.getMainFrame().makeIcon(panel.getPanel(), 340, 325, 75, 75, "Soldier.png");
        MyProgram.getMainFrame().makeIcon(panel.getPanel(), 700, 400, 50, 50, "Rocket.png");
        rocket = MyProgram.getMainFrame().getLabelIcon();
        MyProgram.getMainFrame().makeIcon(panel.getPanel(), 0, 0, 400, 400, "Background.png");
        frame.addKeyListener(this);
        jumper.setVisible(false);
        ducker.setVisible(false);
        panel.addPanel(frame);
        sDodger = dodger;
        sJumper = jumper;
        sDucker = ducker;
    }
    
    //The jump method moves the different stick figure animations to their corresponding spots and repeatedly sets the bounds of the jumper
    public void jump(){
        if(dodger.isVisible()){
            dodger.setBounds(0, 50, 125, 125);
            jumper.setVisible(true);
            jumper.setBounds(0, 240, 125, 125);
            dodger.setVisible(false);
            Timer timer = new Timer();
            TimerTask task = new TimerTask(){
            public void run(){
                sJumpCounter++;
                jumpCounter++;
                if(jumpCounter <= 25){
                    jumper.setBounds(0, getYPosition(jumper) - 4, 125, 125);
                }
                if(jumpCounter > 25){
                    jumper.setBounds(0, getYPosition(jumper) + 4, 125, 125);
                }
                if(jumpCounter >= 50){
                    timer.cancel();
                    sJumpCounter = 0;
                    jumpCounter = 0;
                    dodger.setBounds(0, 240, 125, 125);
                    jumper.setVisible(false);
                    jumper.setBounds(0, 50, 125, 125);
                    dodger.setVisible(true);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 20);
        }
    }
    
    //The duck method moves the different stick figure animations to their corresponding spots and repeatedly sets the bounds of the ducker
    public void duck(){
        if(dodger.isVisible()){
            dodger.setBounds(0, 50, 125, 125);
            ducker.setVisible(true);
            ducker.setBounds(0, 240, 125, 125);
            dodger.setVisible(false);
            Timer timer = new Timer();
            TimerTask task = new TimerTask(){
            public void run(){
                sDuckCounter++;
                jumpCounter++;
                if(jumpCounter <= 30){
                    ducker.setBounds(0, getYPosition(ducker) + 2, 125, 125);
                }
                if(jumpCounter > 30){
                    ducker.setBounds(0, getYPosition(ducker) - 2, 125, 125);
                }
                if(jumpCounter >= 60){
                    timer.cancel();
                    sDuckCounter = 0;
                    jumpCounter = 0;
                    dodger.setBounds(0, 240, 125, 125);
                    ducker.setVisible(false);
                    ducker.setBounds(0, 50, 125, 125);
                    dodger.setVisible(true);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 20);
        }
    }
    
    //getter methods
    public int getYPosition(JLabel type){
        Point position = type.getLocation();
        return position.y;
    }
    public static int getXPosition(){
        Point position = rocket.getLocation();
        return position.x;
    }
    public static int getYPositionRocket(){
        Point position = rocket.getLocation();
        return position.y;
    }
    
    //The rocketMovement method randomly picks 1 of 2 y positions to go to and repeatedly sets the bounds of the rocket to the left side of the frame checking whether it is in contact with one of the stick figure animations until it gets teleported back
    public static void rocketMovement(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                if(getXPosition() < -50){
                    rocket.setBounds(400, 200, 50, 50);
                    HomeScreen.scoreCounterIncrease();
                    HomeScreen.counterIncrease();
                    int random = (int)(Math.random() * 2) + 1;
                    if(random == 1){
                        soldierHeight = 253;
                    } else{
                        soldierHeight = 328;
                    }
                }
                if(HomeScreen.getScoreCounter() > HomeScreen.getHighScore()){
                    HomeScreen.setHighScore(HomeScreen.getScoreCounter());
                }
                if(HomeScreen.getCounter() % 6 == 0 && HomeScreen.getCounter() != 0 && rocketSpeed > 1){
                    rocketSpeed--;
                    timer.cancel();
                    HomeScreen.counterIncrease();
                    rocketMovement();
                }
                rocket.setBounds(getXPosition() - 1, soldierHeight, 50, 50);
                if(getXPosition() <= 75 && getXPosition() >= 0 && (sDodger.getBounds().intersects(rocket.getBounds()) || sJumper.getBounds().intersects(rocket.getBounds()) || sDucker.getBounds().intersects(rocket.getBounds())) && ((sJumpCounter > 0 && getYPositionRocket() < 300) || (sDuckCounter > 0 && getYPositionRocket() > 300) || (sJumpCounter == 0 && sDuckCounter == 0))){
                    timer.cancel();
                    panel.getPanel().setVisible(false);
                    HomeScreen homeScreen = new HomeScreen(MyProgram.getMainFrame());
                    rocketSpeed = 5;
                    HomeScreen.setScoreCounter(0);
                } 
            }
        };
        timer.scheduleAtFixedRate(task, 0, rocketSpeed);
    } 
    
    //implemented Key event methods
    public void keyReleased(KeyEvent e){
    }
    public void keyTyped(KeyEvent e){
    }
    //calls the jump method if the up arrow is pressed and the duck method if the down arrow is pressed
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == 38){
            jump();
        }
        if(e.getKeyCode() == 40){
            duck();
        }
    }
}