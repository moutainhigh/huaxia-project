package PractisePackage;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class ProgressBarFrame extends JFrame {

	public static final int TEXT_ROWS = 10;
	public static final int TEXT_COLUMNS = 40;
	private JButton startButton;
	private JProgressBar progressBar;
	private JCheckBox checkBox;
	private JTextArea textArea;
	private SimulatedActivity activity;
	public ProgressBarFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		textArea = new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
		final int MAX = 1000;
		JPanel panel = new JPanel();
		startButton = new JButton("Start");
		progressBar = new JProgressBar(0,MAX);
		progressBar.setStringPainted(true);
		panel.add(startButton);
		panel.add(progressBar);
		checkBox = new JCheckBox("indeterminate");
		checkBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent enent) {
				// TODO Auto-generated method stub
				progressBar.setIndeterminate(checkBox.isSelected());
				progressBar.setStringPainted(progressBar.isIndeterminate());
			}
			
		});
		panel.add(checkBox);
		add(new JScrollPane(textArea),BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				startButton.setEnabled(false);
				activity = new SimulatedActivity(MAX);
				activity.execute();
			}
		});
		pack();
	}

	public ProgressBarFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ProgressBarFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ProgressBarFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	class SimulatedActivity extends SwingWorker<Void,Integer>
	{
		private int current;
		private int target;
		public SimulatedActivity(int t)
		{
			current = 0;
			target = t;
		}
		protected Void doInBackground() throws Exception{
			try{
				while(current<target)
				{
					Thread.sleep(100);
					current++;
					publish(current);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			return null;
		}
		protected void process(List<Integer> chunks){
			for(Integer chunk:chunks){
				textArea.append(chunk+"\n");
				progressBar.setValue(chunk);
			}
		}
		protected void done()
		{
			startButton.setEnabled(true);
		}
	}
	public static void main(String args[]){
		ProgressBarFrame frame = new ProgressBarFrame();
		frame.setVisible(true);
	}
}
