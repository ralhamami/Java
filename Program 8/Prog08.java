import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Prog08 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean won = false, replay = false, valid;
        int row, column, a;
        Board board = new Board();
        
        for (int i=0; i<42; i++)
            System.out.print("_");
        System.out.println("\n|\t\tTic-Tac-Toe!!!\t\t |");
        System.out.print(  "|        (c) Rayan H. Al-Hammami         |\n|");
        for (int i=0; i<40; i++)
            System.out.print("_");
        System.out.println("|\n");

        //MAIN GAME LOOP
        //Loop while game is not won and the board is still playable
        do{
        while(!board.gameWon() && !board.full()) {
            valid = false;
            board.printBoard();
            
            //Loop until player enters a move on an empty
            while(!valid) {
                System.out.println("Your turn (O) - where (row col)?");
                Pattern pattern = Pattern.compile("[0-2](\\s)[0-2]");
                String temp = input.nextLine();
                if (pattern.matcher(temp).matches()) {
                    row = Character.getNumericValue(temp.charAt(0));
                    column = Character.getNumericValue(temp.charAt(2));
                    if (board.isValid(row,column)) {
                        board.makeMove(row, column);
                        valid = true;
                    }
                    else
                        System.out.println("Illegal move, try again.");
                }
                else {
                    System.out.println("Invalid: enter as (x y)");
                }
            }
            //Check if O won
            if (board.gameWon()){
                System.out.println("You Won!!!");
                board.printBoard();
                System.exit(0);
            }
            
            //Check any empty squares for X to play on
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                        if (board.isValid(i,j)) {
                            if (board.getTurn()=='x') {
                                board.makeMove(i,j);
                            }
                        }
                }
                if (board.getTurn()!='x')
                    break;
            }
                                            
           //Check if X won
           if (board.gameWon()) {
               System.out.println("Computer Won! :(");
               board.printBoard();
               break;
           }
        }
        
        //If loop ends without a winner, it signifies a drawing
        if (!board.gameWon())
            System.out.println("No Winners! Draw!");
        System.out.println("Play again? y/n");
        if (input.next().charAt(0) == 'y')
            replay = true;
    
        else
            replay = false;
        
    } while(replay == true);
    }
}

