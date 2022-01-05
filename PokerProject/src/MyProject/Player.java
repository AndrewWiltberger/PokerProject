package MyProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	public int position;
	public Integer c1;
	public Integer c2;
	
	//holds all 5 card hands given 7 cards ie c(7,5)
	ArrayList<ArrayList<Integer>> fiveCardHands = new ArrayList<ArrayList<Integer>>();
	//holds the absolute rank of your hand 
	public int handRank;
	//the index of the strongest hand in fiveCardHands
	public Integer handIndex;

	public String values[] = {
			"2", "3", "4", "5", "6", "7", "8", "9",
			"10", "Jack", "Queen", "King", "Ace"
			};
	
	//used to create the possible 5 card hands 
	public int perm7[][] =
		{
			    { 0, 1, 2, 3, 4 },
			    { 0, 1, 2, 3, 5 },
			    { 0, 1, 2, 3, 6 },
			    { 0, 1, 2, 4, 5 },
			    { 0, 1, 2, 4, 6 },
			    { 0, 1, 2, 5, 6 },
			    { 0, 1, 3, 4, 5 },
			    { 0, 1, 3, 4, 6 },
			    { 0, 1, 3, 5, 6 },
			    { 0, 1, 4, 5, 6 },
			    { 0, 2, 3, 4, 5 },
			    { 0, 2, 3, 4, 6 },
			    { 0, 2, 3, 5, 6 },
			    { 0, 2, 4, 5, 6 },
			    { 0, 3, 4, 5, 6 },
			    { 1, 2, 3, 4, 5 },
			    { 1, 2, 3, 4, 6 },
			    { 1, 2, 3, 5, 6 },
			    { 1, 2, 4, 5, 6 },
			    { 1, 3, 4, 5, 6 },
			    { 2, 3, 4, 5, 6 }
			};
	
	//returns the players position
	public Player(int p) { 
		position = p;
	}
	
	public void setCards(Integer c1, Integer c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public void setCard(Integer c, int cardNum) {
		if(cardNum == 1) {
			c1 = c;
		}
		else if(cardNum == 2) {
			c2 = c;
		}
		else {
			System.out.println("Error...");
		}
	}
	
	public void setHandIndex(Integer i) {
		handIndex = i;
	}
	
	public Integer getHandIndex() {
		return handIndex;
	}
	
	//takes the Binary format of a card and prints the suit and rank
	public void printCard(Integer c) {
		Integer nibble = (c >> 8);
		//System.out.println(Integer.toBinaryString(nibble));
		nibble = nibble & 0xF;
		//System.out.println(Integer.toBinaryString(nibble));
		String output = values[nibble] + " of ";
		

		if(isKthBitSet(c, 16))
			output += "Clubs";
		else if(isKthBitSet(c, 15))
			output += "Diamonds";
		else if(isKthBitSet(c, 14))
			output += "Hearts";
		else if(isKthBitSet(c, 13))
			output += "Spades";
		System.out.println(output);

	}
	
	//
	//rotine to check if kth bith is set
	//
	private boolean isKthBitSet(Integer n, int k) {
		if ((n & (1 << (k - 1))) > 0)
            return true;
		else
			return false;
	}
	
	//
	//function to set all possible 5 card hands given community cards and personal cards
	//
	public void setFiveCardHands(Integer c3, Integer c4, Integer c5, Integer c6, Integer c7) {
		ArrayList<Integer> sevenCards = new ArrayList<Integer>();
		sevenCards.add(c1);
		sevenCards.add(c2);
		sevenCards.add(c3);
		sevenCards.add(c4);
		sevenCards.add(c5);
		sevenCards.add(c6);
		sevenCards.add(c7);
		
		for(int i = 0; i < 21; i++) {
			ArrayList<Integer> hand = new ArrayList<Integer>();

			for(int j =0; j < 5; j++) {				
				hand.add(sevenCards.get(perm7[i][j]));
			}
			
			fiveCardHands.add(hand);
		}		
	}
	
	//
	//prints all 5 card hands 
	//
	public void printFiveCardHands() {
		System.out.println("Printing all 5 card hands:");
		int j = 1; 
		for(ArrayList<Integer> l1 : fiveCardHands) {
			System.out.println("Printing hand:" + j++);
			for(Integer i : l1) {
				printCard(i);
				System.out.println();
			}
		}
	}
	
	public void printOneHand(ArrayList<Integer> hand) {
		for(Integer i : hand)
		{
			printCard(i);
		}
	}
	public int getHandRank() {
		return handRank;
	}
	
	public void setHandRank(int r) {
		handRank = r;
	}
	
	//function to capture card ranks from user input
	//pretty sure this has a memory leak but idgaf
	public void select2Cards(Deck d) {
		Scanner reader1 = new Scanner(System.in);
		
		System.out.println("Set Player " + position +  "'s cards: ");
		int k =1;
		while(k < 3) {
			System.out.print("Enter card " + k + ":");
			String card = reader1.nextLine();
			
			String cardSuit = card.substring(card.length() -1);
			card = card.substring(0, card.length() - 1);
			Integer cardBinary = d.toFormat(card, cardSuit);
			if(d.pickCard(cardBinary)) {
				setCard(cardBinary, k);
				k++;
			}
			else {
				System.out.println("That card is not in the deck please select another one");
			}
		}
		//reader1.close();
	}
	
	public Boolean selectCardsFromString(Deck d, String inputHand) {		
		char cardRank1 = inputHand.charAt(0);
		char cardSuit1 = inputHand.charAt(1);
		
		char cardRank2 = inputHand.charAt(2);
		char cardSuit2 = inputHand.charAt(3);
		Integer cardBinary1;
		Integer cardBinary2;
		
		try {
			cardBinary1 = d.toFormat(String.valueOf(cardRank1), String.valueOf(cardSuit1));
			cardBinary2 = d.toFormat(String.valueOf(cardRank2), String.valueOf(cardSuit2));

		} catch(Exception e) {
			return false;
			
		}
		
		if(!d.pick2Cards(cardBinary1, cardBinary2)) {
			return false;
		}
		setCard(cardBinary1, 1);
		setCard(cardBinary2, 2);
		//String cardSuit = inputHand[1];
		return true;

	}
	
	
	//function takes in a string and 
	//returns the Integer format of card if
	//valid (in deck/real card) 
	//return -1 if the card is invalid
	public static Integer select1CardFromString(Deck d, String inputCard) {
		char cardRank1 = inputCard.charAt(0);
		char cardSuit1 = inputCard.charAt(1);
		Integer cardBinary1;

		
		try {
			cardBinary1 = d.toFormat(String.valueOf(cardRank1), String.valueOf(cardSuit1));
		} catch(Exception e) {
			return -1;
		}
		if(d.pickCard(cardBinary1)) {
			return cardBinary1;
		}
		return -1;
	}

		
	
	
}
