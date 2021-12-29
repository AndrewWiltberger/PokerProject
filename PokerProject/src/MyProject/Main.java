package MyProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Board b = new Board();
		Deck d = new Deck();
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		Evaluator e = new Evaluator();
		d.ShuffleCards();
		
		
		//p1.setCards(d.DealCard(), d.DealCard());
		//p2.setCards(d.DealCard(), d.DealCard());
		
		//read player 1's cards 
		Scanner reader1 = new Scanner(System.in);
		System.out.println("Set Player 1's card: ");
		int k =1;
		while(k < 3) {
			System.out.print("Enter card " + k + ":");
			String card = reader1.nextLine();
			String cardSuit = card.substring(card.length() -1);
			card = card.substring(0, card.length() - 1);
			Integer cardBinary = d.toFormat(card, cardSuit);
			if(d.pickCard(cardBinary)) {
				p1.setCard(cardBinary, k);
				k++;
			}
			else {
				System.out.println("That card is not in the deck please select another one");
			}
		}
		//reader1.close();

		Scanner reader2 = new Scanner(System.in);
		System.out.println("Set Player 2's card: ");
		k =1;
		while(k < 3) {
			System.out.print("Enter card " + k + ":");
			String card = reader1.nextLine();
			String cardSuit = card.substring(card.length() -1);
			card = card.substring(0, card.length() - 1);
			Integer cardBinary = d.toFormat(card, cardSuit);
			if(d.pickCard(cardBinary)) {
				p2.setCard(cardBinary, k);
				k++;
			}
			else {
				System.out.println("That card is not in the deck please select another one");
			}
		}
		//reader2.close();

		
		
		System.out.print("Player 1 was delt: ");
		p1.printCard(p1.c1);
		System.out.print("And ");
		p1.printCard(p1.c2);
		System.out.println();

		
		System.out.print("Player 2 was delt: ");
		p2.printCard(p2.c1);
		System.out.print("And ");
		p2.printCard(p2.c2);
		System.out.println();

		Scanner reader = new Scanner(System.in);
		System.out.println("Set the community cards: ");
		k =1;
		while(k < 6) {
			System.out.print("Enter card " + k + ":");
			String card = reader.nextLine();
			String cardSuit = card.substring(card.length() -1);
			card = card.substring(0, card.length() - 1);
			Integer cardBinary = d.toFormat(card, cardSuit);
			if(d.pickCard(cardBinary)) {
				b.setCard(k, cardBinary);
				k++;
			}
			else {
				System.out.println("That card is not in the deck please select another one");
			}
		}
		reader.close();
		

		//b.setCards(d.DealCard(), d.DealCard(), d.DealCard(), d.DealCard(), d.DealCard());
		b.printCard(b.c1);
		b.printCard(b.c2);
		b.printCard(b.c3);
		b.printCard(b.c4);
		b.printCard(b.c5);
		
		p1.setFiveCardHands(b.c1, b.c2, b.c3, b.c4, b.c5);
		ArrayList<Integer> bestHandInfo = e.getStrongestHand(p1.fiveCardHands);
		p1.setHandIndex(bestHandInfo.get(0));
		p1.setHandRank(bestHandInfo.get(1));
	
		System.out.println("Player 1 hand rank is:" + p1.getHandRank());
		System.out.println("Player 1 hand is:");
		p1.printOneHand(p1.fiveCardHands.get(p1.getHandIndex()));

	
		p2.setFiveCardHands(b.c1, b.c2, b.c3, b.c4, b.c5);
		bestHandInfo = e.getStrongestHand(p2.fiveCardHands);
		p2.setHandIndex(bestHandInfo.get(0));
		p2.setHandRank(bestHandInfo.get(1));
	
		System.out.println("Player 2 hand rank is:" + p2.getHandRank());
		System.out.println("Player 2 hand is:");
		p2.printOneHand(p2.fiveCardHands.get(p2.getHandIndex()));
		
		if(p2.getHandRank() == p1.getHandRank()) {
			System.out.println("Its a tie!");
		}
		else if(p2.getHandRank() < p1.getHandRank()) {
			System.out.println("Player 2 Wins!");
		}
		else {
			System.out.println("Player 1 Wins!");
		}

		
	}
			
}
