package SudokuTest;



import java.io.File;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import Sudoku.LeaderBoard;
import Sudoku.LeaderBoard.Score;
import Sudoku.TimerLabel;


public class LeaderBoardTest {
	
	@Before
	public void RemoveTestFiles(){
		File fileSIMPLE = new File("simple");
		fileSIMPLE.delete();
		File fileEASY = new File("easy");
		fileEASY.delete();
		File fileMEDIUM = new File("medium");
		fileMEDIUM.delete();
		File fileDIFFICULT = new File("difficult");
		fileDIFFICULT.delete();
		File fileEVIL = new File("evil");
		fileEVIL.delete();		
	}
	
	@Test
	public void TestAddScoreToBoard(){
		LeaderBoard x = new LeaderBoard();
		TimerLabel score = new TimerLabel();
		score.updateTime(30);
		Score[] leaders = x.submitScore("easy", "TOM", score);
		Assert.assertEquals(10, leaders.length);
	}
	
	@Test
	public void TestScoreCompareIsCorrect(){
		LeaderBoard x = new LeaderBoard();
		TimerLabel timer = new TimerLabel();
		timer.pause();
		Score score1 = x.new Score("Tom", timer);
		timer.updateTime(30);
		Score score2 = x.new Score("George", timer);
		Assert.assertEquals(-1, score1.compareTo(score2));
	}

	@Test
	public void TestInsertInCorrectOrder(){
		LeaderBoard x = new LeaderBoard();
		TimerLabel timer = new TimerLabel();
		timer.pause();
		x.submitScore("easy", "Tom", timer);
		timer.updateTime(30);
		Score[] easy = x.submitScore("easy", "George", timer);
		Assert.assertEquals("Tom", easy[0].getName());
		Assert.assertEquals("George", easy[1].getName());
	}
	
	@Test
	public void TestNewScoreGetGetsRidOfLowestScore(){
		LeaderBoard x = new LeaderBoard();
		TimerLabel timer = new TimerLabel();
		timer.pause();
		x.submitScore("easy", "Tom", timer);
		timer.updateTime(30);
		x.submitScore("easy", "Susi", timer);
		timer.updateTime(30);
		x.submitScore("easy", "Sam", timer);
		timer.updateTime(30);
		x.submitScore("easy", "Jeremy", timer);
		timer.updateTime(30);
		x.submitScore("easy", "George", timer);
		timer.updateTime(30);
		x.submitScore("easy", "Sriram", timer);
		timer.updateTime(30);
		x.submitScore("easy", "Nadine", timer);
		timer.updateTime(30);
		x.submitScore("easy", "Seth", timer);
		timer.updateTime(30);
		x.submitScore("easy", "Dr.B", timer);
		timer.updateTime(30);
		x.submitScore("easy", "Cary", timer);
		timer.updateTime(-5);
		Score[] easy = x.submitScore("easy", "Alex", timer);
		Assert.assertEquals("Alex", easy[9].getName());
	}
	
	@Test
	public void TestNewScoreGetGetsRidOfLowestScoreSimple(){
		LeaderBoard x = new LeaderBoard();
		TimerLabel timer = new TimerLabel();
		timer.pause();
		x.submitScore("simple", "Tom", timer);
		timer.updateTime(30);
		x.submitScore("simple", "Susi", timer);
		timer.updateTime(30);
		x.submitScore("simple", "Sam", timer);
		timer.updateTime(30);
		x.submitScore("simple", "Jeremy", timer);
		timer.updateTime(30);
		x.submitScore("simple", "George", timer);
		timer.updateTime(30);
		x.submitScore("simple", "Sriram", timer);
		timer.updateTime(30);
		x.submitScore("simple", "Nadine", timer);
		timer.updateTime(30);
		x.submitScore("simple", "Seth", timer);
		timer.updateTime(30);
		x.submitScore("simple", "Dr.B", timer);
		timer.updateTime(30);
		x.submitScore("simple", "Cary", timer);
		timer.updateTime(-5);
		Score[] easy = x.submitScore("simple", "Alex", timer);
		Assert.assertEquals("Alex", easy[9].getName());
	}
	
	@Test
	public void TestNewScoreGetGetsRidOfLowestScoreMedium(){
		LeaderBoard x = new LeaderBoard();
		TimerLabel timer = new TimerLabel();
		timer.pause();
		x.submitScore("medium", "Tom", timer);
		timer.updateTime(30);
		x.submitScore("medium", "Susi", timer);
		timer.updateTime(30);
		x.submitScore("medium", "Sam", timer);
		timer.updateTime(30);
		x.submitScore("medium", "Jeremy", timer);
		timer.updateTime(30);
		x.submitScore("medium", "George", timer);
		timer.updateTime(30);
		x.submitScore("medium", "Sriram", timer);
		timer.updateTime(30);
		x.submitScore("medium", "Nadine", timer);
		timer.updateTime(30);
		x.submitScore("medium", "Seth", timer);
		timer.updateTime(30);
		x.submitScore("medium", "Dr.B", timer);
		timer.updateTime(30);
		x.submitScore("medium", "Cary", timer);
		timer.updateTime(-5);
		Score[] easy = x.submitScore("medium", "Alex", timer);
		Assert.assertEquals("Alex", easy[9].getName());
	}
	
	@Test
	public void TestNewScoreGetGetsRidOfLowestScoreDifficult(){
		LeaderBoard x = new LeaderBoard();
		TimerLabel timer = new TimerLabel();
		timer.pause();
		x.submitScore("difficult", "Tom", timer);
		timer.updateTime(30);
		x.submitScore("difficult", "Susi", timer);
		timer.updateTime(30);
		x.submitScore("difficult", "Sam", timer);
		timer.updateTime(30);
		x.submitScore("difficult", "Jeremy", timer);
		timer.updateTime(30);
		x.submitScore("difficult", "George", timer);
		timer.updateTime(30);
		x.submitScore("difficult", "Sriram", timer);
		timer.updateTime(30);
		x.submitScore("difficult", "Nadine", timer);
		timer.updateTime(30);
		x.submitScore("difficult", "Seth", timer);
		timer.updateTime(30);
		x.submitScore("difficult", "Dr.B", timer);
		timer.updateTime(30);
		x.submitScore("difficult", "Cary", timer);
		timer.updateTime(-5);
		Score[] easy = x.submitScore("difficult", "Alex", timer);
		Assert.assertEquals("Alex", easy[9].getName());
	}
	
	@Test
	public void TestNewScoreGetGetsRidOfLowestScoreEvil(){
		LeaderBoard x = new LeaderBoard();
		TimerLabel timer = new TimerLabel();
		timer.pause();
		x.submitScore("evil", "Tom", timer);
		timer.updateTime(30);
		x.submitScore("evil", "Susi", timer);
		timer.updateTime(30);
		x.submitScore("evil", "Sam", timer);
		timer.updateTime(30);
		x.submitScore("evil", "Jeremy", timer);
		timer.updateTime(30);
		x.submitScore("evil", "George", timer);
		timer.updateTime(30);
		x.submitScore("evil", "Sriram", timer);
		timer.updateTime(30);
		x.submitScore("evil", "Nadine", timer);
		timer.updateTime(30);
		x.submitScore("evil", "Seth", timer);
		timer.updateTime(30);
		x.submitScore("evil", "Dr.B", timer);
		timer.updateTime(30);
		x.submitScore("evil", "Cary", timer);
		timer.updateTime(-5);
		Score[] easy = x.submitScore("evil", "Alex", timer);
		Assert.assertEquals("Alex", easy[9].getName());
	}
}
