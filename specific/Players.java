package specific;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class Players {
//Should have: name, Balance
  String name;
  Balance balance;
  ArrayList<Cards> hand = new ArrayList<Cards>();

  //This is the in-game attributes of the player
  boolean still_playing; //whether the player did not fold
  int amount_on_bet;
  int score; //this is the score based on the hand that the players has
  boolean split; //this is only true if split potential exist


public Players(String name){
  this.name = name;
  this.balance = new Balance(0);
}

public boolean checkDuplicate(Cards check, int position) { //checks for duplicate
    for(int i = position; i < this.hand.size(); i++) {
        if(check.equals(this.hand.get(i))) {
            return true;
        }
    }
    return false;
}

public void checkSplit() {
    for(int i = 0; i < this.hand.size(); i++) {
        String id = hand.get(i).id;
        if(checkDuplicate(this.hand.get(i),i)) {
            this.split = true;
            break;
        }
    }
}

public Players() {

}

  public void getPlayerHand(Deck d){ //inital hand
      Random rand = new Random();
      int random = rand.nextInt(d.sizeDeck());
      Cards a = d.getCard(random);
      d.removeCard(random);
      this.hand.add(a);
      int rand2 = rand.nextInt(d.sizeDeck());
      Cards b = d.getCard(rand2);
      d.removeCard(rand2);
      hand.add(b);
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
    //this.balance.decreaseBalance(bet_amount);
    this.amount_on_bet += bet_amount;
}

  public int getBet(){
      return amount_on_bet;
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
      sb.append("Your cards are: ");
      for(int i = 0; i < this.hand.size(); i++) {
        if(i == (this.hand.size() -1 )) {
          sb.append(this.hand.get(i).getId());
        } else {
          sb.append(this.hand.get(i).getId() + " and ");
        }
      }
      System.out.println(sb);
  }
  public void printScore(){
      System.out.println("Player's score is " + score);
      }

  public void initalizeBalance(int i){
      balance = new Balance(i);
  }
  public int getBalance(){
      return balance.getMoney();
  }

  public void split() {
      //initialize in the case of a split
      Players second_hand = new Players();
  }

  public void updateScore(Cards c) {
    int value = c.getValue();
    score += value;
  }

}
