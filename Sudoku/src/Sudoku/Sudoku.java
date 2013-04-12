package Sudoku;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Sudoku {
	
	private static int[] solvedValidBoard = { 
		5, 2, 9, 1, 7, 6, 3, 4, 8, 
		1, 4, 3, 5, 8, 2, 6, 7, 9,
		8, 7, 6, 9, 3, 4, 5, 2, 1, 
		6, 9, 5, 2, 4, 7, 8, 1, 3,
		7, 1, 2, 3, 5, 8, 4, 9, 6, 
		3, 8, 4, 6, 9, 1, 2, 5, 7, 
		4, 5, 8, 7, 1, 3, 9, 6, 2, 
		2, 3, 7, 4, 6, 9, 1, 8, 5, 
		9, 6, 1, 8, 2, 5, 7, 3, 4 };
	
	private static int[] solvedInvalidBoard = { 
		4, 2, 9, 1, 7, 6, 3, 4, 8, 
		1, 4, 3, 5, 8, 2, 6, 7, 9,
		8, 7, 6, 9, 3, 4, 5, 2, 1, 
		6, 9, 5, 2, 4, 7, 8, 1, 3,
		7, 1, 2, 3, 5, 8, 4, 9, 6, 
		3, 8, 4, 6, 9, 1, 2, 5, 7, 
		4, 5, 8, 7, 1, 3, 9, 6, 2, 
		2, 3, 7, 4, 6, 9, 1, 8, 5, 
		9, 6, 1, 8, 2, 5, 7, 3, 4 };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<CellBlock> blocks = new ArrayList<CellBlock>();
		for (int value : solvedInvalidBoard) {
			CellBlock temp = new CellBlock();
			temp.setAnswer(value);
			blocks.add(temp);
		}
		StandardSudokuBoard invalidBoard = new StandardSudokuBoard(blocks);
		
		invalidBoard.setConflictingCellsToInvalid();
		
		JFrame frame = new JFrame();
		
		frame.setSize(500, 500);
		frame.add(new SudokuComponent(invalidBoard));
		frame.setVisible(true);
	}

}
