import java.util.Scanner;

public class ttt{

    public static void fillBoard(char[][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = '.';
            }
        }
    }
    
    public static void printBoard(char[][] board){
        System.out.println("   1  2  3");
        char[] letters = {'A', 'B', 'C'};
        int counter = 0;
        for (char[] row : board){  //could be better with regular for loop
            System.out.print(letters[counter]); 
            for (char c : row){
                System.out.print("  " + c);
            }
            System.out.println();
            counter++;
        }
        
    }
    
    public static void move(char[][] board, char player, Scanner obj){
        boolean valid = false;
        String move = ""; //placeholder
        do{
            System.out.print("Enter coordinate to place piece: ");
            move = obj.nextLine().toLowerCase();
            valid = checkValid(move);
            if (!valid)
                System.out.println("Invalid input.\n");
        } while (!valid);

        char[] letters = {'a', 'b', 'c'};
        int row = findLetter(letters, move.charAt(0));
        int col = Integer.valueOf(move.substring(1)) - 1;

        if (board[row][col] != '.'){
            System.out.println("Space taken.\n");
            move(board, player, obj);
            return;
        }
        board[row][col] = player;

    }

    public static int findLetter(char[] letters, char target){
        for (int i = 0; i < letters.length; i++){
            if (letters[i] == target)
                return i;
        }
        return -1;
    }

    public static boolean checkValid(String move){
        if (move.length() != 2)
            return false;
        char c1 = move.charAt(0);
        char c2 = move.charAt(1);

        if (c1 != 'a' && c1 != 'b' && c1 != 'c')
            return false;
        if (c2 != '1' && c2 != '2' && c2 != '3')
            return false;
        
        return true;
    }

    public static boolean checkWinner(char[][] board){
        for (char[] row : board){ //vertically
            String roww = "";
            for (char c : row){roww += c;}

            if (roww.contains("OOO") || roww.contains("XXX"))
                return true;
        }

        for (int i = 0; i < board.length; i++){ //horizontally
            String col = "";
            for (int j = 0; j < board[i].length; j++){col += board[j][i];}

            if (col.contains("OOO") || col.contains("XXX"))
                return true;
        }

        String diag1 = "";
        for (int i = 0; i < 3; i++){diag1 += board[i][i];} //tl --> br diag

        if (diag1.contains("OOO") || diag1.contains("XXX"))
            return true;
        
        String diag2 = "";
        for (int i = 2; i >= 0; i--){diag2 += board[i][2 - i];} //tr --> bl diag

        if (diag2.contains("OOO") || diag2.contains("XXX"))
            return true;
        
        //all cases false;
        return false;
    }
    
    public static void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        char[][] board = new char[3][3];
        String reset = "\u001B[0m", green = "\u001B[32m";
        char player;

        int turn = 1;

        fillBoard(board);
        while (true){
            printBoard(board);
            if (turn % 2 == 1)
                player = 'X';
            else   
                player = 'O';
            
            move(board, player, obj);
            boolean gameOver = checkWinner(board);
            if (gameOver){
                clear();
                printBoard(board);
                System.out.println("\n" + green + player + " WINS!!!" + reset);
                return;
            }
            turn++;
            if (turn == 10){
                clear();
                printBoard(board);
                System.out.println("\nTIE!!!!");
                return;
            }
            clear();
        }
    }
}