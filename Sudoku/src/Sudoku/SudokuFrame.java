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
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Sudoku.LeaderBoard.Score;

public class SudokuFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -37081277569169971L;
	private ISudokuBoard currentBoard;
	private SudokuComponent sudokuComponent;
	public ResourceBundle bundle;

	// Create a file chooser
	final JFileChooser fc = new JFileChooser();
	private TimerLabel timerLabel;
	private String diff;

	public SudokuFrame(String language, String country) {

		this.setLayout(new BorderLayout());
		Locale loc = new Locale(language, country);
		this.bundle = ResourceBundle.getBundle("MessagesBundle", loc);

		getNewBoard();
		if (this.currentBoard == null) {
			System.out.println(this.bundle.getString("board_create_error"));
			System.exit(-1);
		}

		this.setSize(1000, 1000);

		addMenuBar();

		this.setVisible(true);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void addMenuBar() {
		JMenuBar menubar = new JMenuBar();

		JMenu file = new JMenu(this.bundle.getString("file"));

		JMenuItem newGame = new JMenuItem(this.bundle.getString("new_game"));

		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				getNewBoard();
			}
		});

		// TODO String Refactoring Here:
		JMenuItem openGame = new JMenuItem(this.bundle.getString("open_game"));
		openGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// In response to a button click:
				int returnVal = fc.showOpenDialog(SudokuFrame.this);
				if (returnVal == fc.APPROVE_OPTION) {
					try {
						File file = fc.getSelectedFile();
						FileInputStream fin = new FileInputStream(file);
						ObjectInputStream ois = new ObjectInputStream(fin);
						ISudokuBoard openBoard = (ISudokuBoard) ois
								.readObject();
						ois.close();
						remove(sudokuComponent);
						currentBoard = openBoard;
						sudokuComponent = new SudokuComponent(currentBoard);
						add(sudokuComponent, BorderLayout.CENTER);
						repaint();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		// TODO String Refactoring Here:
		JMenuItem saveGame = new JMenuItem(this.bundle.getString("save_game"));
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int returnVal = fc.showSaveDialog(SudokuFrame.this);
				if (returnVal == fc.APPROVE_OPTION) {
					try {
						File file = fc.getSelectedFile();
						FileOutputStream fout = new FileOutputStream(file);
						ObjectOutputStream oos = new ObjectOutputStream(fout);
						oos.writeObject(currentBoard);
						oos.close();
						System.out.println("Done");

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		JMenuItem exitOption = new JMenuItem(this.bundle.getString("exit"));

		exitOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		JMenu help = new JMenu(this.bundle.getString("help"));

		JMenuItem getOne = new JMenuItem(this.bundle.getString("hint"));

		JMenuItem getAll = new JMenuItem(this.bundle.getString("give_up"));

		getOne.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sudokuComponent.giveAnswerToSelectedCell();
				timerLabel.updateTime(30);
				currentBoard.setConflictingCellsToInvalid();
				if (currentBoard.isComplete()) {
					handleWin();
				}
			}

		});

		getAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sudokuComponent.giveAllAnswersToCells();
				currentBoard.setConflictingCellsToInvalid();
				handleCompleteOption();
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

		String[] boards = { this.bundle.getString("standard") };
		// TODO This used to break it, not sure if it still does. Sorry!
		// String[] difficulties = { this.bundle.getString("simple"),
		// this.bundle.getString("easy"), this.bundle.getString("medium"),
		// this.bundle.getString("difficult"),
		// this.bundle.getString("evil") };
		String[] difficulties = { this.bundle.getString("simple"),
				this.bundle.getString("easy"), this.bundle.getString("medium"),
				this.bundle.getString("difficult"),
				this.bundle.getString("evil") };

		JComboBox boardTypes = new JComboBox(boards);
		JComboBox difficultiesList = new JComboBox(difficulties);

		JPanel myPanel = new JPanel();
		myPanel.add(boardTypes);
		myPanel.add(difficultiesList);

		int result = JOptionPane.showConfirmDialog(null, myPanel,

		this.bundle.getString("choose_board"),

		JOptionPane.OK_CANCEL_OPTION);
		diff = (String) difficultiesList.getSelectedItem();

		if (result == JOptionPane.OK_OPTION) {
			if (boardTypes.getSelectedItem() == this.bundle
					.getString("standard")) {
				this.currentBoard = new StandardSudokuBoard(9);
				this.currentBoard.populateBoard();
				BoardAdjuster.adjustForDifficulty(this.currentBoard,
						(String) difficultiesList.getSelectedItem(),
						this.bundle);
				// int[][] adjustedValues = BoardAdjuster.adjustForDifficulty(
				// this.completedBoard,
				// (String) difficultiesList.getSelectedItem(),
				// this.bundle);
				// ArrayList<CellBlock> singleAdjustedArrayValues = new
				// ArrayList<CellBlock>(
				// 81);
				// for (int i = 0; i < 9; i++) {
				// for (int j = 0; j < 9; j++) {
				// CellBlock newCell = new CellBlock();
				// newCell.setAnswer(adjustedValues[i][j]);
				// singleAdjustedArrayValues.add(newCell);
				// }
				// }

				// this.currentBoard = new StandardSudokuBoard(
				// singleAdjustedArrayValues,
				// this.completedBoard.getBoardSolution());
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
							if (currentBoard.isComplete()) {
								handleWin();
							}
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

	private void handleWin() {
		this.timerLabel.pause();
		String name = JOptionPane.showInputDialog(this, "Type in name:");
		LeaderBoard x = new LeaderBoard();
		Score[] scores = x.submitScore(diff, name, this.timerLabel);
		String scoreText = "Name Score<br>";
		for (Score score : scores) {
			if (score != null)
				scoreText += score.getName() + " " + score.getScore() + "<br>";
		}
		JFrame frame = new JFrame();
		JLabel label = new JLabel(String.format("<html>You won in %s<br>",
				this.timerLabel.getText()) + scoreText + "</html>");

		System.out.print(name);
		frame.add(label);

		frame.setSize(300, 300);
		frame.setVisible(true);

	}

	private void handleCompleteOption() {
		this.timerLabel.pause();
		JFrame frame = new JFrame();
		JLabel label = new JLabel(String.format("You are a dirty cheater"));
		frame.add(label);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
