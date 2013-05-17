package Sudoku;

import java.io.Serializable;
import java.util.ArrayList;

public class CellBlock implements Comparable<CellBlock>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5040286658398532423L;
	private ArrayList<Integer> guess;
	private int answer;
	private boolean given;
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
	
	public void setGiven(boolean given) {
		this.given = given;
	}
	
	public boolean getGiven() {
		return this.given;
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
