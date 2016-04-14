public class Board {
    private char[][]gb = new char[3][3];
    private char turn = 'o';  

    public void makeMove(int row, int column) {
            if (turn == 'x') {
                gb[row][column] = 'X';
                turn = 'o';
            }
            else {
                gb[row][column] = 'O';
                turn = 'x';
            }
    }
    
    public void printBoard() {
        System.out.print("+---+---+---+\n"
                       + "| " + gb[0][0] + " | " + gb[0][1] + " | " + gb[0][2] + " |\n"
                       + "+---+---+---+\n"
                        + "| " + gb[1][0] + " | " + gb[1][1] + " | " + gb[1][2] + " |\n"
                       + "+---+---+---+\n"
                        + "| " + gb[2][0] + " | " + gb[2][1] + " | " + gb[2][2] + " |\n"
                       + "+---+---+---+\n");
    }
    
    public boolean gameWon() {
        if (gb[0][0]==gb[0][1] && gb[0][0]==gb[0][2] && gb[0][0]!= ' ')
            return true;
        if (gb[1][0]==gb[1][1] && gb[1][0]==gb[1][2] && gb[1][0]!= ' ')
            return true;
        if (gb[2][0]==gb[2][1] && gb[2][0]==gb[2][2] && gb[2][0]!= ' ')
            return true;
        if (gb[0][0]==gb[1][0] && gb[0][0]==gb[2][0] && gb[0][0]!= ' ')
            return true;
        if (gb[0][1]==gb[1][1] && gb[0][1]==gb[2][1] && gb[0][1]!= ' ')
            return true;
        if (gb[0][2]==gb[1][2] && gb[0][2]==gb[2][2] && gb[0][2]!= ' ')
            return true;
        if (gb[0][0]==gb[1][1] && gb[0][0]==gb[2][2] && gb[0][0]!= ' ')
            return true;
        if (gb[2][0]==gb[1][1] && gb[2][0]==gb[0][2] && gb[2][0]!= ' ')
            return true;
        else
            return false;
    }
    
    public boolean isValid(int row, int column) {
        if (gb[row][column] == 'O' || gb[row][column] == 'X')
            return false;
        return true;
    }
    
    public char[][] getGb() {
        return gb;
    }
    
    public char getTurn() { 
        return turn;
    }
    
    public boolean full() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (gb[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }
    
    public void clear() {
        for (int i=0; i<3; i++) {
                    for (int j=0; j<3; j++) {
                            gb[i][j] = ' ';
                    }
            }
    }
}
