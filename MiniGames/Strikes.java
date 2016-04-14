public class Strikes {
    private int count;
    Strikes(Numbers numbers, int input) {
        if ((input%1000)/100 == numbers.getFirst())
            ++count;
        if ((input%100)/10 == numbers.getSecond())
            ++count;
        if ((input%10) == numbers.getThird())
            ++count;
    }

    public int getCount() {
        return count;
    }
}
