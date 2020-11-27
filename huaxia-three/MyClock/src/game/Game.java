package game;

import java.awt.Container;

import javax.swing.JFrame;


public class Game {
	public static void main(String args[]){
		JFrame w = new JFrame();
		GamePanel mainPanel = new GamePanel(20,30);
		int[] a = mainPanel.retrurnSize();
		w.setSize(a[0],a[1]);
		Container c= w.getContentPane();
		c.add(mainPanel);
		w.setVisible(true);
	}
}
