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

        for(int y = height - 1; y >= 0; y--){
            if (boardArr[posPlacerX][y] == ' ') {
                boardArr[posPlacerX][y] = token;
                return true;
            }
        }

        //Column is full
        return false;
    }
}
