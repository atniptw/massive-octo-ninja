package Sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class SudokuComponent extends JComponent {

	private ISudokuBoard board;
	private CellComponent selectedComponent;
	
	private int lineThickness = 1;

	public SudokuComponent(ISudokuBoard board) {
		super();
		this.board = board;
		this.setLayout(new GridLayout(board.size(), board.size()));

		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				CellComponent cell = new CellComponent(board.getCell(i, j));
				
				int bottomBorderThickness = lineThickness;
				int rightBorderThickness = lineThickness;
				
				if (i % ((int) Math.sqrt(board.size())) == ((int) Math.sqrt(board.size())-1)) {
					bottomBorderThickness=6;
				}
				if (j % ((int) Math.sqrt(board.size())) == ((int) Math.sqrt(board.size())-1)) {
					rightBorderThickness=6;
				}
				cell.setBorder(BorderFactory.createMatteBorder(lineThickness, lineThickness, bottomBorderThickness, rightBorderThickness, Color.BLACK));
				this.add(cell);
				this.selectedComponent = cell;
			}
		}
	}
	
	public void setSelectedCell(int value){
		this.selectedComponent.setValue(value);
	}
	

}
