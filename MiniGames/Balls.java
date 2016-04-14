public class Balls {
    private int count;
    Balls(Numbers numbers, int input) {
        if (input%10 == numbers.getFirst() || input%10 == numbers.getSecond())
            ++count;
        if ((input%100)/10 == numbers.getFirst() || (input%100)/10 == numbers.getThird())
            ++count;
        if ((input%1000)/100 == numbers.getSecond() || (input%1000)/100 == numbers.getThird())
            ++count;
    }

    public int getCount() {
        return count;
    }
}
