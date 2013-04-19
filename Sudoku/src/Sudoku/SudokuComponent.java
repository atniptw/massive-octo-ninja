package Sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class SudokuComponent extends JComponent {

	private ISudokuBoard currentBoard;
	private CellComponent selectedComponent;

	private int lineThickness = 1;

	public SudokuComponent(ISudokuBoard board) {
		super();
		this.currentBoard = board;
		this.setLayout(new GridLayout(board.size(), board.size()));
		CellComponent cell = null;
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				cell = new CellComponent(board.getCell(i, j), false);
				cell.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						selectedComponent.setSelected(false);
						selectedComponent = (CellComponent) e.getSource();
						selectedComponent.setSelected(true);
					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseReleased(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {

					}

					@Override
					public void mouseExited(MouseEvent e) {

					}

				});
				int bottomBorderThickness = lineThickness;
				int rightBorderThickness = lineThickness;

				if (i % ((int) Math.sqrt(board.size())) == ((int) Math
						.sqrt(board.size()) - 1)) {
					bottomBorderThickness = 6;
				}
				if (j % ((int) Math.sqrt(board.size())) == ((int) Math
						.sqrt(board.size()) - 1)) {
					rightBorderThickness = 6;
				}
				cell.setBorder(BorderFactory.createMatteBorder(lineThickness,
						lineThickness, bottomBorderThickness,
						rightBorderThickness, Color.BLACK));
				this.add(cell);
			}
		}
		if (cell != null) {
			this.selectedComponent = cell;
			this.selectedComponent.setSelected(true);
		}
	}

	public void setSelectedCell(int value) {
		this.selectedComponent.setValue(value);
	}

}
