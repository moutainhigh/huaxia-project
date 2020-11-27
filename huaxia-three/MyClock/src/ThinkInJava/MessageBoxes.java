package ThinkInJava;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class MessageBoxes extends JFrame {
	private JButton[] b ={
		new JButton("Alert"),new JButton("Yes/No"),
		new JButton("Color"),new JButton("Input"),
		new JButton("3 Vals")
	};
	private JTextField txt = new JTextField(15);
	private ActionListener al = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			String id=((JButton)e.getSource()).getText();
			if(id.equals("Alert")){
				JOptionPane.showMessageDialog(null, "There is a bug on you!","Hey!",JOptionPane.ERROR_MESSAGE);
			}else if(id.equals("Yes/No")){
				JOptionPane.showConfirmDialog(null, "or no","choose yse",JOptionPane.YES_NO_OPTION);
			}else if(id.equals("Color")){
				Object[] options ={"Red","Green"};
				int sel = JOptionPane.showOptionDialog(null, "choose a Color", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if(sel != JOptionPane.CLOSED_OPTION)
					txt.setText("Color Selected: "+options[sel]);
			}else if(id.equals("Input")){
				String val =JOptionPane.showInputDialog("How many fingers do you see?");
				txt.setText(val);
			}else if(id.equals("3 Vals")){
				Object[] selections ={"First","Second","Third"};
				Object val = JOptionPane.showInputDialog(null, "Choose One", "Input", JOptionPane.INFORMATION_MESSAGE, null, selections, selections[0]);
				if(val != null)
				txt.setText(val.toString());
			}
		}
	};
	public MessageBoxes(){
		this.setLayout(new FlowLayout());
		for(int i=0;i<b.length;i++){
			b[i].addActionListener(al);
			add(b[i]);
		}
		add(txt);
		this.setVisible(true);
//		frame.setPreferredSize(new Dimension(1000,1000));
		this.setLocation(100,300);
		this.setSize(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		new MessageBoxes();
	}
}
