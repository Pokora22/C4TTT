import java.util.ArrayList;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Driver {
    private Random random;
    private ArrayList<Player> players;
    private InputOutput io;

    public Driver(){
        io = new InputOutput();
        players = new ArrayList<Player>();
        random = new Random();
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        try {
            driver.load();
        } catch (Exception e) {
            driver.io.outLine("Error: " + e +"\nPlayer file is missing!\nPress any key to exit...");
            driver.io.readString();
            System.exit(0);
        }

        driver.runMenu();
    }

    private void runMenu()
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
                    io.outLine("Invalid option entered.\nPress any key to continue...");
                    io.readString();
                    break;
            }
        }
    }


    /**
     * Initializes the board for the game chosen
     * @param game - integer deciding which game to play (1: Connect 4, other: TicTacToe)
     * @return object of the game board initialized
     */
    private Board initializeBoard(int game) {
        if (game == 1) {
            int x, y, w;

            x = io.readNumber("Enter Board Width (maximum of 32): ",
                    4, 33,
                    "Wrong input format or out of bounds, try again.");
            y = io.readNumber("Enter Board Height: ",
                    "Wrong input format, try again.");

            w = io.readNumber("How many tokens in row should win (can not be higher than board size): ",
                    1, x > y ? y+1 : x+1,
                    "Wrong input format or out of bounds, try again.");

            return new Connect4(w, y, x);
        }

        else {
            return new TicTacToe(3, 3, 3);
        }
    }


    private Player initializePlayer(){
        while(true) {
            io.out("What would you like to do:\n" +
                    "1) Create a new player\n" +
                    "2) Load an existing player\n");

            switch (io.readFirstChar("==> ")) {
                case '1':
                    String name = io.readString("Enter player name: ", "Cannot be empty, try again.");
                    players.add((new Player(players.size(), name, io.readFirstChar("Enter player token: ", "Can't be empty, try again"))));
                    return (players.get(players.size() - 1));
                case '2':
                    io.out(listPlayers().toString());
                    if (players.size() > 0) {
                        return playerById(io.readNumber("\nChoose player: ", 0, players.size(), "No player with given id."));
                    }
                    else
                        io.out("There is not enough players to load from list\nPress any key to continue...");
                        io.readString();
                        continue;
                default:
            }
        }
    }

    private void startGame(Board boardToPlay, Player p1, Player p2) {
        char p2TokenStore = p2.getToken();
        if(p1.getToken() == p2.getToken()) {
            p2.setToken(io.readFirstChar("Token collision detected,\nPlayer 2, please change your token:  "));
        }
        Player currentPlayer = random.nextInt(2) == 0 ? p1 : p2;

        boolean gameWon = false; //need to initialize for check before switching players
        while(!gameWon) {
            io.out(boardToPlay.drawBoard().toString() +"\n" +
                    currentPlayer.getName() + " (" + currentPlayer.getToken()+ "), place your token: ");

            if(!boardToPlay.placeToken(io.readString(), currentPlayer.getToken()))
                continue;

            if(boardToPlay.checkWin(currentPlayer)) {
                io.out(boardToPlay.drawBoard().toString() +"\n" +
                        currentPlayer.getName() + " has won!\n");
                currentPlayer.setWins(currentPlayer.getWins()+1);
                break;
            }

            if(boardToPlay.boardFull()){
                io.out(boardToPlay.drawBoard() + "\n" +
                        "Game ended with a draw!");
                break;
            }

            if(currentPlayer.equals(p1))
                currentPlayer = p2;
            else
                currentPlayer = p1;
        }

        p1.setMatchesPlayed(p1.getMatchesPlayed()+1);
        if(p2.equals(p1))
            io.out("Congratulation! You played yourself!");
        else p2.setMatchesPlayed(p2.getMatchesPlayed() + 1);

        try {
            assignLeaderboardPositions();
            p2.setToken(p2TokenStore);
            save();
        }
        catch (Exception e) {
            io.out("Error saving players, progress is lost");
        }
    }

    private StringBuilder listPlayers(){
        StringBuilder list = new StringBuilder();
        if (players.size()!=0) {
            for (int i = 1; i < players.size()+1; i++) {
                for (Player player : players) {
                    if (player.getPositionOnLeaderboard()==i) {
                        list.append(players.indexOf(player)).append(": ").append(player).append("\nPlace on the leaderboard: ").append(player.getPositionOnLeaderboard()).append("\n");
                    }
                }
            }
        }
        return list;
    }
    private void assignLeaderboardPositions() {
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

    private char mainMenu()
    {
        io.out("Which game do you want to play?\n" +
                "---------\n" +
                "  1) Connect Four\n" +
                "  2) Tic Tac Toe\n" +
                "  0) Exit\n");
        return io.readFirstChar("==>> ");
    }



    private Player playerById(int id){
        for (Player p : players) {
            if(p.getId() == id)
                return p;
        }

        return null; //no player - check in other place for errors
    }


    @SuppressWarnings("")
    private void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("players.xml"));
        players = (ArrayList<Player>) is.readObject();
        is.close();
    }

    private void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("players.xml"));
        out.writeObject(players);
        out.close();
    }
}
