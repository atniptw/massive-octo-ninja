package Sudoku;

import java.util.ArrayList;

public class CellBlock implements Comparable<CellBlock> {
	private ArrayList<Integer> guess;
	private int answer;
	private boolean isValid = true;

	public CellBlock() {
		guess = new ArrayList<Integer>();
	}

	public CellBlock(int i) {
		answer = i;
	}

	public void guess(int... ints) {
		for (int i : ints) {
			if (guess.contains(i)) {
				throw new IllegalArgumentException(
						"Guesses must contain all unique values...");
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
	
	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	public boolean getIsValid() {
		return this.isValid;
	}

	@Override
	public int compareTo(CellBlock arg0) {
		if (this.getAnswer() < arg0.getAnswer())
			return -1;
		if (this.getAnswer() == arg0.getAnswer())
			return 0;
		else
			return 1;

	}

	@Override
	public boolean equals(Object arg) {
		if (this.getAnswer() == ((CellBlock) arg).getAnswer())
			return true;
		else

			return false;

	}

}
