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
        while(true) {
            System.out.println("What would you like to do:");
            System.out.println("1) Create a new player");
            System.out.println("2) Load an existing player");
            System.out.print("==>");
            char option = sc.nextLine().charAt(0);
            switch (option) {
                case '1':
                    System.out.print("Enter player name: ");
                    String name = sc.nextLine();
                    String tokenStr;
                    do {
                        System.out.print("Enter player token: ");
                        tokenStr = sc.nextLine();
                    }while(tokenStr.equals(""));
                    players.add((new Player(players.size(), name, tokenStr.charAt(0))));
                    return (players.get(players.size() - 1));
                case '2':
                    System.out.println(listPlayers());
                    if (players.size()>1) {
                        System.out.println("\nChoose player: ");
                        return playerById(readNumber());
                    }
                    else
                        System.out.println("There is not enough players to load from list\nPress any key to continue...");
                        sc.nextLine();
                        continue;
                default:
                    continue;
            }
        }
    }

    private static void startGame(Board boardToPlay, Player p1, Player p2) {
        Player currentPlayer = p1; //change to coin flip later
        boolean gameWon = false; //need to initialize for check before switching players
        while(!gameWon) {
            System.out.println(boardToPlay.drawBoard());
            System.out.print("\n"+currentPlayer.getName() +" ("+currentPlayer.getToken()+"), place your token: ");
            if(!boardToPlay.placeToken(sc.nextLine(), currentPlayer.getToken()))
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
        p1.setMatchesPlayed(p1.getMatchesPlayed()+1);
        p2.setMatchesPlayed(p2.getMatchesPlayed()+1);
        System.out.println("\n" + currentPlayer.getName() + " has won!");

        try {
            currentPlayer.setWins(currentPlayer.getWins()+1);
            assignLeaderboardPositions();
            save();
        }
        catch (Exception e) {
            System.out.println("Error saving players, progress is lost");
        }
    }

    private static StringBuilder listPlayers(){
        StringBuilder list = new StringBuilder();
        try {
            load();
        }
        catch (Exception e) {

        }

        if (players.size()!=0) {
            for (int i = 1; i < players.size()+1; i++) {
                for (Player player : players) {
                    if (player.getPositionOnLeaderboard()==i) {
                        list.append(players.indexOf(player) + ": " + player + "\nPlace on the leaderboard: " + player.getPositionOnLeaderboard() + "\n");
                    }
                }
            }
        }
        return list;
    }
    private static void assignLeaderboardPositions() {
        for (Player player : players) {
            int position = 1;
            for (Player pToCompare : players) {
                if (player.calcWinPer() < pToCompare.calcWinPer()) {
                    player.setPositionOnLeaderboard(++position);
                }
                else{
                    player.setPositionOnLeaderboard(position);
                }
            }
        }
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
