package Sudoku;

<<<<<<< HEAD
import javax.swing.JFrame;

public class Sudoku {

//	private static int[] solvedValidBoard = { 5, 2, 9, 1, 7, 6, 3, 4, 8, 1, 4,
//			3, 5, 8, 2, 6, 7, 9, 8, 7, 6, 9, 3, 4, 5, 2, 1, 6, 9, 5, 2, 4, 7,
//			8, 1, 3, 7, 1, 2, 3, 5, 8, 4, 9, 6, 3, 8, 4, 6, 9, 1, 2, 5, 7, 4,
//			5, 8, 7, 1, 3, 9, 6, 2, 2, 3, 7, 4, 6, 9, 1, 8, 5, 9, 6, 1, 8, 2,
//			5, 7, 3, 4 };
//
//	private static int[] solvedInvalidBoard = { 4, 2, 9, 1, 7, 6, 3, 4, 8, 1,
//			4, 3, 5, 8, 2, 6, 7, 9, 8, 7, 6, 9, 3, 4, 5, 2, 1, 6, 9, 5, 2, 4,
//			7, 8, 1, 3, 7, 1, 2, 3, 5, 8, 4, 9, 6, 3, 8, 4, 6, 9, 1, 2, 5, 7,
//			4, 5, 8, 7, 1, 3, 9, 6, 2, 2, 3, 7, 4, 6, 9, 1, 8, 5, 9, 6, 1, 8,
//			2, 5, 7, 3, 4 };
=======
public class Sudoku {
>>>>>>> workingOnGUI

	/**
	 * @param args
	 */
	/*
	 * public static void main(String[] args) { ArrayList<CellBlock> blocks =
	 * new ArrayList<CellBlock>(); for (int value : solvedInvalidBoard) {
	 * CellBlock temp = new CellBlock(); temp.setAnswer(value);
	 * blocks.add(temp); } StandardSudokuBoard invalidBoard = new
	 * StandardSudokuBoard(blocks);
	 * 
	 * invalidBoard.setConflictingCellsToInvalid();
	 * 
	 * JFrame frame = new JFrame();
	 * 
	 * frame.setSize(500, 500); frame.add(new SudokuComponent(invalidBoard));
	 * frame.setVisible(true); }
	 */

	public static void main(String[] args) {
<<<<<<< HEAD
		/*ArrayList<CellBlock> blocks = new ArrayList<CellBlock>();
		int[][] board = SudokuGenerator.generateBoard(9);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				CellBlock temp = new CellBlock();
				temp.setAnswer(board[i][j]);
				blocks.add(temp);
			}
		}*/
		
		StandardSudokuBoard gameBoard = new StandardSudokuBoard(9);
		gameBoard.populateBoard();

		gameBoard.setConflictingCellsToInvalid();

		JFrame frame = new JFrame();

		frame.setSize(500, 500);
		frame.add(new SudokuComponent(gameBoard));
		frame.setVisible(true);
=======

		SudokuFrame frame = new SudokuFrame(args[0], args[1]);

>>>>>>> workingOnGUI
	}
}
