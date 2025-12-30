import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame implements MouseListener{
    
    //instance variables
    private JFrame frame;
    private JButton button;
    private ImageIcon icon;
    private JLabel labelIcon;
    
    //Constructor
    public MainFrame(){
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //creates a clickable button
    public void makeButton(String name, JPanel panel, Color color, int x, int y, int width, int height){
        button = new JButton(name);
        panel.add(button);
        button.setBackground(color);
        button.setBounds(x, y, width, height);
        button.addMouseListener(this);
    }
    
    //Creates a Jlable
    public static void makeLabel(String text, JPanel panel, Color color, int x, int y, int width, int height, int type, int size){
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(new Font("Monospaced", type, size));
        label.setBounds(x, y, width, height);
        panel.add(label);
    }
    
    //creates an image icon
    public void makeIcon(JPanel panel, int x, int y, int dim1, int dim2, String path){
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image temp = image.getScaledInstance(dim1, dim2,  java.awt.Image.SCALE_DEFAULT);
        ImageIcon newIcon = new ImageIcon(temp);
        labelIcon = new JLabel();
        labelIcon.setIcon(newIcon);
        labelIcon.setBounds(x, y, dim1, dim2);
        panel.add(labelIcon);
    }
    
    //getter methods
    public JLabel getLabelIcon(){
        return labelIcon;
    }
    public JFrame getFrame(){
        return frame;
    }
    
    //implemented Mouse event methods
    public void mouseClicked(MouseEvent e){
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }
    //When the button is clicked it removes the current panels and calls the GameScreen consturctor and starts the rocketMovement method
    public void mousePressed(MouseEvent e){
        HomeScreen.getHomePanel().setVisible(false);
        HomeScreen.getTopPanel().setVisible(false);
        GameScreen gameScreen = new GameScreen(frame);
        GameScreen.rocketMovement();
    }
}