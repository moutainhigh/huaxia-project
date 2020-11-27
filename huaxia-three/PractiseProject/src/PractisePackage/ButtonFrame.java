package PractisePackage;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonFrame extends JFrame {
	
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	public ButtonFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("blue");
		JButton redButton = new JButton("red");
		buttonPanel = new JPanel();
		buttonPanel.add(yellowButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(redButton);
		add(buttonPanel);
		
		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction redAction = new ColorAction(Color.RED);
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
	}

	public ButtonFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ButtonFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ButtonFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new ButtonFrame();
		frame.setTitle("colorTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	private class ColorAction implements ActionListener
	{
		private Color backgroundColor;
		public ColorAction(Color c){
			backgroundColor = c;
		}
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			buttonPanel.setBackground(backgroundColor);
		}
	}
}
