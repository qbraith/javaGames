import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class myFrame extends JFrame{
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
