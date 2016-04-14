public class MyMeters {
    private double meters;

    public double getMeters() {
        return meters;
    }
    
    public double getFeet() {
        return meters * 3.2787;
    }

    public void setMeters(double meters) {
        this.meters = meters;
    }
    
}
