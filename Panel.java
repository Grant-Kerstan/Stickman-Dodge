import javax.swing.*;
import java.awt.*;

public class Panel{
    
    //instance variable
    private JPanel panel;
    
    //Panel constuctor
    public Panel(int dim1, int dim2, JFrame frame, Color color){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(dim1, dim2));
        panel.setBackground(color);
    }
    //returns the panel
    public JPanel getPanel(){
       return panel;
    }
    //The addPanel method is used to display the panel after all components are added to it otherwise they may not be displayed
    public void addPanel(JFrame frame){
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
    } 
}