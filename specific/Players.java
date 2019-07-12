package specific;
import java.util.ArrayList; 
import java.util.Arrays; 


public class Players {
  //Should have: name, Balance
	String name;
	Balance balance;
	ArrayList<Cards> hand;

  public Players(String name){
    this.name = name;
    this.balance = new Balance(0);
    this.hand = new ArrayList<Cards>();
  }

  //Player should be able to: Hit, Stand, Split, Double up
  
  


}
