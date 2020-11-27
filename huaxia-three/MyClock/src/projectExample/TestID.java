package projectExample;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 身份证信息解读
 * @author User
 *
 */
public class TestID implements  ActionListener{
	TextField tf;
	TextArea ta;
	Button b;
	public TestID(){
		Frame f = new Frame();
		Panel p = new Panel();
		b = new Button("ok");
		b.addActionListener(this);
		Label a = new Label("input your IDCard");
		tf = new TextField(20);
		ta = new TextArea();
		
		f.add(p,BorderLayout.NORTH);
		p.add(a);
		p.add(tf);
		p.add(b);
		p.add(ta,BorderLayout.CENTER);
		f.setSize(500,200);
		f.setVisible(true);
	}
	
	public static void main(String args[]){
		new TestID();
	}

	@Override
	public void actionPerformed(ActionEvent ee) {
		// 410221 19950529 3452
		if(ee.getSource() == b){
			String s = tf.getText();
			int s1 = s.length();
			String s2 = s.substring(0,2);//省份
			String s3 = s.substring(6,10);//出生年份
			String s4 = s.substring(10,12);//出生月份
			String s5 = s.substring(12,14);//出生日期
			int f =Integer.parseInt(s.substring(16,17)) ;//性別判断
			
			ta.append("出生年份"+s3+"\r\n");
			ta.append("出生日期"+s4+" "+s5+" \r\n");
			if(f%2==0){
				ta.append("性别："+"女"+" \r\n");
			}else{
				ta.append("性别："+"男"+" \r\n");
			}
			System.out.println(s);
		}
		
	}

}
