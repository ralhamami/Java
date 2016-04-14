public class Prog04 {
    public static void main(String[] args) {
        
        //Create necessary objects, variables, and prompt for input
        MyDice dice = new MyDice();
        int x = 0;
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.println("How many times would you like to roll?");
        
        //Continuously loop unless valid range is entered
        while (true) {
            x = input.nextInt();
            if (x > 100000 || x < 1000)
                System.out.println("Please enter a number between"
                        + " 1000 and 100,000:");
            else break;
        }
        
        //Simulate the roll of the dice x number of times
        dice.roll(x);
        
        //Print the results as advised
        System.out.println("The dice was rolled " + x + " times.\n"
                + "the number of 1s = " + dice.getState()[0]
                + "\nthe number of 2s = " + dice.getState()[1]
                + "\nthe number of 3s = " + dice.getState()[2]
                + "\nthe number of 4s = " + dice.getState()[3]
                + "\nthe number of 5s = " + dice.getState()[4]
                + "\nthe number of 6s = " + dice.getState()[5]);
    }
}
