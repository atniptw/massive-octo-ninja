package Sudoku;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class BoardAdjuster {

	public enum Difficulty {
		SIMPLE, EASY, MEDIUM, DIFFICULT, EVIL;
	}

	public static final int VERY_EASY_MAX_GIVEN = 70;
	public static final int VERY_EASY_MIN_GIVEN = 50;
	public static final int EASY_MAX_GIVEN = 49;
	public static final int EASY_MIN_GIVEN = 36;
	public static final int MEDIUM_MAX_GIVEN = 35;
	public static final int MEDIUM_MIN_GIVEN = 32;
	public static final int DIFFICULT_MAX_GIVEN = 31;
	public static final int DIFFICULT_MIN_GIVEN = 28;
	public static final int EVIL_MAX_GIVEN = 27;
	public static final int EVIL_MIN_GIVEN = 22;

	public static int[][] adjustForDifficulty(int[][] originalBoard,
			String diff, ResourceBundle bundle) {

		int[][] newBoard = new int[originalBoard.length][originalBoard.length];

		for (int i = 0; i < originalBoard.length; i++) {
			for (int j = 0; j < originalBoard.length; j++) {
				newBoard[i][j] = originalBoard[i][j];
			}
		}

		int variance;
		int givens = 0;
		int toRemove;
		int maxBoardCells = originalBoard.length * originalBoard.length;

		String simple = bundle.getString("simple");
		String easy = bundle.getString("easy");
		String medium = bundle.getString("medium");
		String difficult = bundle.getString("difficult");
		String evil = bundle.getString("evil");

		Random gen = new Random();

		if (diff.equals(simple)) {
			variance = gen.nextInt((VERY_EASY_MAX_GIVEN - VERY_EASY_MIN_GIVEN));
			givens = VERY_EASY_MIN_GIVEN + variance;
		} else if (diff.equals(easy)) {
			variance = gen.nextInt((EASY_MAX_GIVEN - EASY_MIN_GIVEN));
			givens = EASY_MIN_GIVEN + variance;
		} else if (diff.equals(medium)) {
			variance = gen.nextInt((MEDIUM_MAX_GIVEN - MEDIUM_MIN_GIVEN));
			givens = MEDIUM_MIN_GIVEN + variance;
		} else if (diff.equals(difficult)) {
			variance = gen.nextInt((DIFFICULT_MAX_GIVEN - DIFFICULT_MIN_GIVEN));
			givens = DIFFICULT_MIN_GIVEN + variance;
		} else if (diff.equals(evil)) {
			variance = gen.nextInt((EVIL_MAX_GIVEN - EVIL_MIN_GIVEN));
			givens = EVIL_MIN_GIVEN + variance;
		}

		toRemove = maxBoardCells - givens;

		while (toRemove > 0) {
			int i = gen.nextInt(originalBoard.length - 1);
			int j = gen.nextInt(originalBoard.length - 1);

			if (newBoard[i][j] != 0) {
				newBoard[i][j] = 0;
			} else {
				continue;
			}
			toRemove--;
		}

		return newBoard;

	}

	public static int getTotalUnfilledCells(int[][] board) {
		int count = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 0) {
					count++;
				}
			}
		}

		return count;
	}

	public static int[][] adjustForDifficulty(int[][] clone,
			Difficulty difficult) {
		// TODO Auto-generated method stub
		Locale loc = new Locale("en", "MX");
		ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", loc);
		String diff = "";
		switch(difficult){
		case SIMPLE:
			diff = "simple";
			break;
		case EASY:
			diff = "easy";
			break;
		case MEDIUM:
			diff = "medium";
			break;
		case DIFFICULT:
			diff = "difficult";
			break;
		case EVIL:
			diff = "evil";
			break;
		}
			
		return adjustForDifficulty(clone, diff, bundle);
	}

}
