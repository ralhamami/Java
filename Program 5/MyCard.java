public class MyCard {
    private int suit;
    private int rank;

    public String getSuit() {
        if (suit == 1)
            return "S";
        else if (suit == 2)
            return "D";
        else if (suit == 3)
            return "H";
        else if (suit == 4)
            return "C";
        return "NONE";
    }
    
    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
       
}
