import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class myPanel extends JPanel implements ActionListener{
    private JButton button;
    private int size;
    private int winCon;

    public myPanel(int size, int winCon, int x, int y){
        this.size = size;
        this.winCon = winCon;
        this.setLayout(null);
        this.setBounds(x, y, 250, 400);
                
        JLabel label = new JLabel("(" + winCon + " in a row)");
        label.setForeground(Color.yellow);
        label.setBackground(Color.black);
        label.setOpaque(true);
        label.setBounds(70, 300, 100, 50);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("comic sans", Font.BOLD, 14));

        button = new JButton();
        button.setText(size + "x" + size);
        button.setBounds(25, 25, 200, 200);
        button.setIconTextGap(25);
        button.setVerticalTextPosition(JButton.TOP);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.black); //pink
        button.setForeground(new Color (33, 228, 235)); //light blue
        button.addActionListener(this);
        button.setFocusable(false);
        button.setLayout(null);

        this.add(label);
        this.add(button);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button){
            ((JFrame)SwingUtilities.getWindowAncestor(this)).dispose(); //exits out parent JFrame window
            tictactoe ttt = new tictactoe();
            ttt.makeGame(this.size, this.winCon);
        }
    }

    public void setImageIcon(String fileName){
        ImageIcon icon = new ImageIcon(fileName);
        Image img = icon.getImage();
        Image newImage = img.getScaledInstance(150, 150, java.awt.Image.SCALE_AREA_AVERAGING);
        icon = new ImageIcon(newImage);
        this.button.setIcon(icon);
    }

}
