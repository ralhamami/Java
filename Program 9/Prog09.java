import java.util.Scanner;
public class Prog09 {
    public static void main(String[] args) {
        int response = 0;
        GameHistory gh = new GameHistory();
        Game game = new Game();
        do{    
            Scanner in = new Scanner(System.in);
            System.out.println("1 Hangman, 2 Baseball, 3 TicTacToe, 0 QUIT: ");
            response = in.nextInt();
            
            if (response == 1){
                game.run("Word Guess");
                gh.setList("Word Guess",game.getWord().getScore());
                in.nextLine();
            }
            else if (response == 2) {
                game.run("Baseball");
                gh.setList("Baseball",game.getBall().getScore());
                in.nextLine();
            }
            else if (response == 3) {
                game.run("Tic-Tac-Toe");
                gh.setList("Tic-Tac-Toe", game.getTic().getScore());
            }
            else {
                gh.printHistory();
                System.out.println("-------Game Stats-------");
                gh.printStats("Word Guess");
                gh.printStats("Baseball");
                gh.printStats("Tic-Tac-Toe");
            }
        }while(response != 0);
    }
}
