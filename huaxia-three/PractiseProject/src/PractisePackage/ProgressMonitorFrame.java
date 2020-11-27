package PractisePackage;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import javax.swing.Timer;

public class ProgressMonitorFrame extends JFrame {

	public static final int TEXT_ROWS = 10;
	public static final int TEXT_COLUMNS = 40;
	
	private Timer cancelMonitor;
	private JButton startButton;
	private ProgressMonitor progressDialog;
	private JTextArea textArea;
	private SimulatedActivity activity;
	
	public ProgressMonitorFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		textArea = new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
		JPanel panel = new JPanel();
		startButton = new JButton("Start");
		panel.add(startButton);
		add(new JScrollPane(textArea),BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);
		
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				startButton.setEnabled(false);
				final int MAX = 1000;
				activity = new SimulatedActivity(MAX);
				activity.execute();
				progressDialog = new ProgressMonitor(ProgressMonitorFrame.this,"Waiting for simulated Activity",null,0,MAX);
				cancelMonitor.start();
			}
			
		});
		cancelMonitor = new Timer(500,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if(progressDialog.isCanceled()){
					activity.cancel(true);
					startButton.setEnabled(true);
				}else if(activity.isDone())
				{
					progressDialog.close();
					startButton.setEnabled(true);
				}else
				{
					progressDialog.setProgress(activity.getProgress());
				}
			}
			
		});
		pack();
	}

	public ProgressMonitorFrame(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ProgressMonitorFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ProgressMonitorFrame(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProgressMonitorFrame frame = new ProgressMonitorFrame();
		frame.setVisible(true);
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
					textArea.append(current+"\n");
					setProgress(current);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			return null;
		}
		protected void process(List<Integer> chunks){
			for(Integer chunk:chunks){
				
			}
		}
		protected void done()
		{
			startButton.setEnabled(true);
		}
	}
}
