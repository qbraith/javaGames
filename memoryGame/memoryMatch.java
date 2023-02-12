import java.lang.Thread;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class memoryMatch{
    private static int guesses = 0, pairsFound = 0;
    private final static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static void giveInfo(){
        System.out.println("Welcome to the memory matching game.");
        System.out.println("In this game, you will choose a space and then have to remember which space matches it.");
        System.out.println("You will get to choose the amount of pair to play the game with (min = 3, max = 26): ");
    }

    public static void showBoard(String[][] board){
        System.out.print("    ");
        for (int i = 0; i < board[0].length; i++){System.out.print(letters[i] + "    ");} //printing letter headers

        for (int i = 0; i < board.length; i++){
            System.out.print("\n"+ (i+1) + "  ");
            for (int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j] + "  ");
            }
        }
        System.out.println("\n");
    }
    
    public static void fillBoard(String board[][]){
        String reset = "\u001B[0m";
        String[] colors = {"\u001B[31m", "\u001B[36m", "\u001B[32m", "\u001B[33m", "\u001B[35m", "\u001B[34m"};
        //red, cyan, green, yellow, purple, blue
        ArrayList<String> colorList = new ArrayList<>();
        
        Random r = new Random();
        int row = board.length, col = board[0].length;
        
        for (String[] section : board){Arrays.fill(section, ".");}
        
        for (int i = 0; i < (row * col); i++){
            int randRow = r.nextInt(row), randCol = r.nextInt(col);
            if (!board[randRow][randCol].equals(".")){ i--; continue;}

            if (i % 12 == 0){ //random stuff to randomize colors
                String c = "";
                colorList.removeAll(colorList);
                while (colorList.size() < 6){ //just randomization of colors
                    int num = (int)(Math.random() * 6);
                    if (!c.contains(String.valueOf(num))){
                        colorList.add(colors[num]);
                        c += String.valueOf(num);
                    }
                }
            }
            board[randRow][randCol] = colorList.get((i/2)%6) + letters[i/2] + reset;
        }
    }

    public static String[][] getDimension(Scanner obj){
        int row = 0, col = 0; //placeholders
        boolean rowBool = true, colBool = true;

        do { //getting valid row
            System.out.print("\nEnter the amount of rows you would like the board to have: ");
            try{
                String roww = obj.nextLine();
                row = Integer.valueOf(roww);
                if (row < 2 || row > 26)
                    System.out.println("You must play with between 2 and 26 rows with a maximum of 52 spaces");
                else
                    rowBool = false;
            } catch (NumberFormatException e){
                System.out.println("You must enter a number");
            }
        } while (rowBool);

        do { //getting valid column
            System.out.print("\nEnter the amount of columns you would like the board to have: ");
            try{
                String coll = obj.nextLine();
                col = Integer.valueOf(coll);
                if (col < 2 || col > 26)
                    System.out.println("You must play with between 2 and 26 columns with a maximum of 52 spaces");
                else
                    colBool = false;
            } catch (NumberFormatException e){
                System.out.println("You must enter a number");
            }
        } while (colBool);

        if (row * col > 52){
            System.out.println("You can play with a maximum of 52 spaces.");
            return getDimension(obj);
        }
        if (row * col % 2 == 1){
            System.out.println("You have to play with an even number of spaces.");
            return getDimension(obj);
        }

        if (row > 9){
            System.out.println("\nHorizontal board for readbility\n");
            return new String[col][row];
        }
        System.out.println();
        return new String[row][col];
    }
    
    public static void getMove(String[][] board, String[][] bracketboard, Scanner obj, ArrayList<String> guessed){
        String guess1 = "", guess2 = ""; //placeholders
        boolean goodGuess1 = false, goodGuess2 = false;
        do{
            System.out.print("Enter a coordinate that you think you can guess the pair of: ");
            guess1 = obj.nextLine().toUpperCase();
            goodGuess1 = checkGuess(board, bracketboard, guess1, guessed);
        } while (!goodGuess1);
        guessed.add(guess1);
        
        do{
            System.out.print("Enter the coordinate that you think matches your previous guess: ");
            guess2 = obj.nextLine().toUpperCase();
            goodGuess2 = checkGuess(board, bracketboard, guess2, guessed);
        } while (!goodGuess2);
        guessed.add(guess2);

        int prevGuessRow = findLetter(guessed.get(guessed.size()-1).substring(0, 1));
        int prevGuessCol = Integer.valueOf(guessed.get(guessed.size() - 1).substring(1)) - 1;
        int prev2GuessRow = findLetter(guessed.get(guessed.size()-2).substring(0, 1));
        int prev2GuessCol = Integer.valueOf(guessed.get(guessed.size() - 2).substring(1)) - 1;
        
        if (bracketboard[prev2GuessCol][prev2GuessRow].equals(bracketboard[prevGuessCol][prevGuessRow]) && !bracketboard[prev2GuessCol][prev2GuessRow].equals("[ ]")){
            System.out.println("Correct. You found a pair!!");
            pairsFound++;
        } else{
            bracketboard[prevGuessCol][prevGuessRow] = "[ ]";
            bracketboard[prev2GuessCol][prev2GuessRow] = "[ ]";
        }

        guesses++;
        try{Thread.sleep(2500);} catch (InterruptedException e){}

    }
    
    public static boolean checkGuess(String board[][], String[][] bracketboard, String move, ArrayList<String> guessed){
        int number = 0; //placeholder

        if (move.length() != 2 && move.length() != 3)
            return false;

        int letterIndex = findLetter(move.substring(0, 1));
        if (letterIndex >= board[0].length){
            System.out.println("Location not on board");
            return false;
        } 

        try{
            number = Integer.valueOf(move.substring(1));
            if (number < 1 || number > board.length){
                System.out.println("Location not on board.");
                return false;
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid input.");
            return false;
        }
        if (!bracketboard[number-1][letterIndex].equals("[ ]") || guessed.size() > 0 && move.equals(guessed.get(guessed.size() - 1)) && guessed.size()%2 == 1){ //other side of or might not even be needed
            System.out.println("Invalid guess.");
            return false;
        }
        bracketboard[number-1][letterIndex] = " " + board[number-1][letterIndex] + " ";
        showBoard(bracketboard);
        return true;

    }

    public static int findLetter(String letter){ //binary search for time complexity 
        int left = 0, right = letters.length;
        while (left <= right){
            int mid = (left + right)/2;
            if (letters[mid].equals(letter))
                return mid;
            else if (letters[mid].compareTo(letter) > 0)
                right = mid-1;
            else if (letters[mid].compareTo(letter) < 0)
                left = mid+1;
        }
        return -1;
    }
  
    public static void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        String[][] board;
        ArrayList<String> allGuesses = new ArrayList<>();
        System.out.print("Enter 'c' to choose board size or 'd' for default: ");
        String choice = obj.nextLine().toLowerCase();
        if (choice.equals("c"))
            board = getDimension(obj);
        else
            board = new String[4][4];
        String[][] bracketboard = new String[board.length][board[0].length];
        for(String[] array : bracketboard){Arrays.fill(array, "[ ]");} //fills bracketboard with brackets

        fillBoard(board);
 
        while (pairsFound < (board.length*board[0].length)/2){
            showBoard(bracketboard);
            getMove(board, bracketboard, obj, allGuesses);
            clear();
        }

        showBoard(bracketboard);
        System.out.println("It took you " + guesses + " moves to guess " + (board.length*board[0].length)/2 + " pairs.");

    }

}