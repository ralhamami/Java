public class Prog01 {
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        int n;
        
        //Input Validation
        do {
            System.out.println("Please enter a number between 1 and 10:");
            n = input.nextInt();
            if (n < 1 || n > 10)
                System.out.println("Out of range");
        }while (n < 1 || n > 10);
        System.out.println();
        MyNumber number = new MyNumber();
        
        //Pattern Printing Loops Using Mutators and Accessors
        for (int i = 1; i <= n; i++) {
            number.setNumber(i);
            System.out.println(number.getSequence());
        }
        for (int i = number.getNumber()-1; i > 0; i--) {
            number.setNumber(i);
            System.out.println(number.getSequence());
        }
    }
}
