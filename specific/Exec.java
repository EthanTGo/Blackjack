package specific;

import java.util.ArrayList;
import java.util.Scanner;

public class Exec {
  ArrayList<Cards> Players =  new ArrayList<Cards> ();

  //main method
	public static void main(String[] args) {
		System.out.println("Welcome! Lets play Black Jack!");
    System.out.println("How many players will be playing?");
    Scanner scan = new Scanner(System.in);
    int players = scan.nextInt();
    for(int a = 1; a <= players; a ++){ //initalize # of players
      System.out.println("Enter player's" + a + "name:");
      Scanner new = new Scanner(System.in);
      String name = new.nextString();
      Player a = new Players(name);
      Players.add(a);
    }

		ArrayList<String> a = new ArrayList<String>();
		a.add("hi");
		a.add("bye");
		System.out.println(a.toString());
		Scanner scan = new Scanner(System.in);
		System.out.println(" What action do you want to do?: 0 for Hit, 1 for Stand, 2 for DoubleUp");

		int input = scan.nextInt();
		  switch(input) {
		  	case 0:
			  System.out.println("0");
			  break;
		  	case 1:
		  	  System.out.println("1");
		  	  break;
		  	case 2:
		  	  System.out.println("2");
			  break;
			default:
				System.out.println("Enter a correct value");
		  }

	}

}
