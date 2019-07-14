package specific;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
	//attributes
	Deck deck;
	Dealer dealer; //I believe the dealer
	int round; //which round
	int turn; //whose turn this is

	public BlackJack() {
		this.deck = new Deck();
		this.dealer = new Dealer();
		this.round = 0;
	}

	public void playGame(Players player) {
		//startGame();
		//this.turn = 0;
		//after initializing players, we keep playing until all the players are out...
		while(player.balance.getMoney() <= 0) { //until we have 1 winner
			dealer.getdhand(deck); //sets dealers dhand


      //we need to add the things that happen before
      //Todo





      Scanner scan = new Scanner(System.in);
  		System.out.println(" What action do you want to do?: 0 for Hit, 1 for Stand, 2 for DoubleUp");
  		int input = scan.nextInt();
  		  switch(input) {
  		  	case 0:
  			     Hit(player);
  			     break;
  		  	case 1:
  		  	   Stand(player);
  		  	   break;
  		  	case 2:
  		  	   DoubleUp(player);
  			     break;
  			default:
  				System.out.println("Enter a correct value");
      }
    }
  }


	//Copied from the TicTacToe game :)
	public Players createPlayer() { //initalize player and its balance
	Scanner scan = new Scanner(System.in);
		System.out.println("enter your name: ");
		String name = scan.nextLine();
		Players player = new Players(name);
    System.out.println("enter starting balance:");
    int b = scan.nextInt();
    player.updatebalance(b);
		return player;
	}

	private void reset() { //a function that resets everything
		this.deck = new Deck();
		this.round = 0;
	}

  //todo
	//Logic components/Actions
	  public void Hit(Players a) {//0 for hit
		  //remove a card from deck
		  //add a card to Player
		  Cards hit = this.dealer.getCard(this.deck);
		  a.hand.add(hit); //add that card
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
