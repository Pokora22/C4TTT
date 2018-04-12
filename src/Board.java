import java.util.HashMap;
import java.util.Random;

public class Board {
    protected int winSpaces, height, width;
    protected char[][] boardArr;
    private final char[] tttLabels = {'A', 'B', 'C'};
    private final String base32Values = "123456789ABCDEFGHIJKLMNOPQRSTUVW";

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

    public void drawBoard() {
        for (int h = 0; h < height; h++) {

            if(h == 0){
                System.out.print("|");
                for(int w2 = 0; w2 < width; w2++) {
                    System.out.print("-" + (base32Values.charAt(w2) + "-|"));
                }

            }
            System.out.println();
            if(width == 3 && height == 3)
                System.out.print(base32Values.charAt(h+9) + " ");
            else
                System.out.print("| ");

            for (int w = 0; w < width; w++) {
                System.out.print(boardArr[w][h] + " | ");
            }

            System.out.print("\n|");
            for(int i = 0; i < width; i++){
                System.out.print("---|");
            }
        }
    }

    protected int convertFromBase32(String input) {
        input = input.toUpperCase();
        System.out.println("The value \""+input+"\" to add to array is: " +base32Values.indexOf(input)); //debug
        return base32Values.indexOf(input);
    }

}
