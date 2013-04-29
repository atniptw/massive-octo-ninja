package SudokuTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Sudoku.SudokuFrame;

public class LocalizationTest {

	@Test
	public void testError_en_US() {
		SudokuFrame frame = new SudokuFrame("en", "US");
		assertEquals("There was an error creating the board",
				frame.bundle.getString("board_create_error"));
	}

	@Test
	public void testError_es_MX() {
		SudokuFrame frame = new SudokuFrame("es", "MX");
		assertEquals("Se ha producido un error al crear el tablero de juego",
				frame.bundle.getString("board_create_error"));
	}

}
