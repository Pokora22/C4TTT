import java.util.HashMap;

public class TicTacToe extends Board {
    private char[] labels;

    public TicTacToe(int winSpaces, int height, int width) {
        super(winSpaces, height, width);

        boardArr = new char[width][height];
        buildMapArr();
        labels = new char[] {'A', 'B', 'C'};

    }

    private void buildMapArr(){
        for(int h = 0; h < height; h++){
            for(int w = 0; w < width; w++){
                boardArr[w][h] = ' ';
            }
        }
    }


    public boolean placeToken(String input, char token) {
        int posPlacerX;
        int posPlacerY;

        input = input.toUpperCase();

        ////// (COLUMN, ROW)
        if(input.contains("A"))
            posPlacerY = 0;
        else if(input.contains("B"))
            posPlacerY=1;
        else if(input.contains("C"))
            posPlacerY=2;
        else return false;

        if(input.contains("1"))
            posPlacerX=0;
        else if(input.contains("2"))
            posPlacerX=1;
        else if(input.contains("3"))
            posPlacerX=2;
        else return false;


        if(boardArr[posPlacerX][posPlacerY] == ' ') {
            boardArr[posPlacerX][posPlacerY] = token;
            return true;
        }

        return false;
    }
}
