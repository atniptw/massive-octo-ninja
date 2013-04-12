package Sudoku;

import java.util.Random;

public class BoardAdjuster {

	public enum Difficulty {
		VERY_EASY, EASY, MEDIUM, DIFFICULT, EVIL;
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
			Difficulty diff) {

		int[][] newBoard = originalBoard;

		int variance;
		int givens = 0;
		int toRemove;
		int maxBoardCells = originalBoard.length * originalBoard.length;

		Random gen = new Random();

		switch (diff) {

		case VERY_EASY:

			variance = gen.nextInt((VERY_EASY_MAX_GIVEN - VERY_EASY_MIN_GIVEN));
			givens = VERY_EASY_MIN_GIVEN + variance;
			break;

		case EASY:

			variance = gen.nextInt((EASY_MAX_GIVEN - EASY_MIN_GIVEN));
			givens = EASY_MIN_GIVEN + variance;
			break;

		case MEDIUM:

			variance = gen.nextInt((MEDIUM_MAX_GIVEN - MEDIUM_MIN_GIVEN));
			givens = MEDIUM_MIN_GIVEN + variance;
			break;

		case DIFFICULT:

			variance = gen.nextInt((DIFFICULT_MAX_GIVEN - DIFFICULT_MIN_GIVEN));
			givens = DIFFICULT_MIN_GIVEN + variance;
			break;

		case EVIL:

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
	
	public static int getTotalUnfilledCells(int[][] board){
		int count = 0;
		
		for (int i = 0; i<board.length;i++){
			for (int j = 0; j<board.length;j++){
				if (board[i][j] == 0){
					count++;
				}
			}
		}
		
		return count;
	}

}
