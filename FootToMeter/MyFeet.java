public class MyFeet {
    private double feet;

    public double getFeet() {
        return feet;
    }
    
    public double getMeters() {
        return feet * .305;
    }

    public void setFeet(double feet) {
        this.feet = feet;
    }
    
}
