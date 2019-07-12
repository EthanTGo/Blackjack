package specific;

import java.util.ArrayList;
import java.util.Random;

public class Dealer {
  private int score; // dealer's total card value
  private ArrayList<Cards> dhand; //dealerhands

  public Dealer(Deck toBeTaken){
      score = 0;
      dhand = new ArrayList<Cards>();
    }

  public Cards getCard(Deck input){
	  Random rand = new Random();
    int random = rand.nextInt(input.sizeDeck());
    //check deck find card
    Cards a = input.getCard(random);
    //deck removeCard
    input.removeCard();
    //get card
    return a;
    }




}
