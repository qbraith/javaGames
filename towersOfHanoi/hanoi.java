import java.util.Scanner;
public class hanoi {
    private final static String reset = "\u001B[0m", red = "\u001B[31m", green = "\u001B[32m", blue = "\u001B[34m", yellow = "\u001B[33m", purple = "\u001B[35m", cyan = "\u001B[36m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's play Towers of Hanoi!!");
        
        diskStack[] stacks = new diskStack[3];
        diskStack leftStack = new diskStack("left");
        diskStack rightStack = new diskStack("right");
        diskStack middleStack = new diskStack("middle");
        stacks[0] = leftStack; stacks[1] = middleStack; stacks[2] = rightStack;

        //Game Set Up
        int numDisks = 0; //placeholder

        do{
            System.out.println("\n\nHow many disks do you want to play with?");
            String input = scanner.nextLine();
            try{
                numDisks = Integer.valueOf(input);
                if (numDisks < 3 ||numDisks > 15)
                    System.out.println("You must play with between 3 and 15 disks.");  
            } catch (NumberFormatException e){
                System.out.println(red + "Invalid input." + reset);
                System.out.println("You must play with between 3 and 15 disks.");
            }
        } while (numDisks < 3 || numDisks > 15);

        //adding all disks to left tower
        for (int i = numDisks; i > 0; i--){
            leftStack.push(i);
        }

        int optimalMoves = ((int) Math.pow(2, numDisks)) - 1;

        //Gameplay
        int moves = 0;
        while (rightStack.getSize() != numDisks && middleStack.getSize() != numDisks){
            clear();
            System.out.println(purple + "Moves: " + moves + reset);
            System.out.println(blue + "\n...Current Stacks...\n" + reset);
            for (int i = 0; i < stacks.length; i++){
                System.out.println(cyan + stacks[i].printItems() + reset);
            }

            while (true){
                System.out.println(yellow + "\nWhich stack do you want to move from?\n" + reset);
                diskStack fromStack = getInput(stacks, scanner);

                System.out.println(yellow + "\nWhich stack do you want to move to?\n" + reset);
                diskStack toStack = getInput(stacks, scanner);

                if (fromStack.getSize() == 0){
                    System.out.println(red + "\nInvalid move. Selecting from an empty stack" + reset);
                } else if (toStack.getSize() == 0 || (Integer)fromStack.peek() < (Integer)toStack.peek()){
                    Object disk = fromStack.pop();
                    toStack.push(disk);
                    moves++;
                    break;
                } else
                    System.out.println(red + "\nInvalid move. You can't move a bigger disk onto a smaller one" + reset);
            }
        }
        //game beaten
        clear();
        for (int i = 0; i < stacks.length; i++) {System.out.println(cyan + stacks[i].printItems() + reset);}

        System.out.println(green + "\n\nCongratulations!!! You completed the game in " + moves + " moves!");
        System.out.println("The minimum amount of moves is " + optimalMoves + "." + reset);
        scanner.close();
    }

    public static diskStack getInput(diskStack[] stacks, Scanner scanner){
        String[] choices = new String[3];
        for (int i = 0; i < stacks.length; i++){
            choices[i] = stacks[i].getName().substring(0, 1);
        }

        while (true){
            System.out.println();
            for (int i = 0; i < stacks.length; i++){
                String name = stacks[i].getName();
                String letter = choices[i];
                System.out.println(yellow + "Enter " + letter + " for " + name + reset);
            }

            String input = scanner.nextLine().toLowerCase();

            int index = -1;
            for (int j = 0; j < choices.length; j++){
                if (choices[j].equals(input))
                    index = j;
            }
            
            if (index != -1){
                return stacks[index];
            }
        }
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

}
