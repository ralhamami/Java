public class Prog05 {
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        MyDeck deck = new MyDeck();
        for (int i = 0; i < 52; i++) {
            if (i>0 && i%13==0)
                System.out.println();
            System.out.print(deck.getDeck()[i].getSuit());
            System.out.printf("%02d ",deck.getDeck()[i].getRank());
        }
        System.out.println("\n");
        System.out.println("Enter 1 to shuffle or 0 to quit");
        int x = input.nextInt();
        while(x==1) {
        deck.shuffle();
        for (int i = 0; i < 52; i++) {
            if (i>0 && i%13==0)
                System.out.println();
            System.out.print(deck.getDeck()[i].getSuit());
            System.out.printf("%02d ",deck.getDeck()[i].getRank());
        }
        System.out.println("\nShuffle Again. 1 for yes");
        x = input.nextInt();
        }
    }
}
