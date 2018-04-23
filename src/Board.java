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
                    if (win == 0){
                        return true;
                    }
                }
                else {
                    win = winSpaces;
                }

            }
        }
        win = winSpaces;

        //vertical
        for(int w = 0; w < width; w++){
            for(int h = height - 1; h >= 0; h--) { //check from bottom up to save some calls ?
                if (boardArr[w][h] == player.getToken()) {
                    win--;
                    if (win == 0){
                        return true;
                    }
                }
                else {
                    win = winSpaces;
                }

            }
        }
        win = winSpaces;

        //diagonal x2 ?
        for(int h = height - 1; h >= 0; h--){ //CHANGE!!

            int hc = h;
            for(int w = 0; w < width; w++){

                int wc = w;
                while(wc < width && hc >= 0){
                    if(boardArr[wc][hc] == player.getToken()){
                        win--;
                        if (win == 0){
                            return true;
                        }
                    }
                    else{
                        win = winSpaces;
                    }

                    wc++;
                    hc--;
                }
            }
        }

        for(int h = 0; h < height; h++){
            System.out.println("On row " + h);

            for(int w = 0; w < width; w++){
                int hc = h;
                System.out.println("In column " + w);
                int wc = w;
                while(wc < width && hc < height){
                    System.out.println("Checking [" +wc + "][" + hc + "]");
                    if(boardArr[wc][hc] == player.getToken()){
                        System.out.println(win);
                        win--;
                        System.out.println("Token found");
                        System.out.println(win);

                        if (win == 0){
                            return true;
                        }
                    }
                    else{
                        win = winSpaces;
                    }

                    wc++;
                    hc++;
                }
            }
        }

        return false;
    }

    protected int convertFromBase32(String input) {
        input = input.toUpperCase();
        return base32Values.indexOf(input);
    }

}
