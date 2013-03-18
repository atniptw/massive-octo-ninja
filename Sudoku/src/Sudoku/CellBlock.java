package Sudoku;

import java.util.ArrayList;

public class CellBlock {
	private ArrayList<Integer> guess;
	private int answer;
	
	public CellBlock(){
		guess = new ArrayList<Integer>();
	}

	public void guess(int... ints) {
		for (int i : ints) {
			if(guess.contains(i)){
				throw new IllegalArgumentException("Guesses must contain all unique values...");
			}
			guess.add(i);
		}

	}

	public ArrayList<Integer> value() {
		return guess;
	}

	public void setAnswer(int n) {
		answer = n;		
	}

	public int getAnswer() {
		return answer;
	}

}