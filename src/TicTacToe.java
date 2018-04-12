import java.util.HashMap;

public class TicTacToe extends Board {
    private char[] labels;

    public TicTacToe(int winSpaces, int height, int width) {
        super(winSpaces, height, width);
    }

    public boolean placeToken(String input, char token) { //FIX ! ! FINISH ! !
        String pattern = "^[A-C][1-3]$";
        String patternAlt = "^[1-3][A-C]$";
        input = input.toUpperCase();
        int posPlacerX;
        int posPlacerY;

        if (input.matches(pattern)) {
            ////// (COLUMN, ROW)
            posPlacerY = convertFromBase32(input.substring(0,1)) - 9; //offset Base32
            posPlacerX = convertFromBase32(input.substring(1,2));
            System.out.println("First pattern");
        }
        else if (input.matches(patternAlt)){
            posPlacerX = convertFromBase32(input.substring(0,1));
            posPlacerY = convertFromBase32(input.substring(1,2)) - 9;//offset Base32
            System.out.println("Alt pattern");
        }
        else return false;

        System.out.println("y: " + posPlacerY + " x: " + posPlacerX);
        if(boardArr[posPlacerX][posPlacerY] == ' ') {
            boardArr[posPlacerX][posPlacerY] = token;
            return true;
        }
        else return false;
    }
}

