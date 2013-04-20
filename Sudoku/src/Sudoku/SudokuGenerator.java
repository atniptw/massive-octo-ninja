package Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SudokuGenerator {

	public static int[][] generateBoard(int size) {
		int[][] rowRegions = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				boolean result = addNumber(rowRegions, i, j, size);
				if (!result) {
					i--;
				}
			}
		}

		return rowRegions;
	}

	public static boolean addNumber(int[][] rowRegions, int row, int column,
			int size) {

		Random generator = new Random();
		ArrayList<Integer> val = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			val.add(i + 1);
		}

		int rnd = generator.nextInt(val.size());
		rowRegions[row][column] = val.get(rnd);

		while (!isValid(rowRegions, row + 1, column + 1)) {
			val.remove(rnd);
			if (val.size() == 0)
				return false;
			rnd = generator.nextInt(val.size());
			rowRegions[row][column] = val.get(rnd);
		}
		return true;
	}

	public static boolean isValid(int[][] rowRegions, int row, int column) {
		ArrayList<Integer> testRegion = new ArrayList<Integer>();
		
		//Check Row
		for (int i = 0; i < row; i++) {
			testRegion.clear();
			for (int j = 0; j < column; j++) {
				testRegion.add(rowRegions[i][j]);
			}

			Collections.sort(testRegion);
			for (int k = 0; k < testRegion.size() - 1; k++) {
				if (testRegion.get(k).equals(testRegion.get(k + 1))) {
					return false;
				}
			}

		}

		//Check Column
		for (int j = 0; j < column; j++) {
			testRegion.clear();
			for (int i = 0; i < row; i++) {
				testRegion.add(rowRegions[i][j]);
			}

			Collections.sort(testRegion);
			for (int k = 0; k < testRegion.size() - 1; k++) {
				if (testRegion.get(k).equals(testRegion.get(k + 1))) {
					return false;
				}
			}
		}

		//Check Inner Region
		testRegion.clear();
		if ((row % 3 == 0) & (column % 3 == 0)) {
			testRegion.add(rowRegions[row - 3][column - 3]);
			testRegion.add(rowRegions[row - 3][column - 2]);
			testRegion.add(rowRegions[row - 3][column - 1]);

			testRegion.add(rowRegions[row - 2][column - 3]);
			testRegion.add(rowRegions[row - 2][column - 2]);
			testRegion.add(rowRegions[row - 2][column - 1]);

			testRegion.add(rowRegions[row - 1][column - 3]);
			testRegion.add(rowRegions[row - 1][column - 2]);
			testRegion.add(rowRegions[row - 1][column - 1]);

			Collections.sort(testRegion);
			for (int k = 0; k < testRegion.size() - 1; k++) {
				if (testRegion.get(k).equals(testRegion.get(k + 1))) {
					return false;
				}
			}
		}
		return true;

	}
}
