import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Board tttBoard = new TicTacToe(3, 3, 3);
        Board c4Board = new Connect4(4, 10, 10);

        Player p1 = new Player("Name1", '*');
        Player p2 = new Player("Name 2", '#');

        ArrayList<Player> Players = new ArrayList<Player>();

        Players.add(p1);
        Players.add(p2);


        Player current = Players.get(0);

        while(true){
            c4Board.drawBoard();
            System.out.print("\n"+current.getName() +" ("+current.getToken()+"), place your token: ");
            if(!c4Board.placeToken(sc.nextLine(), current.getToken()))
                continue;
            if(Players.indexOf(current) == 0)
                current = Players.get(1);
            else
                current = Players.get(0);
        }
    }

    String inputFromCurrentPlayer() {
        return "Nothing now";
    }

}
