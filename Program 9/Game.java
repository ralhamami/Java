public class Game{
    private Prog06 word = new Prog06();
    private Prog07 ball = new Prog07();
    private Prog08 tic = new Prog08();
    
    public void run(String i){
        if (i.equals("Word Guess"))
            word.run();
        else if (i.equals("Baseball"))
            ball.run();
        else if (i.equals("Tic-Tac-Toe"))
            tic.run();
    }   

    public Prog06 getWord() {
        return word;
    }

    public Prog07 getBall() {
        return ball;
    }

    public Prog08 getTic() {
        return tic;
    }
    

}
