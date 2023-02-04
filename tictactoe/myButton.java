import javax.swing.JButton;

public class myButton extends JButton{
    private int row;
    private int col;

    public myButton(String thang){
        super(thang);
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public void setCol(int col){
        this.col = col;
    }
}
