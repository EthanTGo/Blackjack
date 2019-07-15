package blackjack;
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
  boolean cashout;
  ArrayList<Players> split_case = new ArrayList<Players>();
  boolean canDouble;
  boolean still_playing;

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
  cashout = true;
  while(player.balance.getMoney() > 0 && cashout) {//until we have player is out of money or wants to cashout
    System.out.println("Your current balance is: $" + player.getBalance());
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
    dealer.initalscore();
    dealer.printdhand();
    //set Player hand
    player.getPlayerHand(deck);
    player.score();
    player.printHand();
    player.printScore();

    if(player.score == 21){
        player.still_playing = false;
        System.out.println("BlackJack!");
        dealer.dstand(player, deck);
        checkWhoWin(player, dealer);

    } else{
        player.still_playing = true;
    }

    //This is where the game actually beginss
    while(player.still_playing) {
      if(player.containSplit()) {
        split_input(player) ;

      }else {
        normal_input(player);
      }
      /*if(this.split_exist) {
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
        }*/

      }
    if(player.balance.getMoney() == 0) {
      changeCashout();
    }else {
    System.out.println("Do you want to cash out? (Yes or No)");
    Scanner x = new Scanner(System.in);
    String ans = x.next();
    if(ans.equals("Yes")) {
      changeCashout();
    }else {
      reset(player);
    }
    }
  }
}
  private void changeCashout() {
    cashout = false;

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
            dealer.dstand(player, deck);
            checkWhoWin(player, dealer);
            break;
          case 2:
            canDouble = false;
            DoubleUp(player);
            if(canDouble) {
              break;
            }else {
            Stand(player);
            dealer.dstand(player, deck);
            checkWhoWin(player, dealer);
            break;
            }
          case 3:
              Players secondH = Split(player);
              normal_input(player);
              normal_input(secondH);
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
             dealer.dstand(player, deck);
             checkWhoWin(player, dealer);
             break;
          case 2:
             canDouble = false;
             DoubleUp(player);
             if(canDouble) {
               break;
             }else {
             Stand(player);
             dealer.dstand(player, deck);
             checkWhoWin(player, dealer);
             break;
             }
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

  private void reset(Players p) { //a function that resets everything
      this.deck = new Deck();
      this.round = 0;
      p.amount_on_bet = 0;
      p.hand = new ArrayList<Cards>();
      p.score = 0;
      dealer.dealerReset();
  }

//todo
  //Logic components/Actions
    public void Hit(Players a) {//0 for hit
        //remove a card from deck
        //add a card to Player
        Cards hit = this.dealer.getCard(this.deck);
        a.hand.add(hit); //add that card
        a.updateScore(hit);;
        a.printHand();
        a.printScore();
        quickcheck(a);
    }

    public void Stand(Players a) { //1 for stand
        //This means you're out of the round but you're score is still there
        a.still_playing = false;
    }

    public void DoubleUp(Players a) { //2 for Doubleup
        //the blackjack function will give me that hand
        int amount = a.amount_on_bet * 2; // (1000) = (500*2)
        int currentAmount = a.balance.getMoney(); //(1000)
        int afterInitBet = currentAmount - a.amount_on_bet; // (500)
        if( amount > currentAmount) {
          System.out.println("Invalid Call: Not enough Balance");
          canDouble = true;
        } else {
        a.amount_on_bet = amount;
        System.out.println(a.name + "'s new bet is " + a.amount_on_bet);
        Hit(a);
        }
    }

    public Players Split(Players a) {
        // Split -> the player has to split their hand into two, can we just make another player???
        Players temp = new Players();
        temp.name = "Second Hand";
        Cards t = a.hand.get(1);
        temp.hand.add(t);
        a.hand.remove(1); //creates two new hand for both players
        Hit(temp);
        temp.printHand();
        Hit(a);
        a.printHand();
        a.score();
        temp.score();
        return temp;
    }

    public void checkWhoWin(Players p, Dealer d) { //Win tester
      if(p.score == 21 && d.getScore() == 21) {
        System.out.println("Draw! No money lost or gained!");
        p.still_playing = false;
      } else if (d.getScore() > 21) {
        int value = p.getBet();
        p.balance.addBalance(value);
        System.out.println(p.name + " has won "+ p.getBet() + "!");
        System.out.println("Your Balance: " + p.balance.getMoney());
        p.still_playing = false;
      } else if (p.score > 21) {
        int value = p.getBet();
        p.balance.decreaseBalance(value);
        System.out.println(p.name + " has lost "+ p.getBet() +"!");
        System.out.println("Your Balance: " +  p.balance.getMoney());
        p.still_playing = false;
      } else if (d.getScore() > p.score) {
        int value = p.getBet();
        p.balance.decreaseBalance(value);
        System.out.println(p.name + " has lost "+ p.getBet() +"!");
        System.out.println("Your Balance: "+  p.balance.getMoney());
        p.still_playing = false;
      } else if (p.score > d.getScore()) {
        int value = p.getBet();
        p.balance.addBalance(value);
        System.out.println(p.name + " has won "+ p.getBet() +"!");
        System.out.println("Your Balance: " +  p.balance.getMoney());
        p.still_playing = false;
      } else if (p.score == d.getScore()){
        System.out.println("Draw! No money lost or gained!");
        p.still_playing = false;
      }
      }
    public void quickcheck(Players a) {
      if(a.score > 21) {
        int value = a.getBet();
        a.balance.decreaseBalance(value);
        System.out.println(a.name + " has lost "+ a.getBet() +"!");
        System.out.println("Your Balance: "+  a.balance.getMoney());
        a.still_playing = false;
      }
    }
}
