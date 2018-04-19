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

    private static ArrayList<Player> players;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        runMenu();
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

    private static char mainMenu()
    {
        System.out.println("Which game do you want to play?");
        System.out.println("---------");
        System.out.println("  1) Connect Four");
        System.out.println("  2) Tic Tac Toe");
        System.out.println("  0) Exit");
        System.out.print("==>> ");
        return sc.nextLine().charAt(0);
    }

    private static void runMenu()
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



    private static Board initializeBoard(int game) {
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

    private static ArrayList<Player> initializePlayers(){
        ArrayList<Player> currPlayers = new ArrayList<Player>(2);
        //code to create/load players into p1/p2
        //nothing
        currPlayers.add(new Player("Name1", '*',0,0)); // 2 placeholders
        currPlayers.add(new Player("Name 2", '#',0,0));
        return currPlayers;
    }

    private static void startGame(Board boardToPlay, ArrayList<Player> currPlayers) {
        Player currentPlayer = currPlayers.get(0);

        while(true) { //switch to !win later
            boardToPlay.drawBoard();
            System.out.print("\n"+currentPlayer.getName() +" ("+currentPlayer.getToken()+"), place your token: ");
            if(!boardToPlay.placeToken(sc.nextLine(), currentPlayer.getToken()))
                continue;
            if(currPlayers.indexOf(currentPlayer) == 0)
                currentPlayer = currPlayers.get(1);
            else
                currentPlayer = currPlayers.get(0);
            //gameplay takes place here
        }

        //add curr players to the saved scores - update and shit
    }

    private static StringBuilder listPlayers() {
        StringBuilder list = new StringBuilder();

        if (players.size()!=0) {
            for (Player player : players) {
                double currPlayerWinPer = player.calcWinPer();
                list.append(players.indexOf(player) + ": " + player + "\nPlace on the leaderboard: " + locatePlayer(currPlayerWinPer) + "\n");
            }
        }
        return list;
    }
    private static int locatePlayer(double currPlayerWinPer) {
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
    private static void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("players.xml"));
        players = (ArrayList<Player>) is.readObject();
        is.close();
    }

    private static void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("players.xml"));
        out.writeObject(players);
        out.close();
    }

}
