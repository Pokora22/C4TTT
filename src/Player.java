public class Player {
	
	private String name;
	private char token;
	private int matchesPlayed;
	private int wins;
	private int id;
	private int positionOnLeaderboard;
	
	public Player(int id, String name, char token) {
		if(name.length() <= 30) {
			this.name = name;
		}
		else {
			this.name = name.substring(0, 10);
		}
		this.token = token;
		this.matchesPlayed = 0;
		this.wins = 0;
		this.id = id;
	}
	
	public double calcWinPer() {
		if(matchesPlayed != 0)
			return toTwoDecimalPlaces(((double)wins/matchesPlayed)*100);
		else {
			return 0;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public char getToken() {
		return token;
	}
	
	public int getMatchesPlayed() {
		return matchesPlayed;
	}
	
	public int getWins() {
		return wins;
	}

	public int getId(){
		return id;
	}

	public int getPositionOnLeaderboard() {
		return positionOnLeaderboard;
	}

	public void setName(String name) {
		if(name.length() <= 10) {
			this.name = name;
		}
		else {
			this.name = name.substring(0, 10);
		}
	}
	
	public void setToken(char token) {
		this.token = token;
	}

	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setPositionOnLeaderboard(int positionOnLeaderboard) {
		this.positionOnLeaderboard = positionOnLeaderboard;
	}
	private double toTwoDecimalPlaces(double num) {
        return (int) (num *100 ) /100.0; 
    }

    public String toString()
    {
        return  "ID: " + id
				+ ", Name: " + name
                + ", Token: " + token
                + ", Matches Played: " + matchesPlayed
                + ", Wins: " + wins
                + ", Win Percentage: " + calcWinPer();
    }

    public boolean equals(Player p){
        return (this.getId() == p.getId());
    }
}
