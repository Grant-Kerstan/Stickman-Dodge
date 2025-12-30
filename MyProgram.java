import javax.swing.*;
import java.awt.*;

public class MyProgram {
    
    //instance variable
    private static MainFrame frame;
    
    public static void main(String[] args) {
        frame = new MainFrame();
        HomeScreen homeScreen = new HomeScreen(frame);
    }
    
    //returns the main frame
    public static MainFrame getMainFrame(){
        return frame;
    }
}