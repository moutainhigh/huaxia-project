package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

	private int rows;//行数
	
	private int cols;//列数
	
	private int bombCount;//炸弹数目
	
	private final int BLOCKWIDTH = 20;//每个方格的宽度
	
	private final int BLOCKHEIGHT = 20;//每个方格的长度
	
	private JLabel[][] labels;//每个方格的绘制信息
	private MyButton[][] buttons;//每个方格的绘制信息
	//位移量
	private final int[][] offset = {{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0}};
	public GamePanel(int rows,int cols){
		this.rows = rows;
		this.cols = cols;
		this.bombCount = rows*cols/10;
		this.labels = new JLabel[rows][cols];
		this.buttons = new  MyButton[rows][cols];
		this.setLayout(null);
		
		initButtons();
		initLabels();
	}
	//绘制扫雷边框
	private void initLabels(){
		for(int i=0 ;i<this.rows;i++){
			for(int j =0 ;j<this.cols;j++){
				JLabel l = new JLabel("",JLabel.CENTER);
				//每个小方格的边界
				l.setBounds(j*BLOCKWIDTH,i*BLOCKHEIGHT,BLOCKWIDTH,BLOCKHEIGHT);
				l.setBorder(BorderFactory.createLineBorder(Color.gray));
				l.setOpaque(true);;
				l.setBackground(Color.YELLOW);
				this.add(l);
				labels[i][j] = l;
			}
		}
		randomBomb();
		writeNumber();
	}
	public int[] retrurnSize(){
		int[] a = {this.cols*BLOCKWIDTH+20,this.rows*BLOCKHEIGHT+40};
		return a;
	}
	private void randomBomb(){
		for(int i = 0 ;i<this.bombCount;i++){
			int rRow = (int)(Math.random()*this.rows);
			int rCol = (int)(Math.random()*this.cols);
			this.labels[rRow][rCol].setText("*");
			this.labels[rRow][rCol].setBackground(Color.DARK_GRAY);
			this.labels[rRow][rCol].setForeground(Color.red);
		}
	}
	
	private void initButtons(){
		for(int i=0 ;i<this.rows;i++){
			for(int j =0 ;j<this.cols;j++){
				MyButton btn = new MyButton();
				//每个小方格的边界
				btn.setBounds(j*BLOCKWIDTH,i*BLOCKHEIGHT,BLOCKWIDTH,BLOCKHEIGHT);
				this.add(btn);
				btn.row = i;
				btn.col = j;
				buttons[i][j] = btn;
				//添加监听器
				btn.addActionListener(new  ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						open((MyButton)e.getSource());
					}
					
				});
			}
		}
	}
	//标注数字
	private void writeNumber(){
		for(int i=0 ;i<this.rows;i++){
			for(int j =0 ;j<this.cols;j++){
				if(labels[i][j].getText().equals("*")){
					continue;
				}
				int bombCount = 0;
				for(int[] off :offset){
					int row = i + off[1];
					int col = j + off[0];
					if(verify(row,col) && labels[row][col].getText().equals("*")){
						bombCount++;
					}
				}
				if(bombCount > 0){
					 labels[i][j].setText(String.valueOf(bombCount));
				}
			}
		}
	}
	//判断位置是否越界
	private boolean verify(int row,int col){
		return row>=0 && row<this.rows && col >= 0 && col <this.cols;
	}
	private void open(MyButton btn){
		//打开按钮
		btn.setVisible(false);
		switch(labels[btn.row][btn.col].getText()){
		case "*":
			for(int i=0 ;i<this.rows;i++){
				for(int j =0 ;j<this.cols;j++){
					buttons[i][j].setVisible(false);
				}
			}
			break;
		case "":
			for(int[] off :offset){
				int newRow = btn.row + off[0];
				int newCol = btn.col + off[1];
				if(verify(newRow,newCol)){
					MyButton sButton = buttons[newRow][newCol];
					if(sButton.isVisible()){
						open(sButton);
					}
				}
			}
		default:
		}
		
	}
}
