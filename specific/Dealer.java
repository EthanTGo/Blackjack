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

  public void getdhand(Deck input){ //use to get dealer's inital hand (add 2 cards)
    Random rand = new Random();
    int random = rand.nextInt(input.sizeDeck());
    Cards a = input.getCard(random);
    input.removeCard(random);
    show = a;
    dhand.add(show);
    int rand2 = rand.nextInt(input.sizeDeck());
    Cards b = input.getCard(rand2);
    input.removeCard(rand2);
    hidden = b;
    dhand.add(hidden);
  }

  public void adddhand(Deck input){ //add one card
    Random rand = new Random();
    int random = rand.nextInt(input.sizeDeck());
    Cards a = input.getCard(random);
    input.removeCard(random);
    dhand.add(a);
  }

  public void updatescore(){ //we need to consider the Ace case
    for(int i = 0; i < this.dhand.size(); i++) {
		  score += dhand.get(i).getValue();
	  }
  }
}
