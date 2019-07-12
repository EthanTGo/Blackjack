package specific;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
	//attributes
	Deck default_deck;
	Dealer dealer; //I believe the dealer
	ArrayList<Players> num_player;
	int round; //which round
	int turn; //whose turn this is

	public BlackJack() {
		this.default_deck = new Deck();
		this.dealer = new Dealer();
		this.num_player = new ArrayList<Players>();
		this.round = 0;
	}

	public void playGame() {
		startGame();
		this.turn = 0;
		//after initializing players, we keep playing until all the players are out...
		while(this.num_player.size() > 1) { //until we have 1 winner
			int action_to_be_taken = num_player.get(turn).Play();
			switch(action_to_be_taken) {
			case 0:
				Hit(num_player.get(turn));
				break;
			case 1:
				Stand(num_player.get(turn));
				break;
			case 2:
				DoubleUp(num_player.get(turn));
				break;
			case 3:
				break;
			}
			if(this.num_player.size() == 1) {
				System.out.println("Okay, everybody have finished deciding");
				break;
			}
			nextValidTurn();
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

	private void nextValidTurn() {
		//find the next valid turn
		if(turn + 1 > this.num_player.size()-1) {
			turn = -1;
		}
		for(int i = turn+1; i < this.num_player.size(); i++) {
			//turn can be 0,1,2,...,size - 1
			if(this.num_player.get(i).still_playing) {
				this.turn = i;
				break;
			}
			else if(turn + 1 > this.num_player.size()-1) {
				i = 0;
			}
		}
	}

	//Logic components/Actions
	  public void Hit(Players a) {//0 for hit

	  }

	  public void Stand(Players a) { //1 for stand
		  //This means you're out of the round but you're score is still there
		  a.still_playing = false;

	  }

	  public void DoubleUp(Players a) { //2 for Doubleup
		  //the blackjack function will give me that hand
		  a.amount_on_bet = a.amount_on_bet * 2;

	  }

	//main method

	
}
