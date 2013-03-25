package sudoku;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * For future reference, it may be useful to know that I currently plan for Grids to 
 * indexed simply like rows/columns but with wrapping.
 *  
 * See below:
 * __________________________
 * |		|		|		|
 * |  i = 1	|	2	|	3	|
 * |________|_______|_______|
 * |		|		|		|
 * |	4	|	5	|	6	|
 * |________|_______|_______|
 * |		|		|		|
 * |	7	|	8	|	9	|
 * |________|_______|_______|
 * 
 */

public class InnerGrid {

	ArrayList<CellBlock> gridList;

	public InnerGrid(ArrayList<CellBlock> gridList) {
		this.gridList = new ArrayList<CellBlock>();
		Iterator<CellBlock> iterator = gridList.iterator();
		while (iterator.hasNext()) {
			this.gridList.add(iterator.next());
		}
	}

	public ArrayList<CellBlock> getGridList() {
		return this.gridList;

	}
	
	public CellBlock getCell(int pos) {
		if (pos > this.gridList.size() - 1 || pos < 0){
			throw new IllegalArgumentException();
		}
		return this.gridList.get(pos);
	}
	
	public void setAnswer(int pos, int val) {
		if (pos > this.gridList.size() - 1 || pos < 0 || val < 1 || val > this.gridList.size()){
			throw new IllegalArgumentException();
		}
		this.gridList.get(pos).setAnswer(val);
	}

	public boolean isValidGrid() {
		for (int i = 0; i < this.gridList.size(); i++) {
			for (int j = i + 1; j < this.gridList.size(); j++) {
				if (this.gridList.get(i).getAnswer() == this.gridList.get(j)
						.getAnswer()) {
					return false;
				}
			}
		}
		return true;
	}

	public ArrayList<CellBlock> getConflictingCells() {
		ArrayList<CellBlock> conflicts = new ArrayList<CellBlock>();
		for (int i = 0; i < this.gridList.size(); i++) {
			for (int j = i + 1; j < this.gridList.size(); j++) {
				if (this.gridList.get(i).getAnswer() == this.gridList.get(j)
						.getAnswer()) {
					if (!conflicts.contains(this.gridList.get(i))) {
						conflicts.add(this.gridList.get(i));
					}
					if (!conflicts.contains(this.gridList.get(j))) {
						conflicts.add(this.gridList.get(j));
					}
				}
			}
		}
		return conflicts;
	}

}
