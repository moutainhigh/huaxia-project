package ThinkInJava;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TrackEvent extends JFrame {
	private HashMap<String,JTextField> h = new HashMap<String,JTextField>();
	private String[] event = {
			"focusGained","focusLost","keyPressed","keyReleased","keyTyped","mouseClicked",
			"ouseEntered","mouseExited","mousePressed","mouseReleased","mouseDragged","mouseMoved"
	};
	private MyButton b1 =new MyButton(Color.blue,"test1");
	private MyButton b2 =new MyButton(Color.red,"test2");
	class MyButton extends JButton{
		void report(String field,String msg){
			h.get(field).setText(msg);
		}
		public MyButton(Color color,String label){
			super(label);
			setBackground(color);
			
		}
	}
	public TrackEvent(){
		this.setLayout(new GridLayout(event.length+1,2));
		for(String evt : event){
			JTextField t = new JTextField();
			t.setEditable(false);
			add(new JLabel(evt,JLabel.RIGHT));
			add(t);
			h.put(evt, t);
		}
		add(b1);
		add(b2);
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TrackEvent();
	}

}
