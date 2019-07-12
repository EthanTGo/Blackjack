package specific;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
	//attributes
	Deck default_deck;
	Dealer dealer;
	ArrayList<Players> num_player;
	int round; //which round
	int turn; //whose turn this is
	
	public BlackJack() {
		this.default_deck = new Deck();
		this.dealer = new Dealer(default_deck);
		this.num_player = new ArrayList<Players>();
		this.round = 0;
	}
	
	public void playGame() {
		startGame();
		//after initializing players, we keep playing until all the players are out...
		while(this.num_player.size() > 1) { //until we have 1 winner
			
		}
	}
	
	
	//Copied from the TicTacToe game :)
	private Players createPlayers() {
		Scanner scan = new Scanner(System.in);
		System.out.println("enter your name: ");
		String name = scan.nextLine();	
		Players player = new Players(name);
		return player;
	}
	
	private void startGame() {
		Scanner scan = new Scanner(System.in);
		System.out.println("How many players are there: ");
		int num_players = scan.nextInt();
		for(int i = 0; i < num_players; i++) {
			//Create a new players for each input
			this.num_player.add(createPlayers());
		}
	}
	
	private void reset() { //a function that resets everything
		this.default_deck = new Deck();
		this.num_player = new ArrayList<Players>();
		this.round = 0;
		playGame();
	}
	
	//Logic components
	
	//main method
	
	
}
