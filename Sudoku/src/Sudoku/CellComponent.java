package Sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class CellComponent extends JComponent {

	CellBlock cell;
	boolean selected;
	boolean given;

	public CellComponent(CellBlock cell, boolean given) {
		super();
		this.cell = cell;
		this.selected = false;
		this.given = given;
		this.setOpaque(true);
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setValue(int value) {
		if (!this.given) {
			this.cell.setAnswer(value);
			this.repaint();
		}
	}

	public void setGiven(boolean given) {
		this.given = given;
	}

	public void paintComponent(Graphics g) {

		this.setFont(new Font(this.getFont().getName(), this.getFont()
				.getStyle(), this.getHeight() / 2));
		if (this.selected) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			if (cell.getAnswer() != 0){
			g.drawString(Integer.toString(cell.getAnswer()),
					this.getWidth() / 3, this.getHeight() / 2);
			}
		} else if (cell.getAnswer() == 0) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
		} else if (!cell.getIsValid()) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(cell.getAnswer()),
					this.getWidth() / 3, this.getHeight() / 2);
		} else if (this.given) {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(cell.getAnswer()),
					this.getWidth() / 3, this.getHeight() / 2);
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(cell.getAnswer()),
					this.getWidth() / 3, this.getHeight() / 2);
		}
	}

}
