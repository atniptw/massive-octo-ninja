package SudokuTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import Sudoku.CellBlock;
import Sudoku.SudokuStandardRegion;
import Sudoku.SudokuTable;

public class SudokuTableTest {
	
	@Test
	public void testTableInitializesWhenGivenRegions() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		assertNotNull(new SudokuTable(rows, columns, innerGrids));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTableThrowsErrorWhenGivenFewerNumberOfRows() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		columns.add(new SudokuStandardRegion(list));
		innerGrids.add(new SudokuStandardRegion(list));
		
		new SudokuTable(rows, columns, innerGrids);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTableThrowsErrorWhenGivenExtraNumberOfRows() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		CellBlock block2 = new CellBlock();
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		ArrayList<CellBlock> list2 = new ArrayList<CellBlock>();
		list2.add(block2);
		
		rows.add(new SudokuStandardRegion(list));
		rows.add(new SudokuStandardRegion(list2));
		columns.add(new SudokuStandardRegion(list));
		innerGrids.add(new SudokuStandardRegion(list));
		
		new SudokuTable(rows, columns, innerGrids);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTableThrowsErrorWhenGivenFewerNumberOfColumns() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		rows.add(new SudokuStandardRegion(list));
		innerGrids.add(new SudokuStandardRegion(list));
		
		new SudokuTable(rows, columns, innerGrids);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTableThrowsErrorWhenGivenExtraNumberOfColumns() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		CellBlock block2 = new CellBlock();
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		ArrayList<CellBlock> list2 = new ArrayList<CellBlock>();
		list2.add(block2);
		
		rows.add(new SudokuStandardRegion(list));
		columns.add(new SudokuStandardRegion(list));
		columns.add(new SudokuStandardRegion(list2));
		innerGrids.add(new SudokuStandardRegion(list));
		
		new SudokuTable(rows, columns, innerGrids);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTableThrowsErrorWhenGivenExtraNumberOfInnerGrids() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		CellBlock block2 = new CellBlock();
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		ArrayList<CellBlock> list2 = new ArrayList<CellBlock>();
		list2.add(block2);
		
		rows.add(new SudokuStandardRegion(list));
		columns.add(new SudokuStandardRegion(list));
		innerGrids.add(new SudokuStandardRegion(list));
		innerGrids.add(new SudokuStandardRegion(list2));
		
		new SudokuTable(rows, columns, innerGrids);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTableThrowsErrorWhenGivenFewerNumberOfInnerGrids() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		rows.add(new SudokuStandardRegion(list));
		columns.add(new SudokuStandardRegion(list));
		
		new SudokuTable(rows, columns, innerGrids);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTableThrowsErrorWhenARowIsLongerThanOtherRegions() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		CellBlock block2 = new CellBlock();
		
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		ArrayList<CellBlock> longList = new ArrayList<CellBlock>();
		longList.add(block);
		longList.add(block2);
		
		rows.add(new SudokuStandardRegion(longList));
		columns.add(new SudokuStandardRegion(list));
		innerGrids.add(new SudokuStandardRegion(list));
		
		new SudokuTable(rows, columns, innerGrids);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTableThrowsErrorWhenAColumnIsLongerThanOtherRegions() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		CellBlock block2 = new CellBlock();
		
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		ArrayList<CellBlock> longList = new ArrayList<CellBlock>();
		longList.add(block);
		longList.add(block2);
		
		rows.add(new SudokuStandardRegion(list));
		columns.add(new SudokuStandardRegion(longList));
		innerGrids.add(new SudokuStandardRegion(list));
		
		new SudokuTable(rows, columns, innerGrids);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTableThrowsErrorWhenAInnerGridIsLongerThanOtherRegions() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		CellBlock block2 = new CellBlock();
		
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		ArrayList<CellBlock> longList = new ArrayList<CellBlock>();
		longList.add(block);
		longList.add(block2);
		
		rows.add(new SudokuStandardRegion(list));
		columns.add(new SudokuStandardRegion(longList));
		innerGrids.add(new SudokuStandardRegion(longList));
		
		new SudokuTable(rows, columns, innerGrids);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTableThrowsErrorWhenCellsForCoorespondingLocationsAreDifferent() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		CellBlock block2 = new CellBlock();
		
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		ArrayList<CellBlock> differentList = new ArrayList<CellBlock>();
		differentList.add(block2);
		
		rows.add(new SudokuStandardRegion(list));
		columns.add(new SudokuStandardRegion(differentList));
		innerGrids.add(new SudokuStandardRegion(list));
		
		new SudokuTable(rows, columns, innerGrids);
		
	}

	@Test 
	public void testTableIsValidReturnsTrueWhenValid() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		
		rows.add(new SudokuStandardRegion(list));
		columns.add(new SudokuStandardRegion(list));
		innerGrids.add(new SudokuStandardRegion(list));
		
		SudokuTable table = new SudokuTable(rows, columns, innerGrids);
		assertTrue(table.isValid());
	}
	
	@Test 
	public void testTableIsValidReturnsFalseWhenInvalid() {
		ArrayList<SudokuStandardRegion> rows = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> columns = new ArrayList<SudokuStandardRegion>();
		ArrayList<SudokuStandardRegion> innerGrids = new ArrayList<SudokuStandardRegion>();
		
		CellBlock block = new CellBlock();
		CellBlock block2 = new CellBlock();
		block.setAnswer(1);
		block2.setAnswer(1);
		ArrayList<CellBlock> list = new ArrayList<CellBlock>();
		list.add(block);
		list.add(block2);
		
		rows.add(new SudokuStandardRegion(list));
		columns.add(new SudokuStandardRegion(list));
		innerGrids.add(new SudokuStandardRegion(list));
		
		SudokuTable table = new SudokuTable(rows, columns, innerGrids);
		assertFalse(table.isValid());
	}


}
