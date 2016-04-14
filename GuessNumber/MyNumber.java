public class MyNumber {
    private int number;

    public int getNumber() {
        return number;
    }
    
    public String getSequence() {
        String temp = "";
        for (int i = 1; i <= number; i++)
           temp += i + " "; 
        return temp;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
}
