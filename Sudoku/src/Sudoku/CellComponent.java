package Sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

public class CellComponent extends JComponent {
	
	CellBlock cell;
	
	public CellComponent(CellBlock cell) {
		super();
		this.cell = cell;
		this.setOpaque(true);
	}
	
	public void paintComponent(Graphics g) {
		this.setFont(new Font(this.getFont().getName(), this.getFont().getStyle(), 25));
		if (!cell.getIsValid()) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(cell.getAnswer()), this.getWidth()/3, this.getHeight()/2);
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(cell.getAnswer()), this.getWidth()/3, this.getHeight()/2);
		}
		
	}

}
