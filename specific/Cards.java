package specific;

public class Cards {
  private int total_cards = 52;
  private int available_cards[] =  new int[52];


  public Cards ( ){ //initializer of cards
    // (A) 1 , 2, 3, 4, 5, 6, 7, 8, 9, 10, (J) 11, (Q) 12, (K) 13,
    for(int i = 1; i <= 13; i ++){
      for(int j = 0; j < 4; j++){
          available_cards[i]= i;
      }
    }
  }

  



}
