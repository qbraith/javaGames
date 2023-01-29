import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFrame;

public class tictactoe{ //!this is the one meant to be reusable
    static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};

    public static void fillBoard(char[][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = '.';
            }
        }
    }
    
    public static void printBoard(char[][] board, int boardSize){ //!resuable
        String reset = "\u001B[0m", red = "\u001B[31m", cyan = "\u001B[36m";
        System.out.print("\n   ");
        for (int i = 0; i < boardSize; i++){System.out.print((i+1) + "  ");}
        System.out.println();
        int counter = 0;
        for (char[] row : board){  //could be better with regular for loop
            System.out.print(letters[counter]); 
            for (char c : row){
                if (c == 'X')
                    System.out.print("  " + red + c + reset);
                else if (c == 'O')
                    System.out.print("  " + cyan + c + reset);
                else 
                    System.out.print("  " + c);
            }
            System.out.println();
            counter++;
        }
        
    }
    
    public static void move(char[][] board, char player, int boardSize, Scanner obj){
        boolean valid = false;
        String move = ""; //placeholder
        do{
            System.out.print("\nEnter coordinate to place piece: ");
            move = obj.nextLine().toLowerCase();
            valid = checkValid(move, boardSize);
            if (!valid)
                System.out.println("Invalid input.\n");
        } while (!valid);

        int row = findLetter(letters, (char)(move.charAt(0) - 32));
        int col = Integer.valueOf(move.substring(1)) - 1;

        if (board[row][col] != '.'){
            System.out.println("Space taken.\n");
            move(board, player, boardSize, obj);
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

    public static boolean checkValid(String move, int boardSize){
        if (move.length() != 2)
            return false;
        char c1 = move.charAt(0);
        char c2 = move.charAt(1);

        if (findLetter(Arrays.copyOf(letters, boardSize), (char) (c1-32)) == -1)    
            return false;

        try{
            int row = Integer.valueOf(String.valueOf(c2));
            if (row < 0 || row > boardSize)
                return false;
        } catch (NumberFormatException e){
            return false;
        }
        
        return true;
    }

    public static boolean checkWinner(char[][] board, int winCon){ //gotta check other diagnoals for 5x5
        for (char[] row : board){ //vertically
            String roww = "";
            for (char c : row){roww += c;}

            if (stringContains(roww, winCon))
                return true;
        }

        for (int i = 0; i < board.length; i++){ //horizontally
            String col = "";
            for (int j = 0; j < board[i].length; j++){col += board[j][i];}

            if (stringContains(col, winCon))
                return true;
        }

        //all diag
        for (int i = 0; i < board.length - 2; i++){
            int startRow = Math.abs(board.length - winCon - i), topLeftCol = 0, topRightCol = board[0].length - 1;
            String leftToRight = "", rightToLeft = "";

            while (startRow < board.length && topLeftCol < board.length && topRightCol >= 0){
                leftToRight += board[startRow][topLeftCol];
                rightToLeft += board[startRow][topRightCol];
                startRow++; topLeftCol++; topRightCol--;
            }
            if (stringContains(leftToRight, winCon) || stringContains(rightToLeft, winCon))
                return true;
        }

        //all cases false;
        return false;
    }

    public static boolean stringContains(String str, int winCon){
        String Xs = "", Os = "";
        for (int i = 0; i < winCon; i++){ Xs += "X"; Os += "O";}

        if (str.contains(Xs) || str.contains(Os))
            return true;
        return false;
    }
    
    public static void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public void makeGame(int size, int winCon){
        Scanner obj = new Scanner(System.in);
        String reset = "\u001B[0m", green = "\u001B[32m";
        char player;
        char[][] board;

        System.out.println("after");
        board = new char[size][size];
        int boardSize = board.length, maxMoves = (board.length*board.length)+1, turn = 1;

        fillBoard(board);
        clear();
        while (true){
            printBoard(board, boardSize);
            if (turn % 2 == 1)
                player = 'X';
            else   
                player = 'O';
            
            move(board, player, boardSize, obj);
            boolean gameOver = checkWinner(board, winCon);
            if (gameOver){
                clear();
                printBoard(board, boardSize);
                System.out.println("\n" + green + player + " WINS!!!" + reset);
                return;
            }
            turn++;
            if (turn == maxMoves){
                clear();
                printBoard(board, boardSize);
                System.out.println("\nTIE!!!!");
                return;
            }
            clear();
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new myFrame();
        frame.setResizable(false);
    }
}