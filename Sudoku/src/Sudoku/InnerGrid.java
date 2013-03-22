package Sudoku;

import java.util.ArrayList;
import java.util.Iterator;

public class InnerGrid {
	
ArrayList<CellBlock> gridList;
	
	public InnerGrid(ArrayList<CellBlock> gridList){
		this.gridList = new ArrayList<CellBlock>();
		Iterator<CellBlock> iterator = gridList.iterator();
		while(iterator.hasNext()) {
			this.gridList.add(iterator.next());
		}
	}

}
