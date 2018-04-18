import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Driver {

    private ArrayList<Player> players;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player players;
        Board tttBoard = new TicTacToe(3, 3, 3);
        Board c4Board = new Connect4(4, 10, 10);

        Player p1 = new Player("Name1", '*');
        Player p2 = new Player("Name 2", '#');

        ArrayList<Player> Players = new ArrayList<Player>();

        Players.add(p1);
        Players.add(p2);


        Player currentPlayer = Players.get(0);
        Board currentBoard = tttBoard;


        while(true){
            currentBoard.drawBoard();
            System.out.print("\n"+currentPlayer.getName() +" ("+currentPlayer.getToken()+"), place your token: ");
            if(!currentBoard.placeToken(sc.nextLine(), currentPlayer.getToken()))
                continue;
            if(Players.indexOf(currentPlayer) == 0)
                currentPlayer = Players.get(1);
            else
                currentPlayer = Players.get(0);
        }
    }

    String inputFromCurrentPlayer() {
        return "Nothing now";
    }


    private String listPlayers() {
        String list = "";
        if (players.size()!=0) {
            int index = 0;
            for (Player player : players) {

                index++;
            }
        }
        //TODO: sort all players in a win% order and list them in a string, could potentially split this into sortPlayers(),listPlayers()
        return list;
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("players.xml"));
        players = (ArrayList<Player>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("players.xml"));
        out.writeObject(players);
        out.close();
    }

}
