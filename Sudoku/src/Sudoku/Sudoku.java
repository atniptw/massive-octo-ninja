package Sudoku;

import javax.swing.JFrame;

public class Sudoku {

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

		SudokuFrame frame = new SudokuFrame(args[0], args[1]);


	}
}
