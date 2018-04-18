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
    private Scanner input;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player players;
        Board tttBoard = new TicTacToe(3, 3, 3);
        Board c4Board = new Connect4(4, 10, 10);

        Player p1 = new Player("Name1", '*');
        Player p2 = new Player("Name 2", '#');

        ArrayList<Player> Players = new ArrayList<Player>();
        Driver app = new Driver();

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

    private void Driver() {
        input = new Scanner(System.in);
        runMenu();
    }
    String inputFromCurrentPlayer() {
        return "Nothing now";
    }


     private int mainMenu()
    {
        System.out.println("Which game do you want to play?");
        System.out.println("---------");
        System.out.println("  1) Connect Four");
        System.out.println("  2) Tic Tac Toe");
        System.out.println("  0) Exit");
        System.out.print("==>> ");
        int option = input.nextInt();
        return option;
    }

    private void runMenu()
    {
        int option = mainMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:    initializeGame(option);
                    break;
                case 2:    initializeGame(option);
                    break;
                default:    System.out.println("Invalid option entered: " + option);
                    break;
            }

            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();

            option = mainMenu();
        }

        System.out.println("Exiting... bye");
        System.exit(0);
    }

    private void initializeGame(int game) {
        if (game == 1) {
            int x;
            int y;
            System.out.print("Enter Board Width");
            x = input.nextInt();
            if(x<4 || x>32) {
                x = 4;
            }
            System.out.print("Enter Board Height");
            y = input.nextInt();
            Board c4Board = new Connect4(4, y, x);
        }

        if (game == 2) {
            Board tttBoard = new TicTacToe(3, 3, 3);
        }
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
