public class word {
    private String word;

    word() {
        words Word = new words();
        word = Word.getWord();
    }
    
    public StringBuffer getWord() {
        java.lang.StringBuffer temp = new StringBuffer(word);
        return temp;
    }
    
}
