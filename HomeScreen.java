import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeScreen{
    
    //instance variable
    private static int highScore = 0;
    private static int scoreCounter = 0;
    private static int counter = 0;
    private int counterOffset = 0;
    private static Panel panel;
    private static Panel top;
    
    //HomeScreen constructor
    public HomeScreen(MainFrame frame){
        for(int i = highScore; i > 9; i/=10){
            counterOffset++;
        }
        //creates the various different components of the home screen
        top = new Panel(400, 50, frame.getFrame(), Color.red);
        panel = new Panel(400, 400, frame.getFrame(), Color.pink);
        frame.makeButton("Start", panel.getPanel(), Color.orange, 150, 125, 100, 50);
        MainFrame.makeLabel("DODGER", top.getPanel(), Color.blue, 130, -22, 300, 100, Font.BOLD, 40);
        MainFrame.makeLabel("High Score", panel.getPanel(), Color.black, 125, 200, 200, 35, Font.PLAIN, 25);
        MainFrame.makeLabel(String.valueOf(highScore), panel.getPanel(), Color.black, 190 - (13 * counterOffset), 250, 100, 50, Font.BOLD, 40);
        MyProgram.getMainFrame().makeIcon(panel.getPanel(), 125, 220, 150, 150, "trophy.png");
        top.addPanel(frame.getFrame());
        panel.addPanel(frame.getFrame());
    }
    
    //getter and setter methods
    public static JPanel getHomePanel(){
        return panel.getPanel();
    }
    public static JPanel getTopPanel(){
        return top.getPanel();
    }
    public static int getCounter(){
        return counter;
    }
    public static void counterIncrease(){
        counter++;
    }
    public static void scoreCounterIncrease(){
         scoreCounter++;
    }
    public static int getScoreCounter(){
        return scoreCounter;
    }
    public static void setHighScore(int num){
        highScore = num;
    }
    public static int getHighScore(){
        return highScore;
    }
    public static void setScoreCounter(int num){
        scoreCounter = num;
    }
}