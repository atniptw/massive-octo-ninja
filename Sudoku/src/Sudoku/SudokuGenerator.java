package Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SudokuGenerator {

	public static int[][] generateBoard(int size) {
		int[][] rowRegions = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				addNumber(rowRegions, i, j);
			}
		}

		return rowRegions;
	}

	public static void addNumber(int[][] rowRegions, int i, int j) {
		Random generator = new Random();
		ArrayList<Integer> val = new ArrayList<Integer>();
		val.add(1);
		val.add(2);
		val.add(3);
		val.add(4);
		int rnd = generator.nextInt(val.size());
		rowRegions[i][j] = val.get(rnd);
		
		while (!isValid(rowRegions, i, j)) {
			val.remove(rnd);
			
			rnd = generator.nextInt(val.size());
			rowRegions[i][j] = val.get(rnd);
		}

	}

	public static boolean isValid(int[][] rowRegions, int row, int column) {
		ArrayList<Integer> testRegion = new ArrayList<Integer>();
		for (int i = 0; i < row; i++) {
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
		return true;

	}
}
