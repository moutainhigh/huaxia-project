package ThinkInJava;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.ProgressMonitor;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Progress extends JFrame{
	private JProgressBar pb = new JProgressBar();
	private ProgressMonitor pm = new ProgressMonitor(this,"Monitoring Progress","Test",0,100);
	private JSlider sb = new JSlider(JSlider.HORIZONTAL,0,100,60);
	public Progress(){
		this.setLayout(new GridLayout(2,1));
		add(pb);
		pm.setProgress(0);
		pm.setMillisToPopup(1000);
		sb.setValue(0);
		sb.setPaintTicks(true);
		sb.setMajorTickSpacing(20);
		sb.setMinorTickSpacing(5);
		sb.setBorder(new TitledBorder("Slide Me"));
		pb.setModel(sb.getModel());
		add(sb);
		sb.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				pm.setProgress(sb.getValue());
			}
			}
			);

		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String args[]){
		new Progress();
	}
}
