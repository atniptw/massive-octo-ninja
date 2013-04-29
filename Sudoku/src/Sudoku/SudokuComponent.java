package Sudoku;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class SudokuComponent extends JComponent {

	private ISudokuBoard currentBoard;
	private CellComponent selectedComponent;
	private CellComponent[][] componentList;

	private int lineThickness = 1;

	public SudokuComponent(ISudokuBoard board) {
		super();
		this.currentBoard = board;
		this.setLayout(new GridLayout(board.size(), board.size()));
		CellComponent cell = null;
		componentList = new CellComponent[board.size()][board.size()];
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
//				if (board.getCell(i, j).getAnswer() == 0) { 
				cell = new CellComponent(board.getCell(i, j), false);
				componentList[i][j] = cell;
//				} else {
//					cell = new CellComponent(board.getCell(i, j), true);
//				}
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

	public void giveAnswerToSelectedCell(int[][] completedBoard) {
		for (int i = 0; i < this.currentBoard.size(); i++) {
			for (int j = 0; j < this.currentBoard.size(); j++) {
				if (this.currentBoard.getCell(i, j) == this.selectedComponent.cell){
					this.selectedComponent.setValue(completedBoard[i][j]);
					this.selectedComponent.setGiven(true);
				}
			}
		}
	}

	public void giveAllAnswersToCells(int[][] completedBoard) {
		for (int i = 0; i < this.currentBoard.size(); i++) {
			for (int j = 0; j < this.currentBoard.size(); j++) {
				this.componentList[i][j].setValue(completedBoard[i][j]);
				System.out.println(completedBoard[i][j]);
				this.componentList[i][j].repaint();
			}
		}
	}

}
