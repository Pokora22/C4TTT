import java.util.HashMap;
import java.util.Random;

public abstract class Board {
    protected int winSpaces, height, width;
    protected char[][] boardArr;
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

    public abstract void drawBoard();
    public abstract boolean placeToken(String input, char token);

    public boolean checkWin(Player player){
        int win = winSpaces;

        //horizontal
        for(int h = 0; h < height; h++){
            for(int w = 0; w < width; w++) {
                if (boardArr[w][h] == player.getToken()) {
                    win--;
                }
                else {
                    win = winSpaces;
                }
                if (win == 0){
                    return true;
                }
            }
        }

        //vertical
        for(int w = 0; w < width; w++){
            for(int h = height - 1; h >= 0; h--) { //check from bottom up to save some calls ?
                if (boardArr[w][h] == player.getToken()) {
                    win--;
                }
                else {
                    win = winSpaces;
                }
                if (win == 0){
                    return true;
                }
            }
        }

        //diagonal x2 ?
        for(int w = 0; w < width; w++){

        }

        return false;
    }

    protected int convertFromBase32(String input) {
        input = input.toUpperCase();
        return base32Values.indexOf(input);
    }

}
