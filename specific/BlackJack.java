package specific;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class BlackJack {
	//attributes
	Deck deck;
	Dealer dealer; //I believe the dealer
	int round; //which round
	int turn; //whose turn this is
	boolean split_exist;
	ArrayList<Players> split_case = new ArrayList<Players>();

	public BlackJack() {
		this.deck = new Deck();
		this.dealer = new Dealer();
		this.round = 0;
		this.split_exist = false;
	}

	public void playGame(Players player) {
		//startGame();
		//this.turn = 0;
		//after initializing players, we keep playing until all the players are out...
		
	while(player.balance.getMoney() >= 0) {//until we have player is out of money
      System.out.println("Your current balance is: " + player.getBalance());
      System.out.println("How much do you want to bet?");
      Boolean validBet = true;
      while(validBet){
        Scanner scan = new Scanner(System.in);
        int bet = scan.nextInt();
        if(bet > 0 && bet <= player.balance.getMoney()){
          validBet= false;
          player.Bet(bet);
        }
        else{
          System.out.println("invalid bet!");
        }
      }
      System.out.println(player.getBet() + " is a valid bet!");


      dealer.getdhand(deck); //sets dealers dhand
      dealer.updatescore();
      //set Player hand
      player.getPlayerHand(deck);
      player.score();
      player.printHand();
      player.printScore();
      player.still_playing = true;

      //This is where the game actually beginss
      while(player.still_playing) {
    	  if(this.split_exist) {
    		  Scanner scan = new Scanner(System.in);
    		  for(int i = 0; i < this.split_case.size(); i++) {
    			  Players current_hand = this.split_case.get(i);
    			  System.out.println("for hand " + i + "what action do you want to do");
    			  current_hand.printHand();
    			  choose(current_hand);
    		  }
    	  }
    	  else {
    		  choose(player);
    	  }
      	}
    }
  }
	
	private void choose(Players player) {
		  if(player.split) {
			  split_input(player);
		  }
		  else {
			  normal_input(player);
		  }
	}
	
	private void split_input(Players player) {
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
		  	case 3:
		  		Split(player);
		  		break;
			default:
				System.out.println("Enter a correct value");
		  }
	}
	
	private void normal_input(Players player) {
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

	//Copied from the TicTacToe game :)
	public Players createPlayer() { //initalize player and its balance
		Scanner scan = new Scanner(System.in);
		System.out.println("enter your name: ");
		String name = scan.nextLine();
		Players player = new Players(name);
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
		  Hit(a);
		  a.still_playing = false;
	  }
	  
	  public void Split(Players a) {
		  // Split -> the player has to split their hand into two, can we just make another player???
		  Players temp = new Players();
		  a.hand.remove(0); //creates two new hand for both players
		  //
		  Hit(a);
		  //add a  new player 
		  Hit(temp);
		  this.split_case.add(temp);
	  }


}


