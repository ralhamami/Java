import java.util.Scanner;
public class Prog07 {
    public static void main(String[] args) {
        boolean won = false, valid = true;
        int tries = 0, guess = 0;
        String temp = "";
        char replay = '\0';
        Scanner input = new Scanner(System.in);
        System.out.print("Do you need instructions on how to play? (y/n): ");
        if (input.nextLine().charAt(0)=='y')
            System.out.print("\nInstructions:\nPlay Ball is a number guessing "
                    + "game where the user\nguesses 3 non-repeating numbers, "
                    + "each between 0-9\ninclusive. 'Strikes' tells the user "
                    + "how many of the\nnumbers he has chosen are correct and "
                    + "in the right\nplace. 'Balls' tells the user how many "
                    + "numbers are\ncorrect but in the wrong place. When the "
                    + "user guesses\nthe correct sequence, they have won!\n");
        do {
            Numbers numbers = new Numbers();
            for (int i=0; i<42; i++)
                System.out.print("_");
            System.out.println("\n|\t\tPlay Ball!\t\t |");
            System.out.print(  "|        (c) Rayan H. Al-Hammami         |\n|");
            for (int i=0; i<40; i++)
                System.out.print("_");
            System.out.println("|\nStrike out sequence -- " + numbers.getWhole());
            do {
                do {
                    //Input Validation Begins
                    if (valid == false) {
                        System.out.println("\tFOUL! Let's keep this game clean and "
                                + "only insert 3 valid numbers.");
                        //Subtracting try for FOULS!
                        --tries;
                    }
                    ++tries;
                    System.out.print("Heyyyy batta batta....Choose your move: ");
                    temp = input.nextLine();
                    
                    //No more or less than 3 digits
                    if (temp.length()>3 || temp.length()<3)
                        valid = false;
                    
                    //No non-integers
                    else if (temp.charAt(0)<48 || temp.charAt(0)>57||
                             temp.charAt(1)<48 || temp.charAt(1)>57||
                             temp.charAt(2)<48 || temp.charAt(2)>57)
                        valid = false;
                    
                    //No repeating integers
                    else if (temp.charAt(0)==temp.charAt(1)||temp.charAt(0)==
                             temp.charAt(2) || temp.charAt(1)==temp.charAt(2))
                        valid = false;
                    else 
                        valid = true;
                } while(valid == false);
                guess = Integer.parseInt(temp);
                Balls balls = new Balls(numbers,guess);
                Strikes strikes = new Strikes(numbers,guess);
                System.out.print("\t\tStrikes: " + strikes.getCount());
                System.out.println(" Balls: " + balls.getCount());
                if (guess == numbers.getWhole())
                    won = true;
            } while(won == false);
            System.out.println("\nSTRIKE! GAME!!!");
            System.out.println("Congratulations, you won in " + tries + " try/tries.");
            System.out.print("Would you like to play again? (y/n): ");
            replay = input.next().charAt(0);
            
            //Reset for new game
            input.nextLine();
            tries = 0;
            won = false;
        } while(replay=='y');
        System.out.println("\nThanks for playing!!!");
    }
}
