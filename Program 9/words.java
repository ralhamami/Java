
import java.util.ArrayList;

public class words {
    private ArrayList<String> words;

    words() {
        words = new ArrayList<String>();
        words.add("print");
        words.add("hello");
        words.add("computer");
        words.add("comfort");
        words.add("science");
        words.add("monitor");
        words.add("country");
        words.add("telephone");
        words.add("program");
        words.add("orange");
        words.add("prison");
        words.add("mathematics");
        words.add("professional");
        words.add("language");
        words.add("classroom");
        words.add("funny");
        words.add("projector");
        words.add("angry");
        words.add("happy");
        words.add("electric");
        words.add("music");
        words.add("art");
        words.add("instrument");
        words.add("copier");
        words.add("house");
        words.add("fireplace");
        words.add("soccer");
        words.add("university");
        words.add("smell");
        words.add("taste");
    }
    
    public String getWord() {
        int x = (int)(Math.random() * (29 - 0) + 1);
        return words.get(x);
    }
}

