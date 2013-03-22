package Sudoku;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class InnerGrid {

	ArrayList<CellBlock> gridList;

	public InnerGrid(ArrayList<CellBlock> gridList) {
		this.gridList = new ArrayList<CellBlock>();
		Iterator<CellBlock> iterator = gridList.iterator();
		while (iterator.hasNext()) {
			this.gridList.add(iterator.next());
		}
	}

	@Test
	public void testInnerGridAcceptsFilledGrid() {
		ArrayList<CellBlock> gridCellList = new ArrayList<CellBlock>();
		for (int i = 0; i < 9; i++) {
			CellBlock cell = new CellBlock();
			cell.setAnswer(i + 1);
			gridCellList.add(cell);
		}
		InnerGrid grid = new InnerGrid(gridCellList);
		ArrayList<CellBlock> gridList = grid.getGridList();
		for (int i = 0; i < 9; i++) {
			assertEquals(i + 1, gridList.get(i).getAnswer());
		}
	}

}
