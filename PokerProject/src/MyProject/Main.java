package MyProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Board b = new Board();
		Deck d = new Deck();
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		Evaluator e = new Evaluator();
		
	
		Integer[] cards5 = new Integer[5];
		p1.select2Cards(d);
		System.out.println("Player 1 cards selected!");

		p2.select2Cards(d);
		System.out.println("Player 2 cards selected!");
		


		TypeFTR typeFTR = new TypeFTR();
		System.out.println("Calculating equiting over all flops turns and rivers...");

		e.generateAllCC(d.getCards(), cards5, 0, d.getCards().size()-1, 0, 5, typeFTR, p1.c1, p1.c2, p2.c1, p2.c2);
		
		/*ArrayList<Integer[]> allFTR = typeFTR.getList();
		System.out.println("Lenght allFTR:" + allFTR.size());
		
		
		int k = 0;
		
		
		for(Integer[] list : typeFTR.getList()) {
			System.out.println("Printing hand allFTR:" + k);
			int p = 0;
			for(Integer c : list) {
				e.printCard(c);
			}
			k++;
			System.out.println();
			if(k > 15) break;

		}
		System.out.println("Last ###########################");

		e.printCard(typeFTR.getList().get(0)[0]);
		e.printCard(typeFTR.getList().get(1000)[0]);
		e.printCard(typeFTR.getList().get(23455)[0]);
		e.printCard(typeFTR.getList().get(234678)[0]);
		e.printCard(typeFTR.getList().get(987123)[0]);


		
		e.caclEquity(allFTR, p1.c1, p1.c2, p2.c1, p2.c2);	*/
		
		System.out.println("Player 1 wins:" + e.player1Wins);
		System.out.println("Player 2 wins:" + e.player2Wins);
		double eq1 = e.player1Wins / (e.player1Wins + e.player2Wins);
		double eq2 = e.player2Wins / (e.player1Wins + e.player2Wins);
		
		System.out.println("Player 1 equity:" + eq1);
		System.out.println("Player 2 equity:" + eq2);



	}
			
}
