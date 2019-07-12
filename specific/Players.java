package specific;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Scanner; 


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
  public void Play() {
	  Scanner scan = new Scanner(System.in);
	  if(split) { //if the split is not possible
		  System.out.println(this.name + " What action do you want to do?: 0 for Hit, 1 for Stand, 2 for DoubleUp, 3 for Split");
	  }
	  else {
		  System.out.println(this.name + " What action do you want to do?: 0 for Hit, 1 for Stand, 2 for DoubleUp");
		  int input = scan.nextInt();
		  switch(input) {
		  	case 0:
			  Hit();
			  break;
		  	case 1:
		  	  Stand();
		  	  break;
		  	case 2:
		  	  DoubleUp();
			  break;
			default:
				System.out.println("Enter a correct value");
		  }
	  }  
  }
  
  
  
  
  public void Bet(int bet_amount) {
	  this.balance.decreaseBalance(bet_amount);
	  this.amount_on_bet += bet_amount;
  }
  
  public int Hit() {//0 for hit
	  return 0;
  }
  
  public int Stand() { //1 for stand
	  //This means you're out of the round but you're score is still there
	  this.still_playing = false;
	  return 1;
  }
  
  public int DoubleUp() { //2 for Doubleup
	  //the blackjack function will give me that hand
	  this.amount_on_bet = this.amount_on_bet * 2;
	  return 2;
  }
  
  public void score() { //returns the score based on the hand value
	  for(int i = 0; i < this.hand.size(); i++) {
		  score += this.hand.get(i).getValue();
	  }
  }


}
