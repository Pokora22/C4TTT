import java.util.Scanner;


public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TicTacToe tttBoard = new TicTacToe(3, 32, 32);
        Board c4Board = new Connect4(4, 10, 10);

        while(true){
            tttBoard.drawBoard();
            tttBoard.placeToken(sc.nextLine(), 'T');
        }
    }

    String inputFromCurrentPlayer() {
        return "Nothing now";
    }

}
