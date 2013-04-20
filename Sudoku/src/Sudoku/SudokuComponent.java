package Sudoku;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class SudokuComponent extends JComponent {

	ISudokuBoard board;

	public SudokuComponent(ISudokuBoard board) {
		super();
		this.board = board;
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setLayout(new GridLayout(board.size(), board.size()));

		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				this.add(new CellComponent(board.getCell(i, j)));
			}
		}
	}

}
