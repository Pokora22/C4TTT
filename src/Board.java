import java.util.HashMap;
import java.util.Random;

public abstract class Board {
    protected int winSpaces, height, width;
    protected char[][] boardArr;
    //private final char[] tttLabels = {'A', 'B', 'C'};
    protected final String base32Values = "123456789ABCDEFGHIJKLMNOPQRSTUVW";

    public Board(int winSpaces, int height, int width) {
        this.winSpaces = winSpaces;
        this.height = height;
        this.width = width;

        boardArr = new char[width][height];
        buildBoardArr();
    }

    private void buildBoardArr(){
        for(int h = 0; h < height; h++){
            for(int w = 0; w < width; w++){
                boardArr[w][h] = ' ';
            }
        }
    }

    public boolean flipCoin() {

        return false;
    }

    public abstract void drawBoard();
    public abstract boolean placeToken(String input, char token);

    protected int convertFromBase32(String input) {
        input = input.toUpperCase();
        return base32Values.indexOf(input);
    }

}
