import javax.swing.*;
import java.awt.*;

public class myFrame extends JFrame{

    JButton button1;
    JButton button2;
    JButton button3;

    public myFrame(){
        myPanel threePanel = new myPanel(3, 3, 0, 0);
        myPanel fivePanel = new myPanel(5, 4, 250, 0);
        myPanel sevenPanel = new myPanel(7, 5, 500, 0);
        
        threePanel.setImageIcon("3x3.png");
        threePanel.setBackground(new Color (156, 121, 199)); //lighter purple
        fivePanel.setImageIcon("5x5.png");
        fivePanel.setBackground(new Color (110, 64, 168)); //other purple
        sevenPanel.setImageIcon("7x7.png");
        sevenPanel.setBackground(new Color (39, 0, 89)); //dark purple
        
        button1 = threePanel.getButton();
        button2 = fivePanel.getButton();
        button3 = sevenPanel.getButton();
        
        this.setTitle("TICTACTOE");
        this.setIconImage((new ImageIcon("3x3.png")).getImage());
        this.setSize(750, 400);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(threePanel);
        this.add(fivePanel);
        this.add(sevenPanel);
    }

}
