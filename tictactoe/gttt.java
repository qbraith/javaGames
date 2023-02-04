import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class gttt extends JFrame implements ActionListener{
    ArrayList<myButton> buttonList = new ArrayList<>();
    private static tictactoe ttt = new tictactoe();
    private char[][] board;
    private static int turn = 1;
    private int winCon;

    public gttt(int size, int winCon){
        this.setSize(800, 800);
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setForeground(Color.black);
        
        this.board = new char[size][size];
        this.winCon = winCon;
    
        for (int i = 0; i < size * size; i++){
            myButton button = new myButton(String.valueOf(i/size) + String.valueOf(i%size));
            button.setText(" ");
            button.setForeground(Color.BLACK);
            button.setBackground(Color.black);
            //button.setContentAreaFilled(false);
            button.setFocusable(false);
            //button.setOpaque(true);
            this.add(button);
            buttonList.add(button);
            button.addActionListener(this);
            button.setRow(i/size);
            button.setCol(i%size);
        }

        this.setLayout(new GridLayout(size, size, 2, 2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    public void actionPerformed(ActionEvent e){
        for (myButton b : buttonList){
            if (e.getSource() == b){
                b.setBackground(Color.black);
                b.setContentAreaFilled(false);
                Dimension dim = b.getPreferredSize();
                int width = dim.width, height = dim.height;
                Dimension fsize = ((JFrame) b.getTopLevelAncestor()).getSize();
                int bWidth = fsize.width/(int) Math.sqrt(buttonList.size());
                int bHeight = fsize.height/(int) Math.sqrt(buttonList.size());
                JLabel label = new JLabel();
                //b.add(label);
                label.setBounds(0, 0, width, height);
                label.setText("noway");
                label.setForeground(Color.red);
                label.setBackground(Color.green);
                label.setOpaque(true);
                //b.setIcon(new ImageIcon("thisO.png"));
                if (!b.getText().equals("")){
                    ImageIcon preset = (turn % 2 == 1) ? new ImageIcon("thisO.png") : new ImageIcon("thisX.png");
                    b.setIcon(new ImageIcon(preset.getImage().getScaledInstance(bWidth, bHeight, java.awt.Image.SCALE_AREA_AVERAGING)));
                    boolean done = ttt.graphGame(board, turn, b, winCon);
                    if (done){
                        // String player = (turn % 2 == 1) ? "O" : "X";
                        // JLabel winner = new JLabel();
                        // winner.setText(player + "WINS!!!!");  
                        // winner.setBounds(400, 20, 200, 100);
                        // this.add(winner);   
                        //try{Thread.sleep(5000);} catch(InterruptedException error){System.out.println("error caught");}
                        this.dispose();
                    }
                    turn++;
                }
                b.setText("");
            }
        }
    }

    public void hideJunt(){
        this.setVisible(false);
    }
    public static void main(String[] args){
        //new gttt(4);
        // JFrame frame = new JFrame();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(500, 500);
        // frame.setLayout(new GridLayout(3, 3, 5, 5)); //3 rows, 3 columns, horizontal/vertical spacing between stuff

        // JButton button1 = new JButton("1");
        // frame.add(button1);
        // frame.add(new JButton("2"));
        // frame.add(new JButton("3"));
        // frame.add(new JButton("4"));
        // frame.add(new JButton("5"));
        // frame.add(new JButton("6"));
        // frame.add(new JButton("7"));
        // frame.add(new JButton("8"));
        // frame.add(new JButton("9"));
        
        // frame.setVisible(true);
    }

}
