public class MyDice {
    private int state[] = new int[6];

    public int[] getState() {
        return state;
    }

    public void roll(int n) {
        //Start a loop to generate a random number n times
        for (int i = 0; i < n; i++) {
            int x = (int)(Math.random() * (7 - 1) + 1);
            
            //Store the result accordingly in state array
            switch (x) {
                case 1: ++state[0];
                        break;
                case 2: ++state[1];
                        break;
                case 3: ++state[2];
                        break;
                case 4: ++state[3];
                        break;
                case 5: ++state[4];
                        break;
                case 6: ++state[5];
                        break;
            }
        }
    }
}
