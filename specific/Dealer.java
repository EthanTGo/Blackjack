package specific;

import java.util.ArrayList;
import java.util.Random;

public class Dealer {
  private int score; // dealer's total card value
  private ArrayList<Cards> dhand; //dealerhands
  private Cards show;
  private Cards hidden;

  public Dealer(){
      score = 0;
      dhand = new ArrayList<Cards>();
    }

  public Cards getCard(Deck input){
    Random rand = new Random();
    int random = rand.nextInt(input.sizeDeck());
    //check deck find card
    Cards a = input.getCard(random);
    //deck removeCard
    input.removeCard(random);
    //get card
    return a;
    }

  public int getScore(){
    return score;
  }

  public void printdhand() {
    StringBuilder sb = new StringBuilder();
    sb.append("Dealer's cards has a hidden card and a ");
    sb.append(show.id);
    System.out.println(sb);
  }

  public void getdhand(Deck input){ //use to get dealer's inital hand (add 2 cards)
    Random rand = new Random();
    int random = rand.nextInt(input.sizeDeck());
    Cards a = input.getCard(random);
    input.removeCard(random);
    show = a;
    dhand.add(show); //The first card to be shown
    int rand2 = rand.nextInt(input.sizeDeck());
    Cards b = input.getCard(rand2);
    input.removeCard(rand2);
    hidden = b;
    dhand.add(hidden); //the second card is hidden (as game)
  }

  public void dstand(Players p,  Deck d) {
    while(score < p.score && score != 21) {
      adddhand(d); // add to dealers hand
      printallcards(); //show all current cards (reveal hidden cards)
      updatescore(); //show score
    }
  }

  public void adddhand(Deck input){ //add one card
    Random rand = new Random();
    int random = rand.nextInt(input.sizeDeck());
    Cards a = input.getCard(random);
    input.removeCard(random);
    dhand.add(a);
  }
  public void printallcards() {
    StringBuilder sb = new StringBuilder();
    sb.append("Dealer's cards are ");
    for(int i = 0; i < this.dhand.size(); i++) {
      if(i == this.dhand.size()-1) {
        sb.append(dhand.get(i).getId());
      }else {
       sb.append(dhand.get(i).getId() + " and ");
      }
  }
    System.out.println(sb);

  }

  public void initalscore() {
    score += show.getValue();
    System.out.println("Dealer's score is " + score);
  }

  public void updatescore(){ //we need to consider the Ace case
    score = 0;
    for(int i = 0; i < this.dhand.size(); i++) {
          score += dhand.get(i).getValue();
      }
    System.out.println("Dealer's score is " + score);
  }

  public void dealerReset() {
    score = 0;
    dhand = new ArrayList<Cards>();
  }
}
