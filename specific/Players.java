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
	boolean split; //this is only true if split


  public Players(String name){
    this.name = name;
    this.balance = new Balance(0);
  }

	public void getPlayerHand(Deck d){ //inital hand
		Random rand = new Random();
    int random = rand.nextInt(d.sizeDeck());
    Cards a = d.getCard(random);
    input.removeCard(random);
    hand.add(a);
    int rand2 = rand.nextInt(input.sizeDeck());
    Cards b = d.getCard(rand2);
    d.removeCard(rand2);
    hand.add(d);
	}


  //Player should be able to: Hit, Stand, Split, Double up, Bet
  public int Play() {
	  boolean valid_number = false;
	  int input = 0;
	  Scanner scan = new Scanner(System.in);
	  if(split) { //if the split is not possible
		  System.out.println(this.name + " What action do you want to do?: 0 for Hit, 1 for Stand, 2 for DoubleUp, 3 for Split");
		  while(!valid_number) {
			  System.out.println("Enter a valid number");
			  input = scan.nextInt();
			  if(input >= 0 & input < 4) {
				  valid_number = true;
			  }
		  }
		  return input;
	  }
	  else {
		  System.out.println(this.name + " What action do you want to do?: 0 for Hit, 1 for Stand, 2 for DoubleUp");
		  while(!valid_number) {
			  System.out.println("Enter a valid number");
			  input = scan.nextInt();
			  if(input >= 0 & input < 3) {
				  valid_number = true;
			  }
		  }
		  return input;
	 }
}

  public void Bet(int bet_amount) {
	  this.balance.decreaseBalance(bet_amount);
	  this.amount_on_bet += bet_amount;
  }

  public void score() { //returns the score based on the hand value
	  for(int i = 0; i < this.hand.size(); i++) {
		  score += this.hand.get(i).getValue();
	  }
  }

	public void updatebalance(int i){
			if(i > 0) {
				this.balance.addBalance(i);
			}
			else {
				this.balance.decreaseBalance(i);
			}
	}

	public Boolean checkPlayerBalance(){
		if(balance.getMoney() <= 0){
			return false;
		}
		else{
			return true;
		}
	}

	public void printHand(){
		StringBuilder sb = new StringBuilder();
		sb.append("Your cards are: ")
		for(int i = 0; i < this.hand.size(); i++) {
			sb.append(this.hand.get(i).getId() + " ");
		}
		System.out.println(sb);
	}
	public void printScore(){
		System.out.println("Players score is " + score);

	}



	}


}
