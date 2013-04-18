package Sudoku;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SudokuFrame extends JFrame {

	private ISudokuBoard board;	
	private SudokuComponent sudokuComponent;
	
	public SudokuFrame() {
		this.setLayout(new BorderLayout());
		getNewBoard();
		if (this.board == null) {
			System.out.println("There was an error creating the board");
		}
		
		this.setSize(1000, 1000);
		
		addMenuBar();
		
		this.setVisible(true);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void addMenuBar() {
		JMenuBar menubar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				getNewBoard();
			}
		});
		
		JMenuItem exitOption = new JMenuItem("Exit");
		exitOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		
		file.add(newGame);
		file.add(exitOption);
		menubar.add(file);
		this.setJMenuBar(menubar);
	}
	
	private void getNewBoard() {
		String[] boards = {"Standard"};
		String[] difficulties = {"Extremely Easy", "Easy", "Medium", "Difficult", "Evil"};
		JComboBox boardTypes = new JComboBox(boards);
		JComboBox difficultiesList = new JComboBox(difficulties);
		
		JPanel myPanel = new JPanel();
		myPanel.add(boardTypes);
		myPanel.add(difficultiesList);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Choose Board Type and Difficulty", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			if (boardTypes.getSelectedItem() == "Standard") {
				int[][] cellValues = SudokuGenerator.generateBoard(9);
				ArrayList<CellBlock> singleArrayValues = new ArrayList<CellBlock>(81);
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						CellBlock newCell = new CellBlock();
						newCell.setAnswer(cellValues[i][j]);
						singleArrayValues.add(newCell);
					}
				}
				
				this.board = new StandardSudokuBoard(singleArrayValues);
				this.board.setConflictingCellsToInvalid();
				if (this.sudokuComponent!=null){
				this.remove(this.sudokuComponent);
				}
				this.sudokuComponent = new SudokuComponent(this.board);
				this.add(this.sudokuComponent, BorderLayout.CENTER);
				JPanel buttonPanel = new JPanel();
				for (int i = 0; i < this.board.size(); i++) {
					JButton button = new JButton(String.format("%d", i+1));
					button.setPreferredSize(new Dimension(50, 80));
					button.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							sudokuComponent.setSelectedCell(Integer.parseInt(((JButton) e.getSource()).getText()));
							board.setConflictingCellsToInvalid();
						}
						
					});
					buttonPanel.add(button);
				}
				this.add(buttonPanel, BorderLayout.SOUTH);
			}
		}
	}
}
