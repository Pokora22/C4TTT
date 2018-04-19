import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Driver {

    private ArrayList<Player> players;
    private Scanner input;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Player players;
        Board tttBoard = new TicTacToe(3, 3, 3);
        Board c4Board = new Connect4(4, 10, 10);

        Player p1 = new Player("Name1", '*',0,0);
        Player p2 = new Player("Name 2", '#',0,0);

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

    private static int readNumber(){
        while(true){
            String input = sc.nextLine();
            if(input.matches("^\\d+$"))
                return Integer.valueOf(input);
            else {
                System.out.println("Wrong input format, try again.");
            }
        }
    }

    private char mainMenu()
    {
        System.out.println("Which game do you want to play?");
        System.out.println("---------");
        System.out.println("  1) Connect Four");
        System.out.println("  2) Tic Tac Toe");
        System.out.println("  0) Exit");
        System.out.print("==>> ");
        char option = input.nextLine().charAt(0);
        return option;
    }

    private void runMenu()
    {
        while (true)
        {
            switch (mainMenu())
            {
                case '1':
                    startGame(initializeBoard(1), initializePlayers());
                    //discard board code
                    break;
                case '2':
                    startGame(initializeBoard(2), initializePlayers());
                    break;
                case '0':
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option entered.\nPress any key to continue...");
                    sc.nextLine();
                    break;
            }
        }
    }



    private Board initializeBoard(int game) {
        if (game == 1) {
            System.out.print("Enter Board Width: ");
            int x = readNumber();
            if(x<4 || x>32) {
                x = 4;
            }

            System.out.print("Enter Board Height: ");
            int y = readNumber();

            System.out.print("How many tokens in row should win: ");
            int w = readNumber();
            if(w < 4 || w > x || w > y)
                w = 4;

            return new Connect4(w, y, x);
        }

        else {
            return new TicTacToe(3, 3, 3);
        }
    }

    private Player[] initializePlayers(){
        Player[] currPlayers = new Player[2];
        //code to create/load players into p1/p2
    //    startGame();
        //nothing
        return currPlayers;
    }

    public void startGame(Board boardToPlay, Player[] currPlayers) {

        while(!win) {
            //gameplay takes place here
        }

        //add curr players to the saved scores - update and shit
    }

    private String listPlayers() {
        String list = "";
        if (players.size()!=0) {
            for (Player player : players) {
                double currPlayerWinPer = player.calcWinPer();
                list = list + players.indexOf(player) + ": " + player + "\nPlace on the leaderboard: " + locatePlayer(currPlayerWinPer) + "\n";
            }
        }
        return list;
    }
    private int locatePlayer(double currPlayerWinPer) {
        int position = 1;
        for(int i = 0; i < players.size() ; i++) {
            double nextPlayerWinPer=players.get(i).calcWinPer();
            if(currPlayerWinPer<nextPlayerWinPer) {
                position++;
            }
        }
        return position;
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
