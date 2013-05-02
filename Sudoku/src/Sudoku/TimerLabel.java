package Sudoku;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerLabel extends JLabel {

	private int minutes;
	private int seconds;
	private Timer timer;

	public TimerLabel() {
		this.minutes = 0;
		this.seconds = 0;
		this.setFont(new Font("Arial", Font.PLAIN, 30));
		this.setText(this.minutes + ":0" + this.seconds);
		this.timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateTime(1);
			}

		});
		this.timer.start();
	}
	
	public void pause() {
		this.timer.stop();
	}

	public void updateTime(int seconds) {
		this.minutes += (this.seconds + seconds) / 60;
		this.seconds = (this.seconds + seconds) % 60;
		if (this.seconds < 10) {
			this.setText(this.minutes + ":0" + this.seconds);
		} else {
			this.setText(this.minutes + ":" + this.seconds);
		}
	}

}
