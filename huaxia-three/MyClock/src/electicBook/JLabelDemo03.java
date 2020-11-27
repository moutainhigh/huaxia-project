package electicBook;
import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class JLabelDemo03 {
	public static void main(String args[]){
		JFrame f = new JFrame("JLabel图像示例窗体");
		String picPath = "C:/Users/User/Pictures/Feedback/{D0EEE938-8EBA-4E86-A0C3-CF14C6C697F5}/Capture001.png";
		Icon icon = new ImageIcon(picPath);
		JLabel label = new JLabel("国旗",icon,JLabel.CENTER);
		label.setPreferredSize(new Dimension(200,300));
		label.setBackground(Color.YELLOW);
		label.setForeground(Color.red);
		f.add(label);
		f.setSize(260,160);
		f.setBackground(Color.WHITE);
		f.setLocation(300,200);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
