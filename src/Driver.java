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

    private static ArrayList<Player> players = new ArrayList<Player>();
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
                    startGame(initializeBoard(1), initializePlayer(), initializePlayer());
                    //discard board code
                    break;
                case '2':
                    startGame(initializeBoard(2), initializePlayer(), initializePlayer());
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

    private static Player initializePlayer(){

            //if loading - do stuff

            //if creating new
            if (true) {
                //ask all the info for player - \/ are placeholders
                System.out.print("Enter player name: ");
                String name = sc.nextLine();
                System.out.print("Enter player token: ");
                char token = sc.nextLine().charAt(0);
                players.add((new Player(players.size(), name, token)));
                return (players.get(players.size() - 1));
            }

            return null;
    }

    private static void startGame(Board boardToPlay, Player p1, Player p2) {
        Player currentPlayer = p1; //change to coin flip later
        boolean gameWon = false; //need to initialize for check before switching players
        while(!gameWon) {
            boardToPlay.drawBoard();
            System.out.print("\n"+currentPlayer.getName() +" ("+currentPlayer.getToken()+"), place your token: ");
            if(!boardToPlay.placeToken(sc.nextLine(), currentPlayer.getToken())) //ask Siobhane about dispatch calls - placeToken seems to get called AFTER checkWin condition (log says token not found)
                continue;

            if(gameWon = boardToPlay.checkWin(currentPlayer)) {
                boardToPlay.drawBoard();
                break;
            }

            if(currentPlayer.equals(p1))
                currentPlayer = p2;
            else
                currentPlayer = p1;
            //gameplay takes place here
        }
        System.out.println("\n" + currentPlayer.getName() + " has won!");

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

    private static Player playerById(int id){
        for (Player p : players) {
            if(p.getId() == id)
                return p;
        }

        return null; //no player - check in other place for errors
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
