package specific;

import java.util.ArrayList;
import java.util.Scanner;

public class Exec {
  public static boolean keepPlaying = true;
  //main method
	public static void main(String[] args) {

      while(keepPlaying){
        System.out.println("Welcome! Lets play Black Jack!");
        //initalize deck and dealer and game
        BlackJack game = new BlackJack();
        //initalize player and balance
        Players CurrentPlayer = game.createPlayer();
        System.out.println("Let's start Playing");
        game.playGame(CurrentPlayer);

        //after initial game ends, ask if they want to continue?
        System.out.println("Do you want to play again: Yes or No");
        Scanner scan = new Scanner(System.in);
        String ans = scan.nextLine();
        if(ans == "No"){
          keepPlaying = false;
        } else {
          continue;
        }

      }

    }
    }

