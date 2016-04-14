import java.util.Scanner;
import java.lang.StringBuffer;
public class Prog06 {
    public static void main(String[] args) {
        int count = 1;
        boolean won = false;
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
            System.out.print("Guess this word: ");
            System.out.print(underscores);
            System.out.println("\t\t  Unused Letters: "+alphabet);
            System.out.print("\n[" + count++ + "] Guess a letter: ");            
            char input = keyboard.next().charAt(0);
            while (input<97 || input>122) {
                System.out.print("Please enter a valid lowercase letter: ");
                input = keyboard.next().charAt(0);
            }
            if (alphabet.charAt(input-97)=='.') {
                System.out.print("Already guessed this letter.");
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
            for (int i=0; i<word1.getWord().length();i++) {
                if (underscores.charAt(i)==word1.getWord().charAt(i)
                        && i<word1.getWord().length()-1) {
                    won = true;
                }
                else won = false;
            }  
        }
        System.out.println("\n" + word1.getWord()+": You got it with " + (count-1) + " guesses.");
    }
}
