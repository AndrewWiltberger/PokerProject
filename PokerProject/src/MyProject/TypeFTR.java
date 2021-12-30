package MyProject;

import java.util.ArrayList;

public class TypeFTR {
	ArrayList<Integer[]> listFTR = new ArrayList<Integer[]>(1712302);
	
	public void add(Integer[] hand) {
		listFTR.add(hand);
	}
	public Integer[] get(int index) {
		return listFTR.get(index);
	}
	public ArrayList<Integer[]> getList() {
		return listFTR;
	}
	public void set(int index, Integer[] object) {
		listFTR.set(index, object);
	}
	
}
