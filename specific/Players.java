package specific;
import java.util.ArrayList; 
import java.util.Arrays; 


public class Players {
  //Should have: name, Balance
	String name;
	Balance balance;
	ArrayList<Cards> hand;
	
	//This is the in-game attributes of the player
	boolean still_playing; //whether the player did not fold
	int amount_on_bet;
	int score; //this is the score based on the hand that the players has
	
	

  public Players(String name){
    this.name = name;
    this.balance = new Balance(0);
    this.hand = new ArrayList<Cards>();
  }

  //Player should be able to: Hit, Stand, Split, Double up, Bet
  public void Bet(int bet_amount) {
	  this.balance.decreaseBalance(bet_amount);
	  this.amount_on_bet += bet_amount;
  }
  
  public void Hit() {
	  
  }
  
  public void Stand() {
	  //This means you're out of the round
	  this.still_playing = false;
	  this.amount_on_bet = 0; //you lose that bet
  }
  
  public void DoubleUp(Dealer dealer) {
	  this.hand.add(dealer.get)
	  this.amount_on_bet = this.amount_on_bet * 2;
  }
  
  public void score() { //returns the score based on the hand value
	  for(int i = 0; i < this.hand.size(); i++) {
		  score += this.hand.get(i).getValue();
	  }
  }


}
