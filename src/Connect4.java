public class Connect4 extends Board{

    public Connect4(int winSpaces, int height, int width) {
        super(winSpaces, height, width);
    }

    public boolean placeToken(String input, char token) {
        String pattern = "^[1-9A-W]$";
        int posPlacerX;
        input = input.toUpperCase();

        if (input.matches(pattern)){
            posPlacerX = convertFromBase32(input);
        }
        else return false;
        if (posPlacerX>=width) {
            return false;
        }
        for(int y = height - 1; y >= 0; y--){
            if (boardArr[posPlacerX][y] == ' ') {
                boardArr[posPlacerX][y] = token;
                return true;
            }
        }

        //Column is full
        return false;
    }

    public StringBuilder drawBoard() {
        StringBuilder board = new StringBuilder("");
        for (int h = 0; h < height; h++) {
            if (h == 0) {
                board.append("|");
                for (int w2 = 0; w2 < width; w2++) {
                    board.append("-" + (base32Values.charAt(w2) + "-|"));
                }

            }
            board.append("\n| ");

            for (int w = 0; w < width; w++) {
                board.append(boardArr[w][h] + " | ");
            }

            board.append("\n|");
            for (int i = 0; i < width; i++) {
                board.append("---|");
            }
        }
        return board;
    }
}
