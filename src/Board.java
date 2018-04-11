import java.util.HashMap;
import java.util.Random;

public class Board {
    protected int winSpaces, height, width;
    protected char[][] boardArr;
    private char[] tttLabels = {'A', 'B', 'C'};

    public Board(int winSpaces, int height, int width) {
        this.winSpaces = winSpaces;
        this.height = height;
        this.width = width;
    }

    public boolean flipCoin() {

        return false;
    }

    public void drawBoard() {
        for (int h = 0; h < height; h++) {

            if(h == 0){
                System.out.print("|");
                for(int w2 = 0; w2 < width; w2++) {
                    System.out.print("-" + (w2 + 1) + "-|");
                }

            }
            System.out.println();
            if(width == 3 && height == 3)
                System.out.print(tttLabels[h] + " ");
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
}
