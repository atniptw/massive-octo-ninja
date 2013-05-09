package Sudoku;

import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class BoardAdjuster {

	public enum Difficulty {
		SIMPLE, EASY, MEDIUM, DIFFICULT, EVIL;
	}

	public static final double SIMPLE_MAX_FACTOR = 7.5;
	public static final double SIMPLE_MIN_FACTOR = 6;
	public static final double EASY_MAX_FACTOR = 5.5;
	public static final double EASY_MIN_FACTOR = 4.5;
	public static final double MEDIUM_MAX_FACTOR = 4;
	public static final double MEDIUM_MIN_FACTOR = 3.5;
	public static final double DIFFICULT_MAX_FACTOR = 3.5;
	public static final double DIFFICULT_MIN_FACTOR = 3;
	public static final double EVIL_MAX_FACTOR = 3;
	public static final double EVIL_MIN_FACTOR = 2.5;

	public static final int SIMPLE_STANDARD_FILL_FLOOR = 5;
	public static final int EASY_STANDARD_FILL_FLOOR = 4;
	public static final int MEDIUM_STANDARD_FILL_FLOOR = 3;
	public static final int DIFFICULT_STANDARD_FILL_FLOOR = 2;
	public static final int EVIL_STANDARD_FILL_FLOOR = 0;

	public static void adjustForDifficulty(ISudokuBoard currentBoard,
			String diff, ResourceBundle bundle) {

		int size = currentBoard.size();

		int variance;
		int givens = 0;
		int toRemove;
		int maxBoardCells = size * size;
		double range;
		int difficultyRegionFillFloor = 0;

		String simple = bundle.getString("simple");
		String easy = bundle.getString("easy");
		String medium = bundle.getString("medium");
		String difficult = bundle.getString("difficult");
		String evil = bundle.getString("evil");

		Random gen = new Random();

		if (diff.equals(simple)) {
			difficultyRegionFillFloor = SIMPLE_STANDARD_FILL_FLOOR;
			range = (SIMPLE_MAX_FACTOR * size) - (SIMPLE_MIN_FACTOR * size);

			variance = gen.nextInt((int) Math.ceil(range));
			givens = (int) Math.ceil((SIMPLE_MIN_FACTOR * size)) + variance;
		} else if (diff.equals(easy)) {
			difficultyRegionFillFloor = EASY_STANDARD_FILL_FLOOR;
			range = (EASY_MAX_FACTOR * size) - (EASY_MIN_FACTOR * size);

			variance = gen.nextInt((int) Math.ceil(range));
			givens = (int) Math.ceil((EASY_MIN_FACTOR * size)) + variance;
			
		} else if (diff.equals(medium)) {
			difficultyRegionFillFloor = MEDIUM_STANDARD_FILL_FLOOR;
			range = (MEDIUM_MAX_FACTOR * size) - (MEDIUM_MIN_FACTOR * size);

			variance = gen.nextInt((int) Math.ceil(range));
			givens = (int) Math.ceil((MEDIUM_MIN_FACTOR * size)) + variance;
			
		} else if (diff.equals(difficult)) {
			difficultyRegionFillFloor = DIFFICULT_STANDARD_FILL_FLOOR;
			range = (DIFFICULT_MAX_FACTOR * size)
					- (DIFFICULT_MIN_FACTOR * size);

			variance = gen.nextInt((int) Math.ceil(range));
			givens = (int) Math.ceil((DIFFICULT_MIN_FACTOR * size)) + variance;

		} else if (diff.equals(evil)) {
			difficultyRegionFillFloor = EVIL_STANDARD_FILL_FLOOR;
			range = (EVIL_MAX_FACTOR * size) - (EVIL_MIN_FACTOR * size);

			variance = gen.nextInt((int) Math.ceil(range));
			givens = (int) Math.ceil((EVIL_MIN_FACTOR * size)) + variance;

		}

		toRemove = maxBoardCells - givens;

		HashMap<Integer, Integer> fillsRows = new HashMap<Integer, Integer>();
		fillsRows = initializeMap(fillsRows, size);

		HashMap<Integer, Integer> fillsCols = new HashMap<Integer, Integer>();
		fillsCols = initializeMap(fillsCols, size);

		while (toRemove > 0) {
			int i = gen.nextInt(size);
			int j = gen.nextInt(size);

			if (currentBoard.getAnswer(i, j) != 0
					&& fillsRows.get(i) > difficultyRegionFillFloor
					&& fillsCols.get(j) > difficultyRegionFillFloor) {
				currentBoard.setAnswer(i, j, 0);
				int tempRow = fillsRows.get(i);
				int tempCol = fillsCols.get(j);
				fillsRows.put(i, tempRow - 1);
				fillsCols.put(j, tempCol - 1);
			} else {
				continue;
			}
			toRemove--;

		}

	}

	private static HashMap<Integer, Integer> initializeMap(
			HashMap<Integer, Integer> fills, int size) {
		HashMap<Integer, Integer> init = fills;
		init.put(0, size);
		init.put(1, size);
		init.put(2, size);
		init.put(3, size);
		init.put(4, size);
		init.put(5, size);
		init.put(6, size);
		init.put(7, size);
		init.put(8, size);

		return init;
	}

	public static int getTotalUnfilledCells(ISudokuBoard board) {
		int count = 0;

		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				if (board.getAnswer(i, j) == 0) {
					count++;
				}
			}
		}
		
		return count;
	}

	public static int getTotalUnfilledCellsInRow(ISudokuBoard board, int i) {
		int count = 0;

		for (int j = 0; j < board.size(); j++) {
			if (board.getAnswer(i, j) == 0) {
				count++;
			}
		}
		return count;
	}

	public static int getTotalUnfilledCellsInCol(ISudokuBoard board, int j) {
		int count = 0;

		for (int i = 0; i < board.size(); i++) {
			if (board.getAnswer(i, j) == 0) {
				count++;
			}
		}
		return count;
	}

	public static int getTotalGivensInCol(ISudokuBoard board, int i) {
		return board.size() - getTotalUnfilledCellsInCol(board, i);
	}

	public static int getTotalGivensInRow(ISudokuBoard board, int i) {
		return board.size() - getTotalUnfilledCellsInRow(board, i);
	}

}
