package Sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class LeaderBoard {

	Score[] simple = new Score[10];
	Score[] easy = new Score[10];
	Score[] medium = new Score[10];
	Score[] difficult = new Score[10];
	Score[] evil = new Score[10];

	public Score[] submitScore(String diff, String name, TimerLabel score) {
		Score newScore = new Score(name, score);
		if (diff.equals("simple")) {
			simple = LoadFile(diff);
			simple = insertScore(simple, newScore);
			SaveFile(diff, simple);
			return simple;
		} else if (diff.equals("easy")) {
			easy = LoadFile(diff);
			easy = insertScore(easy, newScore);
			SaveFile(diff, easy);
			return easy;
		} else if (diff.equals("medium")) {
			medium = LoadFile(diff);
			medium = insertScore(medium, newScore);
			SaveFile(diff, medium);
			return medium;
		} else if (diff.equals("difficult")) {
			difficult = LoadFile(diff);
			difficult = insertScore(difficult, newScore);
			SaveFile(diff, difficult);
			return difficult;
		} else {
			evil = LoadFile(diff);
			evil = insertScore(evil, newScore);
			SaveFile(diff, evil);
			return evil;
		}
	}

	private Score[] LoadFile(String diff) {

		Score[] temp = new Score[10];
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader(diff));
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] vals = line.split(" ");
				temp[i] = new Score(vals[0], vals[1]);
				i++;
			}
			br.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return temp;
	}

	private void SaveFile(String diff, Score[] scoreList) {
		try {
			String content = "";
			for (int i = 0; i < 10; i++) {
				if(scoreList[i] == null){
					break;
				}
				content += scoreList[i].getName() + " " + scoreList[i].getScore() + "\n"; 
			}


			File file = new File(diff);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Score[] insertScore(Score[] scoreBoard, Score newScore) {
		int i;
		for (i = 0; i < 10; i++) {
			if (scoreBoard[i] == null) {
				scoreBoard[i] = newScore;
				return scoreBoard;
			}
		}
		if (scoreBoard[9].compareTo(newScore) == 1) {
			scoreBoard[9] = newScore;
			Arrays.sort(scoreBoard);
		}
		return scoreBoard;
	}

	public class Score implements Comparable<Object> {
		private String name;
		private int score;

		public Score(String name, TimerLabel timer) {
			setName(name);
			setScore(timer);
		}

		public Score(String name, String score) {
			this.name = name;
			this.score = Integer.parseInt(score);
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setScore(TimerLabel timer) {
			String time = timer.getText();
			score = (Integer.parseInt(time.split(":")[0]) * 60)
					+ Integer.parseInt(time.split(":")[1]);
		}

		@Override
		public int compareTo(Object o) {
			Score other = (Score) o;
			if (this.getScore() > other.getScore()) {
				return 1;
			} else if (this.getScore() < other.getScore()) {
				return -1;
			} else
				return 0;
		}
	}

}
