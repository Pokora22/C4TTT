public class TicTacToe extends Board {
    private int posPlacerX;
    private int posPlacerY;

    void tokenPlacer(String input) {

        ////// (COLUMN, ROW)
        if(input.contains("A"))
            this.posPlacerY=0;
        if(input.contains("B"))
            this.posPlacerY=1;
        if(input.contains("C"))
            this.posPlacerY=2;
        if(input.contains("1"))
            this.posPlacerX=0;
        if(input.contains("2"))
            this.posPlacerX=1;
        if(input.contains("3"))
            this.posPlacerX=2;
    }
}
