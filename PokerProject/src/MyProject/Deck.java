package MyProject;

import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.*;


public class Deck {
	public ArrayList<Integer> cards = new ArrayList<Integer>();
	public int ranks[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	public String ranksString[] = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
	public int ranksM[]= { 
						65536, 131072, 262144,524288,
						1048576,2097152,4194304,8388608,
						16777216,33554432,67108864,134217728,268435456}; 
	public int primeRanks[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
	//[0]=c, [1]=d, [2]=h, [3]=s
	public int suit[] = {8, 4, 2, 1};
	public String suitsString[] = {"c", "d", "h", "s"};
		
	public Deck() {
		for(int i = 0; i < suit.length; i++) {
			for(int j = 0; j < ranks.length; j++) {
				cards.add(makeCard(j, i));
			}
			
		}
	}
	
	public ArrayList<Integer> getCards() { 
		return cards;
	}
	public Integer makeCard(int rankI, int suitI) {
		Integer temp =0;
		//set suit bit
		temp = temp | (suit[suitI]<<12);
		////System.out.println("suit bit:" + Integer.toBinaryString(temp));

		//set rank bit
		temp = temp | ranksM[rankI];
		//System.out.println("rank bit:" + Integer.toBinaryString(temp));

		
		//store the prime rank of card !
		temp = temp | primeRanks[rankI];
		//System.out.println("prime rank:" + Integer.toBinaryString(temp));

		
		//finally store rank value of card
		temp = temp | (ranks[rankI]<< 8);
		//System.out.println("Making card: " + i + "," + j + ":" + Integer.toBinaryString(temp));
		
		return temp;
	}
	
	public Integer toFormat(String rankS, String suitS) {
		return makeCard(Arrays.asList(ranksString).indexOf(rankS),
				Arrays.asList(suitsString).indexOf(suitS));
	}
	
	public Integer DealCard() {
		Integer i = cards.get(0);
		cards.remove(0);
		System.out.println("DEALING CARD");
		return i;
	}
	
	
	//if target is in the deck return true and
	//remove target from deck
	//ow card has already been delt and we 
	//need cannot use it
	public Boolean pickCard(Integer target) {
		if(cards.indexOf(target) != -1) {
			System.out.println("PICKING CARD");

			cards.remove(target);
			return true;
		}
		return false;
	}
	
	public void returnCard(Integer target) {
		if(cards.indexOf(target) == -1) {
			cards.add(target);
		}
	}
	
	public Boolean pick2Cards(Integer c1, Integer c2) {
		if(cards.indexOf(c1) != -1 && cards.indexOf(c2) != -1) {
			cards.remove(c1);
			if(cards.indexOf(c2) != -1) {
				cards.remove(c2);
				return true;
			}
			else {
				cards.add(c1);
				return false;
			}
		}
		return false;
	}
	
	//takes in a string 
	public Boolean dealFlop(String flopString, Evaluator e) {
		if(flopString.length() != 6) return false;
		
		e.flop[0] = flopString.charAt(0) + flopString.charAt(1);
		
		return true;
	}
		
	
	public void ShuffleCards() {
        Collections.shuffle(cards);
	}

}
