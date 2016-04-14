import java.util.ArrayList;
public class GameHistory {
    ArrayList<Integer> play = new ArrayList<Integer>();
    ArrayList<Integer> list = new ArrayList<Integer>();
    ArrayList<String> name = new ArrayList<String>();
    double average = 0;
    int count = 0;
    static int i = 0;
    public void setList(String game, int score){
        play.add(++i);
        list.add(score);
        name.add(game);
    }
    
    public void printHistory(){
        System.out.println("-------Game History-------");
        for (int i=0; i<play.size(); i++){
            System.out.println(play.get(i) + " " + name.get(i) + " " + 
                    list.get(i));
        }
    }
    
    public void printStats(String e){
        for(int i=0; i<name.size();i++){
            if (name.get(i).equals(e)){
                average+=list.get(i);
                ++count;
            }
        }
        if (count!=0){
            average/=count;
            System.out.printf(e + " Average: %.2f\n",average);
            count = 0;
            average = 0;
        }
    }
}
