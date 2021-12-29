package MyProject;

public class Board  {
	public Integer c1;
	public Integer c2;
	public Integer c3;
	public Integer c4;
	public Integer c5;
	public String values[] = {
			"2", "3", "4", "5", "6", "7", "8", "9",
			"10", "Jack", "Queen", "King", "Ace"
			};
	
	public void setCards(Integer c1, Integer c2, Integer c3, Integer c4, Integer c5) {
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;

	}
	
	public void setCard(int cardNum, Integer card) {
		if(cardNum == 1) {
			c1 = card;
		}
		else if(cardNum == 2) {
			c2 = card;
		}
		else if(cardNum == 3) {
			c3 = card;
		}
		else if(cardNum == 4) {
			c4 = card;
		}
		else if(cardNum == 5) {
			c5 = card;
		}
		else {
			System.out.println("Error out of bounds...");
		}
	}


	public void printCard(Integer c) {
		Integer nibble = (c >> 8);
		//System.out.println(Integer.toBinaryString(nibble));
		nibble = nibble & 0xF;
		//System.out.println(Integer.toBinaryString(nibble));
		String output = values[nibble] + " of ";
		//System.out.print(values[nibble] + " of ");

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
	
	private boolean isKthBitSet(Integer n, int k) {
		if ((n & (1 << (k - 1))) > 0)
            return true;
		else
			return false;
	}



}
