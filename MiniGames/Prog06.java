import java.util.Scanner;
import java.lang.StringBuffer;
import java.util.regex.Pattern;
public class Prog06 extends Score{
    public void run() {
        int count = 1;
        char input = ' ';
        boolean won = false, valid;
        StringBuffer underscores = new StringBuffer("");
        StringBuffer alphabet = new StringBuffer("abcdefghijklmnop"
                + "qrstuvwxyz");
        Scanner keyboard = new Scanner(System.in);
        for (int i=0; i<42; i++)
            System.out.print("*");
        System.out.println("\n*    WELCOME TO THE WORD GUESS GAME      *");
        System.out.println(  "*        (c) Rayan H. Al-Hammami         *");
        for (int i=0; i<42; i++)
            System.out.print("*");
        word word1 = new word();
        for (int i=0; i<word1.getWord().length();i++)
            underscores.append("_");
        System.out.println("\nFor Testing: Computer Got: " + word1.getWord());
        
        while (won == false) {
            valid = false;
            System.out.print("Guess this word: ");
            System.out.print(underscores);
            System.out.println("\t\t\t"+alphabet);
            while(!valid){
            System.out.print("\n[" + count+1 + "] Guess a letter: ");
            Pattern pattern = Pattern.compile("[a-zA-Z]");
            String strInput = keyboard.next();
            input = strInput.charAt(0);
            if (pattern.matcher(strInput).matches()){
                valid = true;
                count++;
            }
            else{
                System.out.println("Please enter a single letter: ");
            }
            }
            if (alphabet.charAt(input-97) == '.') {
                System.out.print("Already guessed this letter.\n");
                count--;
                continue;
            }
            //Scan word for letter
            for (int i=0; i<word1.getWord().length();i++) {
                if (input == word1.getWord().charAt(i)) {
                    underscores.setCharAt(i, input);
                }
                alphabet.setCharAt(input-97,'.');
            }   
                if (word1.getWord().toString().equals(underscores.toString())) {
                    won = true;
                }
                else won = false;   
        }
        System.out.println("\n" + word1.getWord()+": You got it with " + (count-1) + " guesses.");
        super.setScore((5*(count-1))-
                (count-1-word1.getWord().length())*-1);
        System.out.println("You scored " + super.getScore());
    }
}
