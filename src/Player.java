public class Player {
	
	private String name;
	private char token;
	private int matchesPlayed;
	private int wins;
	
	public Player(String name, char token, int matchesPlayed, int wins) {
		if(name.length() <= 30) {
			this.name = name;
		}
		else {
			this.name = name.substring(0, 10);
		}
		this.token = token;
		this.matchesPlayed = matchesPlayed;
		this.wins = wins;
	}
	
	public double calcWinPer() {
		return toTwoDecimalPlaces((wins/matchesPlayed)*100);
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
	
	private double toTwoDecimalPlaces(double num) {
        return (int) (num *100 ) /100.0; 
    }

    public String toString()
    {
        return "Name: " + name
                + ", Token: " + token
                + ", Matches Played: " + matchesPlayed
                + ", Wins: " + wins
                + ", Win Percentage: " + calcWinPer();
    }
}
