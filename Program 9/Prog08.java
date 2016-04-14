import java.util.Scanner;
import java.util.regex.Pattern;
public class Prog08 extends Score{
    public void run(){
        for (int i=0; i<42; i++)
            System.out.print("_");
        System.out.println("\n|\t\tTic-Tac-Toe!!!\t\t |");
        System.out.print(  "|        (c) Rayan H. Al-Hammami         |\n|");
        for (int i=0; i<40; i++)
            System.out.print("_");
        System.out.println("|\n");

        Scanner input = new Scanner(System.in);
        boolean won = false, valid;
        int row, column, moveCount = 0;
        Board board = new Board();

        //MAIN GAME LOOP
        //Loop while game is not won and the board is still playable
        
        board.clear();
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
                        moveCount++;
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
                super.setScore(100-(3-moveCount));
                System.out.println("You scored " + super.getScore());
                break;
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
    }
}

