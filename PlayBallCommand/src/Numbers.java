public class Numbers {
    private int first;
    private int second;
    private int third;
    Numbers() {
        first = (int)(Math.random() * (9 - 0) + 1);
        do {
            second = (int)(Math.random() * (9 - 0) + 1);
        } while(first==second);
        do {
            third = (int)(Math.random() * (9 - 0) + 1);
        } while(third == second || third == first);
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getThird() {
        return third;
    }
    
    public int getWhole() {
        return ((first*100)+(second*10)+third);
    }
}
