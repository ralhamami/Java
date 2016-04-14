import java.util.Scanner;
public class Prog07 extends Score{
    public void run(){
        boolean won = false, valid = true;
        int tries = 0, guess;
        String temp = "";
        Scanner input = new Scanner(System.in);
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
        super.setScore(100-((tries-1)*2));
        System.out.println("You scored " + super.getScore());
    }
}
