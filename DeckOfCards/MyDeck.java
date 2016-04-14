public class MyDeck {
    private MyCard deck[] = new MyCard[52];
    public MyDeck() {
        for (int i = 0; i < 52; i++) {
            deck[i] = new MyCard();
        }
        int suit = 1, rank = 1;
        for (int i = 0; i < 52; i++) {
            deck[i].setRank(rank++);
            deck[i].setSuit(suit);
            if (rank > 13) {
                rank = 1;
                suit++;
            }
        }
    }
    
    public MyCard[] getDeck() {
        return deck;
    }

    public void shuffle() {
        for (int i = 0; i < 52; i++) {
            int x = (int)(Math.random() * (51 - 0) + 1);
            MyCard temp;
            temp = deck[i];
            deck[i] = deck[x];
            deck[x] = temp;
                   
        }
    }

    public void setDeck(MyCard[] deck) {
        this.deck = deck;
    }
    
}
