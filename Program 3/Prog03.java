public class Prog03 {
    public static void main(String[] args) {
        
        //Create Class Objects As Needed
        MyFeet feet = new MyFeet();
        MyMeters meters = new MyMeters();
        
        //Print Heading and Start Print Loop Structure
        System.out.printf("%5s %8s %10s %6s\n","Feet","Meters","Meters","Feet");
        for (int i = 1, j = 20; i <= 10; i++, j+=5) {
            feet.setFeet(i);
            System.out.printf("%2.0f %10.3f",feet.getFeet(),feet.getMeters());
            meters.setMeters(j);
            System.out.printf("%8.0f %13.3f\n",meters.getMeters(),meters.getFeet());
        }
    }
}
