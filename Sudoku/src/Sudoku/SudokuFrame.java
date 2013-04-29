package Sudoku;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SudokuFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -37081277569169971L;
	private ISudokuBoard currentBoard;
	private int[][] completedBoard;
	private SudokuComponent sudokuComponent;
	// Create a file chooser
	final JFileChooser fc = new JFileChooser();
	private TimerLabel timerLabel;

	public SudokuFrame() {
		this.setLayout(new BorderLayout());
		getNewBoard();
		if (this.currentBoard == null) {
			System.out.println("There was an error creating the board");
			System.exit(-1);
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

		JMenuItem openGame = new JMenuItem("Open Game");
		openGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// In response to a button click:
				int returnVal = fc.showOpenDialog(SudokuFrame.this);
				if (returnVal == fc.APPROVE_OPTION) {
					try {
						File file = fc.getSelectedFile();
						FileInputStream fin = new FileInputStream(file);
						ObjectInputStream ois = new ObjectInputStream(fin);
						ISudokuBoard openBoard = (ISudokuBoard) ois.readObject();
						ois.close();
						currentBoard = openBoard;
						//invalidate();
						//validate();
						//repaint();
						System.out.print(openBoard.getAnswer(0, 2));
						System.out.print(currentBoard.getAnswer(0, 2));

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		JMenuItem saveGame = new JMenuItem("Save Game");
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int returnVal = fc.showSaveDialog(SudokuFrame.this);
				if(returnVal == fc.APPROVE_OPTION){
					   try{
						   File file = fc.getSelectedFile();
							FileOutputStream fout = new FileOutputStream(file);
							ObjectOutputStream oos = new ObjectOutputStream(fout);   
							oos.writeObject(currentBoard);
							oos.close();
							System.out.println("Done");
					 
						   }catch(Exception ex){
							   ex.printStackTrace();
						   }
				}
			}
		});

		JMenuItem exitOption = new JMenuItem("Exit");
		exitOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		JMenu help = new JMenu("Help");

		JMenuItem getOne = new JMenuItem("Hint");

		JMenuItem getAll = new JMenuItem("I Give Up");

		getOne.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sudokuComponent.giveAnswerToSelectedCell(completedBoard);
				timerLabel.updateTime(30);
				currentBoard.setConflictingCellsToInvalid();
			}

		});

		getAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sudokuComponent.giveAllAnswersToCells(completedBoard);
				currentBoard.setConflictingCellsToInvalid();
			}

		});

		file.add(newGame);
		file.add(openGame);
		file.add(saveGame);
		file.add(exitOption);
		help.add(getOne);
		help.add(getAll);
		menubar.add(file);
		menubar.add(help);
		this.setJMenuBar(menubar);
	}

	private void getNewBoard() {
		String[] boards = { "Standard" };
		String[] difficulties = { "Simple", "Easy", "Medium", "Difficult",
				"Evil" };
		JComboBox boardTypes = new JComboBox(boards);
		JComboBox difficultiesList = new JComboBox(difficulties);

		JPanel myPanel = new JPanel();
		myPanel.add(boardTypes);
		myPanel.add(difficultiesList);

		int result = JOptionPane.showConfirmDialog(null, myPanel,
				"Please Choose Board Type and Difficulty",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			if (boardTypes.getSelectedItem() == "Standard") {
				this.completedBoard = SudokuGenerator.generateBoard(9);
				int[][] adjustedValues = BoardAdjuster.adjustForDifficulty(
						this.completedBoard, BoardAdjuster.Difficulty
								.valueOf(((String) difficultiesList
										.getSelectedItem()).toUpperCase()));
				ArrayList<CellBlock> singleAdjustedArrayValues = new ArrayList<CellBlock>(
						81);
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						CellBlock newCell = new CellBlock();
						newCell.setAnswer(adjustedValues[i][j]);
						singleAdjustedArrayValues.add(newCell);
					}
				}

				this.currentBoard = new StandardSudokuBoard(
						singleAdjustedArrayValues);
				this.currentBoard.setConflictingCellsToInvalid();
				if (this.sudokuComponent != null) {
					this.remove(this.sudokuComponent);
				}
				this.sudokuComponent = new SudokuComponent(this.currentBoard);
				this.add(this.sudokuComponent, BorderLayout.CENTER);
				JPanel buttonPanel = new JPanel();
				for (int i = 0; i < this.currentBoard.size(); i++) {
					JButton button = new JButton(String.format("%d", i + 1));
					button.setPreferredSize(new Dimension(50, 80));
					button.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							sudokuComponent.setSelectedCell(Integer
									.parseInt(((JButton) e.getSource())
											.getText()));
							currentBoard.setConflictingCellsToInvalid();
						}

					});
					buttonPanel.add(button);
				}
				this.add(buttonPanel, BorderLayout.SOUTH);
				this.timerLabel = new TimerLabel();
				this.add(this.timerLabel, BorderLayout.NORTH);
			}
		}
	}
}
