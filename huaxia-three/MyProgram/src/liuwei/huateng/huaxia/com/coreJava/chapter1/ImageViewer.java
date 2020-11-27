/**
 * Title: ImageViewer.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月25日下午3:20:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter1;

import javax.swing.JFrame;

/**
 * @class_name:ImageViewer2019年12月25日
 * @comments：显示图片的窗口
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月25日下午3:20:16
 */
public class ImageViewer {

	/**
	 * 
	 */
	public ImageViewer() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月25日下午3:20:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new ImageViewerFrame();
		frame.setTitle("ImageViewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
