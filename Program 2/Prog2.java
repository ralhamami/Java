public class Prog2 {
    public static void main(String[] args){
        MyCD cd = new MyCD();
        java.util.Scanner input = new java.util.Scanner(System.in);
        double number = 0, value = 0;
        
        //3 LOOPS FOR INPUT VALIDATION
        while (number < 1) {
            System.out.println("Enter the principal:");
            number = input.nextDouble();
            cd.setPrincipal(number);
            if (number < 1) System.out.println("Please input value greater than 0.");
        }
        do{
            System.out.println("Enter the annual percentage rate:");
            number = input.nextDouble();
            cd.setAnnualRate(number);
            if (number < 1) System.out.println("Please input value greater than 0.");
        }while(number < 1);
        do{
            System.out.println("Enter the number of months:");
            number = (int)input.nextDouble();
            cd.setNumberOfMonth((int)number);
            if (number < 1) System.out.println("Please input value greater than 0.");
        }while(number < 1);
        
        //LOOP TO PRINT TABLE
        System.out.println("Month   CD Value");
        for (int i = 1; i <= cd.getNumberOfMonth(); i++) {
            if (i==1)
                value = cd.getPrincipal();
            value += value * cd.getAnnualRate() / 12;
            //System.out.print(i);
            System.out.printf("%2d %13.2f\n",i,value);
        }
    }
}
