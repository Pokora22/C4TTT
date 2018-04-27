public class TicTacToe extends Board {

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
            ///// (COLUMN, ROW)
            posPlacerY = convertFromBase32(input.substring(0,1)) - 9; //offset Base32
            posPlacerX = convertFromBase32(input.substring(1,2));
        }
        else if (input.matches(patternAlt)){
            posPlacerX = convertFromBase32(input.substring(0,1));
            posPlacerY = convertFromBase32(input.substring(1,2)) - 9;//offset Base32
        }
        else return false;

        if(boardArr[posPlacerX][posPlacerY] == ' ') {
            boardArr[posPlacerX][posPlacerY] = token;
            return true;
        }
        else return false;
    }

    public StringBuilder drawBoard() {
        StringBuilder board = new StringBuilder();

        for (int h = 0; h < height; h++) {

            if(h == 0){
                board.append("|");
                for(int w2 = 0; w2 < width; w2++) {
                    board.append("-").append(super.base32Values.charAt(w2)).append("-|");
                }

            }
            board.append("\n");

            board.append(super.base32Values.charAt(h+9) + " ");

            for (int w = 0; w < width; w++) {
                board.append(boardArr[w][h] + " | ");
            }

            board.append("\n|");
            for(int i = 0; i < width; i++){
                board.append("---|");
            }
        }
        return board;
    }
}
